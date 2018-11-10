package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.ConferenceTalk;
import no.kristiania.pgr200.database.interfaces.DataAccessObject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConferenceTalkDao extends AbstractDao implements DataAccessObject<ConferenceTalk> {

    public ConferenceTalkDao(DataSource dataSource) {
        super(dataSource);
    }

    public void insertTalk(String title, String description, String topic) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql =
                    "insert into conference_talks (title, description, topic) values (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, title);
                statement.setString(2, description);
                statement.setString(3, topic);
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void save(ConferenceTalk talk) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql =
                    "insert into conference_talks (title, description, topic) values (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

    public void update(Long id, String newTitle, String newDescription, String newTopic) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            String sql =
                    "update conference_talks set title = ? set description = ? set topic = ? where id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.setString(2, newTitle);
                statement.setString(3, newDescription);
                statement.setString(4, newTopic);
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
}
