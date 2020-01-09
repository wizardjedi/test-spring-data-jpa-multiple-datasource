package com.a1s.testjpamultiple;

import com.a1s.testjpamultiple.domain.Author;
import com.a1s.testjpamultiple.domain.Book;
import com.a1s.testjpamultiple.primary.BookRepository;
import com.a1s.testjpamultiple.shard.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;

@SpringBootApplication
public class TestJpaMultipleApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TestJpaMultipleApplication.class);

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestJpaMultipleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Books:{}", bookRepository.findAll());
        log.info("Authors:{}", authorRepository.findAll());

        final Book b2 = new Book();
        b2.setId(2l);
        b2.setName("ываыаыва");

        bookRepository.save(b2);

        log.info("Books:{}", bookRepository.findAll());
    }
}
