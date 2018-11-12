package no.kristiania.pgr200.database;

import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.database.entity.Timeslot;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeslotDaoTest {

	private TimeslotDao timeSlotsDao = new TimeslotDao(TestDataSource.createTestDataSource());

	@Test
	public void shouldFindSavedTime() throws SQLException {

		Timeslot timeSlots = sampleTimeslot();
		timeSlotsDao.save(timeSlots);
		assertThat(timeSlotsDao.listAll()).contains(timeSlots);
		System.out.println(timeSlotsDao.listAll());
	}

	@Test
	public void shouldInsertTimeslot() throws SQLException {
		Timeslot timeSlots = sampleTimeslot();
		timeSlotsDao.save(timeSlots);

		assertThat(timeSlots.getId()).isNotNull();
		assertThat(timeSlotsDao.retrieve(timeSlots.getId())).isEqualToComparingFieldByField(timeSlots);
	}

	// ******SAMPLE DATA FOR TESTING PURPOSES*****
	private Timeslot sampleTimeslot() {
		Timeslot time = new Timeslot();
		time.setTime(LocalTime.of(13, 30));
		return time;
	}
}
