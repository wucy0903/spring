package com.cht.springboot.demo.Demo6JDBC;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class JDBCRunner2  implements CommandLineRunner {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRunner2.class.getSimpleName());
    private static final String SELECT1 = "SELECT 666";

    public JDBCRunner2(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void run(String... args) throws Exception {
        SqlParameterSource parameter = new MapSqlParameterSource();
        Integer result = jdbcTemplate.queryForObject(SELECT1, parameter, Integer.class);
        LOGGER.info("Query using NamedParameterJDBC Template = " + result);
    }
}
