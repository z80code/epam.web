package by.gsu.epamlab.dao.entitys;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {

    T  getById(int id) throws SQLException;
    T add(T item) throws SQLException;
    boolean deleteById(int id) throws SQLException;
    List<T> getAll() throws SQLException;

    void close() throws SQLException;
}
