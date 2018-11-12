package no.kristiania.pgr200.database;

import no.kristiania.pgr200.database.dao.ConferenceTalkDao;
import no.kristiania.pgr200.database.dao.DayDao;
import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.database.entity.Day;
import no.kristiania.pgr200.database.entity.Timeslot;

import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DatabaseMain {

	private DataSource dataSource;
	private ConferenceTalkDao talkDao;
	private TimeslotDao timeDao;
	private DayDao dayDao;

	public DatabaseMain() {
		this.dataSource = DatabaseConnection.createDataSource();
		this.talkDao = new ConferenceTalkDao(dataSource);
		this.timeDao = new TimeslotDao(dataSource);
		this.dayDao = new DayDao(dataSource);
	}

	public void run(String[] args) throws SQLException, IOException {
		if (args.length == 0) {
			System.out.println("Run the class with an argument, on of `insert`, or ...");
			System.exit(1);
		}

		String command = args[0];

		if (command.equals("insert")) { // Default insertion of tables in DB
			insertTalk();
			Timeslot testTimeslot = new Timeslot();
			testTimeslot.setTime(LocalTime.of(9, 00));
			timeDao.save(testTimeslot);
			Day testDay = new Day();
			testDay.setDate(LocalDate.of(2018, 11, 01));
			dayDao.save(testDay);

			System.out.println("Tables successfully added to Database!");
		} else if (command.equals("resetdb")) {
			resetdb();
		} else if (command.equals("list")) { // Default insertion of tables in DB
			list(args);
		} else if (command.equals("show")) {
			show(args);
		} else if (command.equals("update")) {
			update(args);
		} else if (command.equals("add")) {
			insertTalkCmd(args);
		}
	}

	public void insertTalk() throws SQLException {
		talkDao.insertTalk("Java workshop", "Introduction to java programming language", "Java");
		talkDao.insertTalk("HTML & CSS workshop", "Introduction to HTML & CSS", "HTML & CSS");
		talkDao.insertTalk("Kotlin workshop", "Introduction to Kotlin programming language", "Kotlin");
		// timeDao.insertTime(LocalTime.of(10, 00), LocalTime.of(12, 00));
		// timeDao.insertTime(LocalTime.of(12, 30), LocalTime.of(14, 30));
		// timeDao.insertTime(LocalTime.of(14, 30), LocalTime.of(16, 30));
	}

	// ** COMMAND LINE ARGUMENTS ***

	public static void resetdb() throws SQLException {
		DataSource dataSource = DatabaseConnection.createDataSource();
		try (Connection conn = dataSource.getConnection()) {
			String sql = "drop table conference_talks cascade;" + "drop table days cascade;"
					+ "drop table timeslots cascade;" + "drop table flyway_schema_history cascade;";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.executeUpdate();
			}
		}
	}

	// Show the detail of an conference.
	public List show(String[] args) throws SQLException, IOException {
		List list = null;
		String command1 = args[1];

		for (int i = 0; i < args.length - 1; i++) {
			if (command1.equals("-java")) {
				System.out.println("");
				System.out.print(talkDao.retrieve((long) 1));
			} else if (command1.equals("-HTML/CSS")) {
				System.out.println("");
				System.out.println(talkDao.retrieve((long) 2));
			} else if (command1.equals("-kotlin")) {
				System.out.println("");
				System.out.println(talkDao.retrieve((long) 3));
			}
		}
		return list;
	}

	// List every conference and its topic FCK
	private List<String> list(String[] args) throws SQLException, IOException {
		List<String> list = null;
		String command1 = args[1];
		String command2 = args[2];

		for (int i = 1; i < args.length - 1; i++) {
			if (command1.equals("-talks") && command2.equals("-topics")) {
				System.out.println("");
				System.out.println("LISTING CONFERENCES WITH TOPICS");
				System.out.println("");
				System.out.println("----TITLE WITH TOPIC----");
				System.out.println(talkDao.listTalks());
			}
		}
		return list;
	}

	/*
	 * Update the title of an existing conference. The conference title can be
	 * chosen by user input FAEN
	 */
	private List update(String[] args) throws SQLException, IOException {
		List list = null;
		String command = args[1];

		for (int i = 0; i < args.length - 1; i++) {
			if (command.equals("-updatetitle")) {
				System.out.println(talkDao.updateTalk());
			}
		}
		return list;
	}

	/*
	 * Add a new a new conference The title, description and topic can be set by
	 * user input.
	 */
	private List insertTalkCmd(String[] args) throws SQLException, IOException {
		List list = null;

		for (int i = 0; i < args.length; i++) {
			System.out.println(talkDao.add());
		}
		return list;
	}

	public static void main(String[] args) throws SQLException, IOException {

		// ConferenceTalkDao conferenceTalkDao = new
		// ConferenceTalkDao(DatabaseConnection.createDataSource());
		// DayDao dayDao = new DayDao(DatabaseConnection.createDataSource());
		TimeslotDao timeslotDao = new TimeslotDao(DatabaseConnection.createDataSource());
		new DatabaseMain().run(args);
	}

}
