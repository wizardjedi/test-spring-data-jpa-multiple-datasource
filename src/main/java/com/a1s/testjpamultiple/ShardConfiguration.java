package com.a1s.testjpamultiple;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "shardEntityManagerFactory",
    basePackages = "com.a1s.testjpamultiple.shard",
    transactionManagerRef = "shardTransactionManager"
)
@EnableTransactionManagement
public class ShardConfiguration {
    @Bean(name = "shardEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean shardEntityManagerFactory(
        @Qualifier("DS2") DataSource dataSource
    ) {
        final EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);

        return builder
            .dataSource(dataSource)
            .packages("com.a1s.testjpamultiple.domain")
            .persistenceUnit("shardEntityManagerFactory")
            .build();
    }

    @Bean(name = "shardTransactionManager")
    public PlatformTransactionManager shardTransactionManager(
        @Qualifier("shardEntityManagerFactory") EntityManagerFactory shardEntityManagerFactory) {
        return new JpaTransactionManager(shardEntityManagerFactory);
    }
}
