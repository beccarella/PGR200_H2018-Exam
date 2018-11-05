package no.kristiania.pgr200.database_core;

import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeslotDaoTest {
    private TimeslotDao dao = new TimeslotDao(TestDataSource.createTestDataSource());

    @Test
    public void shouldInsertDay() throws SQLException {
        Timeslot timeslot = sampleTimeslot();
        dao.save(timeslot);
        assertThat(dao.listAll()).contains(timeslot);
        dao.delete(timeslot.getId());
    }

    @Test
    public void shouldFindTalkById() throws SQLException {
        Timeslot timeslot = sampleTimeslot();
        dao.save(timeslot);
        assertThat(timeslot.getId()).isNotNull();
        assertThat(dao.retrieve(timeslot.getId())).isEqualToComparingFieldByField(timeslot);
    }

    @Test
    public void shouldDeleteTalk() throws SQLException {
        Timeslot timeslot = sampleTimeslot();
        dao.save(timeslot);
        assertThat(dao.listAll()).contains(timeslot);
        dao.delete(timeslot.getId());
        assertThat(dao.listAll())
                .doesNotContain(timeslot);
    }

    public Timeslot sampleTimeslot() {
        Timeslot timeslot = new Timeslot();
        timeslot.setId(Long.valueOf(1));
        timeslot.setTime(LocalTime.of(9, 00));
        return timeslot;
    }
}
