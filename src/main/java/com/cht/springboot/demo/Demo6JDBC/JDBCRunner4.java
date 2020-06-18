package com.cht.springboot.demo.Demo6JDBC;

import com.cht.springboot.demo.Demo6JDBC.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JDBCRunner4 implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCRunner4.class.getSimpleName());
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String DROP_SQL = "DROP TABLE USERS IF EXISTS";
    private static final String CREATE_TABLE = "CREATE TABLE USERS (ID SERIAL, USERNAME VARCHAR(255), HASHPASSWORD VARCHAR(255))";
    private static final String INSERT_USERS = "INSERT INTO USERS(USERNAME, HASHPASSWORD) VALUES (?,?)";
    private static final String QUERY_USERS = "SELECT * FROM USERS";
    private static final String QUERY_USERS_BY_NAME = "SELECT * FROM USERS WHERE USERNAME = ?";

    @Override
    public void run(String... args) throws Exception {
        createTable();
        insertTableData();
        querySomeData();
        queryDataByUsername();
    }

    private void queryDataByUsername() {
        List<User> users = jdbcTemplate.query(QUERY_USERS_BY_NAME, new Object[]{"Mark"},
                (recordSet, rowNum) -> new User(recordSet.getString("USERNAME"),
                        recordSet.getString("HASHPASSWORD")));
        users.forEach(user -> LOGGER.info("query user with username='Tim' result = {}", user.toString()));
    }

    private void querySomeData() {
        LOGGER.info("Query data from db");
        //recordSet , row number
        jdbcTemplate.query(QUERY_USERS, (rs, rowNum) -> new User(rs.getString("USERNAME"),
                rs.getString("HASHPASSWORD"))).forEach(user -> LOGGER.info(user.toString()));
    }

    private void insertTableData() {
        List<Object[]> splitUpName = Arrays.asList("Mark password1", "John password2", "Ken password3", "Tim password4")
                .stream().map(userPasswordString-> userPasswordString.split(" "))
                .collect(Collectors.toList());
        LOGGER.info("split result print as {}",splitUpName);
        splitUpName.forEach(userPasswordPair->LOGGER.info("user={}, hashed password={}", userPasswordPair[0], userPasswordPair[1]));
        jdbcTemplate.batchUpdate(INSERT_USERS, splitUpName);
    }


    private void createTable() {
        LOGGER.info("drop old table, create new table...");
        try {
            String URL = jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();
            LOGGER.info("current jdbc template url = {}", URL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcTemplate.execute(DROP_SQL);
        jdbcTemplate.execute(CREATE_TABLE);
    }

}
