package no.kristiania.pgr200.database;

import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.database.entity.Timeslot;
import org.junit.Test;


import java.sql.SQLException;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeslotDaoTest {

    private TimeslotDao dao  = new TimeslotDao(TestDataSource.createTestDataSource());


    @Test
    public void shouldInsertTimeslot() throws SQLException {
        Timeslot time = sampleTimeslot();
        dao.save(time);
        assertThat(dao.retrieve(time.getId()))
                .isEqualToComparingFieldByField(time);
        dao.delete(time.getId());
    }

    @Test
    public void shouldFindTimeslotById() throws SQLException {
        Timeslot time = sampleTimeslot();
        dao.save(time);
        assertThat(time.getId()).isNotNull();
        assertThat(dao.retrieve(time.getId())).isEqualToComparingFieldByField(time);
        dao.delete(time.getId());
    }

    @Test
    public void shouldDeleteTimeslot() throws SQLException {
        Timeslot timeslot = sampleTimeslot();
        dao.save(timeslot);
        assertThat(dao.listAll()).contains(timeslot);
        dao.delete(timeslot.getId());
        assertThat(dao.listAll()).doesNotContain(timeslot);
    }

    private Timeslot sampleTimeslot() {
        Timeslot time = new Timeslot();
        time.setTime(LocalTime.of(13, 30));
        return time;
    }
}
