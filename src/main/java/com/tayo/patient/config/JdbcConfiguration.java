package com.tayo.patient.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;


@Configuration
public class JdbcConfiguration {

    final static Logger LOGGER = LoggerFactory.getLogger(JdbcConfiguration.class);

    @Autowired
    private Environment env;


    @Value("${db.maxPoolSize}")
    private int maxPoolSize;


    @PostConstruct
    private void startup() {
        LOGGER.info("***************************APPLICATION CONTEXT STARTED******************************");
    }


    /*
     * Configure HikariCP pooled DataSource.
     */
    @Bean(destroyMethod = "")
    DataSource ArtDataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        try {


            dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
            dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.jdbcUrl"));
            dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
            dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
            dataSourceConfig.setConnectionTestQuery("SELECT 1 from dual");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return new HikariDataSource(dataSourceConfig);
    }


    // Spring infamous jdbc Template for performing jdbc operations - encapsulate the main steps of the jdbc ops
    @Bean
    @Lazy(true)
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(ArtDataSource());
        return jdbcTemplate;
    }


    @PreDestroy
    private static void destroy() {
        LOGGER.info("**********************Application Destroyed...**********************");
    }

}
