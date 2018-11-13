package no.kristiania.pgr200.database;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnection {

    public static DataSource createDataSource() {

        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("innlevering.properties");
            properties.load(input);

            PGPoolingDataSource dataSource = new PGPoolingDataSource();
            dataSource.setUrl(properties.getProperty("dataSource.url"));
            dataSource.setUser(properties.getProperty("dataSource.username"));
            dataSource.setPassword(properties.getProperty("dataSource.password"));

            Flyway.configure().dataSource(dataSource).load().migrate();

            return dataSource;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
