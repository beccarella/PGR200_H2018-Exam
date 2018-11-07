package no.kristiania.pgr200.database;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class DatabaseTest {
    private DataSource createDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        return dataSource;
    }
}
