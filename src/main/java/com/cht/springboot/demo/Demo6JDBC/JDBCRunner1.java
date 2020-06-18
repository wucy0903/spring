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
public class JDBCRunner1 implements CommandLineRunner {
    //Spring Container has JDBCTemplate Bean
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRunner1.class.getSimpleName());
    private static final String SELECT1 = "SELECT 888";
    @Override
    public void run(String... args) throws Exception {
        SqlParameterSource parameter = new MapSqlParameterSource();
        Integer result = jdbcTemplate.queryForObject(SELECT1, parameter, Integer.class);
        LOGGER.info("Query using NamedParameterJDBC Template = " + result);

    }
}
