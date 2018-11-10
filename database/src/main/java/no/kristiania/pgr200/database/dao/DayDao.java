package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.interfaces.DataAccessObject;
import no.kristiania.pgr200.database.entity.Day;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DayDao extends AbstractDao implements DataAccessObject<Day> {

    public DayDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Day day) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql =
                    "INSERT INTO days (date) values (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setObject(1, day.getDate());
                statement.executeUpdate();

                try (ResultSet rs = statement.getGeneratedKeys()) {
                    rs.next();
                    day.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        deleteObject("delete from days where id = ?", id);
    }

    @Override
    public List<Day> listAll() throws SQLException {
        return list("select * from days", this::mapToDay);
    }

    @Override
    public Day retrieve(Long id) throws SQLException {
        return retrieveSingleObject("SELECT * FROM days where id = ?", this::mapToDay, id);
    }

    public Day mapToDay(ResultSet rs) throws SQLException {
        Day day = new Day();

        day.setId(rs.getLong("id"));
        day.setDate(rs.getDate(2).toLocalDate());
        return day;
    }
}

