package no.kristiania.pgr200.database;


import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;
import java.util.UUID;

public class DatabaseMain {

    private static DataSource dataSource;

    public static void main(String[] args) throws SQLException {
        dataSource = createDataSource();
        ConferenceTalkDao conferenceTalkDao = new ConferenceTalkDao(dataSource);
        DayDao dayDao = new DayDao(dataSource);
        TimeslotDao timeslotDao = new TimeslotDao(dataSource);


        if (args[0].equals("list")) {
            System.out.println(conferenceTalkDao.listAll());
            System.out.println(dayDao.listAll());
            System.out.println(timeslotDao.listAll());

        } else if(args[0].equals("resetdb")) {
            resetdb();

        } else {
            ConferenceTalk testTalk = new ConferenceTalk();
            testTalk.setTitle("Some title");
            testTalk.setDescription("Some description");
            conferenceTalkDao.save(testTalk);

            Day testDay = new Day();
            testDay.setDate(LocalDate.of(2018, 11, 01));
            dayDao.save(testDay);

            Timeslot testTimeslot = new Timeslot();
            testTimeslot.setTime(LocalTime.of(9, 00));
            timeslotDao.save(testTimeslot);
        }
    }

    public static DataSource createDataSource() {

        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/innlevering.properties");
            properties.load(input);

            PGPoolingDataSource dataSource = new PGPoolingDataSource();
            dataSource.setUrl("jdbc:postgresql://" + properties.getProperty("dbname"));
            dataSource.setUser(properties.getProperty("dbuser"));
            dataSource.setPassword(properties.getProperty("dbpassword"));

            Flyway.configure().dataSource(dataSource).load().migrate();

            return dataSource;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void resetdb() throws SQLException {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "drop table conference_talks cascade;"
                    + "drop table days cascade;"
                    + "drop table timeslots cascade;"
                    + "drop table flyway_schema_history cascade";
            try(PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        }
    }
}
