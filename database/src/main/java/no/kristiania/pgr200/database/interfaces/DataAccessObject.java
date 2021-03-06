package no.kristiania.pgr200.database.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject <T> {
    /**
     * Saves the argument object into the database.
     * Will generate a primary key if needed and set
     * the object ID to this key
     *
     * @param object the object to be saved
     */
    void save(T object) throws SQLException;

    /**
     *
     * @param id
     * @throws SQLException
     */
    void delete(Long id) throws SQLException;

    /**
     *
     */
//    void update(Long id) throws SQLException;

    /**
     * Finds the single object with the argument id
     * in the database.
     *
     * @param id The primary key of the row to find
     * @return A new copy of the object in the database or null if not found
     * @throws SQLException
     */
    T retrieve(Long id) throws SQLException;

    /**
     * Finds all the object of this type in the database.
     *
     * @return All objects in the database
     * @throws SQLException
     */
    List<T> listAll() throws SQLException;
}
