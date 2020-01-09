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
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    basePackages = "com.a1s.testjpamultiple.primary",
    transactionManagerRef = "primaryTransactionManager"
)
@EnableTransactionManagement
public class PrimaryConfiguration {
    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
        @Qualifier("DS1") DataSource dataSource
    ) {
        final EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);

        return builder
            .dataSource(dataSource)
            .packages("com.a1s.testjpamultiple.domain")
            .persistenceUnit("primaryEntityManagerFactory")
            .build();
    }

    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
        @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory
    ) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }

}
