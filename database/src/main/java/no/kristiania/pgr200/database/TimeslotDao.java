package no.kristiania.pgr200.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TimeslotDao extends AbstractDao implements DataAccessObject<Timeslot> {

    public TimeslotDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Timeslot timeslot) throws SQLException {
        try (Connection connection = getDataSource().getConnection()) {
            String sql =
                    "INSERT into timeslots (time) values (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setObject(1, timeslot.getTime());
                statement.executeUpdate();

                try (ResultSet rs = statement.getGeneratedKeys()) {
                    rs.next();
                    timeslot.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        deleteObject("delete from timeslots where id = ?", id);
    }

    @Override
    public List<Timeslot> listAll() throws SQLException {
        return list("select * from timeslots", this::mapToTimeslot);
    }


    @Override
    public Timeslot retrieve(Long id) throws SQLException {
        return retrieveSingleObject("select * from timeslots where id = ?", this::mapToTimeslot, id);
    }


    public Timeslot mapToTimeslot(ResultSet rs) throws SQLException {
        Timeslot timeslot = new Timeslot();
        timeslot.setId(rs.getLong("id"));
        timeslot.setTime(rs.getTime(2).toLocalTime());

        return timeslot;
    }
}


