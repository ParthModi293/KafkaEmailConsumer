package com.email.kafkaemailconsumer;

import jakarta.persistence.SharedCacheMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan({"org.communication","org.common", "com.email.kafkaemailconsumer"})
@EnableJpaRepositories({"org.communication","org.common", "com.email.kafkaemailconsumer"})
public class KafkaEmailConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaEmailConsumerApplication.class, args);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        em.setPackagesToScan("org.communication","org.common");
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        // TODO: change with the postgress credentianal
        //hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        return em;
    }

}
