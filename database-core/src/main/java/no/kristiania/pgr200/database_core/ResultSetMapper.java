package no.kristiania.pgr200.database_core;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {
    T mapResultSet(ResultSet rs) throws SQLException;
}
