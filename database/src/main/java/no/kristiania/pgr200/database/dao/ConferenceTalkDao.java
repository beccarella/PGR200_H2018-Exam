package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.ConferenceTalk;
import no.kristiania.pgr200.database.interfaces.DataAccessObject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConferenceTalkDao extends AbstractDao implements DataAccessObject<ConferenceTalk> {

	public ConferenceTalkDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void save(ConferenceTalk talk) throws SQLException {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "insert into conference_talks (title, description, topic) values (?, ?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, talk.getTitle());
				statement.setString(2, talk.getDescription());
				statement.setString(3, talk.getTopic());
				statement.executeUpdate();

				try (ResultSet rs = statement.getGeneratedKeys()) {
					rs.next();
					talk.setId(rs.getLong(1));
				}
			}
		}
	}

	public void insertTalk(String title, String description, String topic) throws SQLException {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "insert into conference_talks (title, description, topic) values (?, ?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, title);
				statement.setString(2, description);
				statement.setString(3, topic);
				statement.executeUpdate();
			}
		}
	}

	@Override
	public void delete(Long id) throws SQLException {
		deleteObject("delete from conference_talks where id = ?", id);
	}

	@Override
	public List<ConferenceTalk> listAll() throws SQLException {
		return list("select * from conference_talks", this::mapToTalk);
	}

	@Override
	public ConferenceTalk retrieve(Long id) throws SQLException {
		return retrieveSingleObject("Select * from conference_talks where id = ?", this::mapToTalk, id);
	}

	public ConferenceTalk mapToTalk(ResultSet rs) throws SQLException {
		ConferenceTalk talk = new ConferenceTalk();

		talk.setId(rs.getLong("id"));
		talk.setTitle(rs.getString("title"));
		talk.setDescription(rs.getString("description"));
		talk.setTopic(rs.getString("topic"));
		return talk;
	}

	// THESE METHODS ARE FOR THE COMMAND LINE ARGUMENTS

	public List<ConferenceTalk> listTalks() throws SQLException {
		return list("select * from conference_talks", this::mapResultSetToTalk);
	}

	// Gets the title and topics from talk and return it
	public ConferenceTalk mapResultSetToTalk(ResultSet rs) throws SQLException {
		ConferenceTalk talks = new ConferenceTalk();
		talks.setTitle(rs.getString("title"));
		talks.setTopic(rs.getString("topic"));
		return talks;
	}

	// Update title for a talk
	public List<ConferenceTalk> updateTalk() throws SQLException {
		List<ConferenceTalk> update = null;
		try (Connection conn = dataSource.getConnection()) {
			String sql = "UPDATE conference_talks SET title = ? WHERE ID = ? ";

			try (PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				Scanner reader = new Scanner(System.in);
				System.out.print("Enter title: ");
				String titleName = reader.nextLine();

				statement.setString(1, titleName);
				statement.setLong(2, 1);

				int affectedRows = statement.executeUpdate();
				update = new ArrayList<>();

				try (ResultSet rs = statement.getGeneratedKeys()) {
					while (rs.next()) {
						System.out.print("Title UPDATED on row ID = " + rs.getInt(affectedRows));
						break;
					}
					reader.close();
				}
			}
		}
		return update;
	}

	// add a new talk
	public List<ConferenceTalk> add() throws SQLException {
		List<ConferenceTalk> add = null;

		try (Connection conn = dataSource.getConnection()) {
			String sql = "insert into conference_talks (title, description, topic) values (?, ?, ?)";
			try (PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				Scanner reader = new Scanner(System.in);
				System.out.println("Enter title: ");
				String myTitle = reader.nextLine();
				System.out.println("Enter description: ");
				String myDescription = reader.nextLine();
				System.out.println("Enter topic: ");
				String myTopics = reader.nextLine();

				statement.setString(1, myTitle);
				statement.setString(2, myDescription);
				statement.setString(3, myTopics);

				int affectedRows = statement.executeUpdate();
				add = new ArrayList<>();

				try (ResultSet rs = statement.getGeneratedKeys()) {
					while (rs.next()) {
						System.out.print("Title, Description and topic Added on row ID = " + rs.getInt(affectedRows));
						break;
					}
					reader.close();
				}
			}
		}
		return add;
	}

}
