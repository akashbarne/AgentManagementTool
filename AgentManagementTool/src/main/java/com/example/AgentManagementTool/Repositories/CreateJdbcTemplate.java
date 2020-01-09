package com.example.AgentManagementTool.Repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class CreateJdbcTemplate {
    private static final String url = "jdbc:mysql://localhost:3306/ AgentInstaller";
    private static final String dbUsername = "root";
    private static final String dbPassword = "services";

    private static DataSource dataSource;

    public JdbcTemplate CreateTemplate() throws Exception {

        dataSource = getDataSource();

        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);

        return template;

    }

    public static DriverManagerDataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(url);

        dataSource.setUsername(dbUsername);

        dataSource.setPassword(dbPassword);

        return dataSource;
    }
}

