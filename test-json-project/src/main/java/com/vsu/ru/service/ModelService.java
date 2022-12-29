package com.vsu.ru.service;

import com.vsu.ru.model.DataBaseItem;

import java.util.List;

public interface ModelService <K, T extends DataBaseItem<K>>{
    List<T> readListFromFile(String pathToFile);
    String getAsString(List<T> list);
    String getAsString(T value);
    void create(List<T> list);
    void create(T value);
    void writeToConsole(List<T> list);
    void writeToConsole(T value);
    void writeToFile(String fileName, List<T> list);
    void writeToFile(String fileName, T value);
    List<T> getAll();
    T getById(K id);
    void deleteAll();
    void deleteById(K id);
    void deleteAll(List<K> ids);
    T convertFromString(String string);
    List<T> convertAllFromString(String string);
    K getIdFromString(String stringId);
}
