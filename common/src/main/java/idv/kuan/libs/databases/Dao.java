package idv.kuan.libs.databases;

public interface Dao<T> {

    void add(T t);

    T get(Object id);

    void update(T t, Object o);

    void delete(Object o);

}
