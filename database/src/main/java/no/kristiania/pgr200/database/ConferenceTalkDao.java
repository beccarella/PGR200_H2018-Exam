package no.kristiania.pgr200.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConferenceTalkDao extends AbstractDao implements DataAccessObject<ConferenceTalk>{

    public ConferenceTalkDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(ConferenceTalk talk) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql =
                    "INSERT INTO  conference_talks (title, description) values (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, talk.getTitle());
                statement.setString(2, talk.getDescription());
                statement.executeUpdate();

                try (ResultSet rs = statement.getGeneratedKeys()) {
                    rs.next();
                    talk.setId(rs.getLong(1));
                }
            }
        }
    }

    public void update(String sql, Long id, String newTitle) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.setString(2, newTitle);
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
        return talk;
    }
}
