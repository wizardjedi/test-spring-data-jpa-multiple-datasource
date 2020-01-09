package com.a1s.testjpamultiple;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean(name = "DS1")
    public EmbeddedDatabase dataSource1() {
        return new EmbeddedDatabaseBuilder().
            setType(EmbeddedDatabaseType.H2).
            setName("DB1").
            addScript("schema1.sql").
            build();

    }

    @Bean(name = "DS2")
    public EmbeddedDatabase dataSource2() {
        return new EmbeddedDatabaseBuilder().
            setType(EmbeddedDatabaseType.H2).
            setName("DB2").
            addScript("schema2.sql").
            build();

    }

    /*@Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
        @Qualifier("primaryTransactionManager") PlatformTransactionManager primaryTransactionManager,
        @Qualifier("shardTransactionManager") PlatformTransactionManager shardTransactionManager
    ) {
        return new ChainedTransactionManager(primaryTransactionManager, shardTransactionManager);
    }*/
}
