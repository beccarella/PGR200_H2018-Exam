package no.kristiania.pgr200.database.main;


import no.kristiania.pgr200.database.dao.ConferenceTalkDao;
import no.kristiania.pgr200.database.dao.DayDao;
import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.database.entity.ConferenceTalk;
import no.kristiania.pgr200.database.entity.Day;
import no.kristiania.pgr200.database.entity.Timeslot;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


public class DatabaseMain {

    public static void main(String[] args) throws SQLException {
//        dataSource = createDataSource();
        ConferenceTalkDao conferenceTalkDao = new ConferenceTalkDao(DatabaseConnection.createDataSource());
        DayDao dayDao = new DayDao(DatabaseConnection.createDataSource());
        TimeslotDao timeslotDao = new TimeslotDao(DatabaseConnection.createDataSource());


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
            testTalk.setTopic("Some arbitrary topic");
            conferenceTalkDao.save(testTalk);

            Day testDay = new Day();
            testDay.setDate(LocalDate.of(2018, 11, 01));
            dayDao.save(testDay);

            Timeslot testTimeslot = new Timeslot();
            testTimeslot.setTime(LocalTime.of(9, 00));
            timeslotDao.save(testTimeslot);
        }
    }


    public static void resetdb() throws SQLException {
        DataSource dataSource = DatabaseConnection.createDataSource();
        try(Connection conn = dataSource.getConnection()) {
            String sql = "drop table conference_talks cascade;"
                    + "drop table days cascade;"
                    + "drop table timeslots cascade;"
                    + "drop table flyway_schema_history cascade;";
            try(PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        }
    }
}
