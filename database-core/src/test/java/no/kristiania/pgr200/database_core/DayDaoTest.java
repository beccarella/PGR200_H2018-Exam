package no.kristiania.pgr200.database_core;

import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DayDaoTest {
    private DayDao dao = new DayDao(TestDataSource.createTestDataSource());

    @Test
    public void shouldInsertDay() throws SQLException {
        Day day = sampleDay();
        dao.save(day);
        assertThat(dao.listAll()).contains(day);
        dao.delete(day.getId());
    }

    @Test
    public void shouldFindTalkById() throws SQLException {
        Day day = sampleDay();
        dao.save(day);
        assertThat(day.getId()).isNotNull();
        assertThat(dao.retrieve(day.getId())).isEqualToComparingFieldByField(day);
    }

    @Test
    public void shouldDeleteTalk() throws SQLException {
        Day day = sampleDay();
        dao.save(day);
        assertThat(dao.listAll()).contains(day);
        dao.delete(day.getId());
        assertThat(dao.listAll())
                .doesNotContain(day);
    }

    public Day sampleDay() {
        Day day = new Day();
        day.setId(Long.valueOf(1));
        day.setDate(LocalDate.of(2018, 11,12));
        return day;
    }
}
