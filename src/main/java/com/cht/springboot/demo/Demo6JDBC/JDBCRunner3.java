package com.cht.springboot.demo.Demo6JDBC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class JDBCRunner3 implements CommandLineRunner {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRunner3.class.getSimpleName());
    private static final String QUERY_DML1 = "SELECT :a + :b - :c * :d";
    @Override
    public void run(String... args) throws Exception {
        SqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("a", 100)
                .addValue("b",200)
                .addValue("c",300)
                .addValue("d",400);

        Integer result = jdbcTemplate.queryForObject(QUERY_DML1, parameter, Integer.class);
        LOGGER.info("using SqlParameterSource , query result={}", result);
    }
}
