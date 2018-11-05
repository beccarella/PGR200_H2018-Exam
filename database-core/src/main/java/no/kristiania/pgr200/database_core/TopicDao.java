package no.kristiania.pgr200.database_core;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TopicDao extends AbstractDao implements DataAccessObject<Topic> {
    public TopicDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Topic topic) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql =
                    "INSERT INTO topics (topic) values (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setObject(1, topic.getTopicName());
                statement.executeUpdate();

                try (ResultSet rs = statement.getGeneratedKeys()) {
                    rs.next();
                    topic.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        deleteObject("delete from topics where id = ?", id);
    }

//    @Override
//    public void update(Long id) throws SQLException {
//       updateObject("update conference_talks set title = ? where id = ?", id);
//    }

    @Override
    public Topic retrieve(Long id) throws SQLException {
        return retrieveSingleObject("select * from topics where id = ?", this::mapToTopic, id);
    }

    @Override
    public List<Topic> listAll() throws SQLException {
        return list("select * from topics", this::mapToTopic);
    }

    public Topic mapToTopic(ResultSet rs) throws SQLException {
        Topic topic = new Topic();
        topic.setId(rs.getLong("id"));
        topic.setTopicName(rs.getString("topicName"));
        return topic;
    }
}
