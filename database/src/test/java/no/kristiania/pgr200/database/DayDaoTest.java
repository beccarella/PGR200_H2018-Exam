package no.kristiania.pgr200.database;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DayDaoTest {

    private DayDao dao = new DayDao(TestDataSource.createTestDataSource());

    @Test
    public void shouldCreateDay() throws SQLException {
        Day day = sampleDay();
        dao.save(day);
        assertThat(day).hasNoNullFieldsOrProperties();
        assertThat(dao.retrieve(day.getId()))
                .isEqualToComparingFieldByField(day);
    }

    @Test
    public void shouldIncludeSavedDayInListAll() throws SQLException {
        Day day = sampleDay();
        dao.save(day);
        assertThat(dao.listAll())
                .contains(day);
    }

    private Day sampleDay() {
        Day day = new Day();
        day.setDate(LocalDate.of(2018, 12, 07));

        return day;
    }
}
