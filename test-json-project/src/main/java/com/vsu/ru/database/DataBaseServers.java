package com.vsu.ru.database;

import com.vsu.ru.model.DataBaseItem;

import java.util.List;

public interface DataBaseServers<T extends DataBaseItem<K>, K> {
    void saveOrUpdate(T item);
    T read(K id);
    List<T> readAll();
    void saveOrUpdateAll(Iterable<T> iterable);
    boolean isExist(K id);
    void delete(K id);
    void deleteAll();
}
