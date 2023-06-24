package idv.kuan.libs.databases;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void create(T entity) throws SQLException;

    T findById(Serializable id);

    void update(T entity) throws SQLException;

    void delete(T entity);

    List<T> findAll();



}
