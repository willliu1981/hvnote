package idv.kuan.libs.databases;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void create(T entity) throws SQLException;

    T findById(Serializable id) throws SQLException;

    void update(T entity) throws SQLException;

    default void createOrUpdateEntity(T entity) throws SQLException {
        throw new UnsupportedOperationException("upsert() is not yet implemented");
    }

    void delete(T entity);

    List<T> findAll() throws SQLException;

    default <U> U findByIDOrAll(T entity) throws SQLException {
        throw new UnsupportedOperationException("findByIDOrAll() is not yet implemented");
    }


}
