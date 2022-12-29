package com.vsu.ru.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsu.ru.model.DataBaseItem;
import com.vsu.ru.dao.DataBaseAbstractDao;
import lombok.SneakyThrows;

import java.io.*;
import java.util.List;

public abstract class ModelServiceAbs<K, T extends DataBaseItem<K>> implements ModelService<K, T>{
    private final ObjectMapper objectMapper = new ObjectMapper();
    {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
    }

    @SneakyThrows
    @Override
    public List<T> readListFromFile(String pathToFile) {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(pathToFile);
        return objectMapper.readValue(resourceAsStream, new TypeReference<List<T>>() {});
    }

    @SneakyThrows
    @Override
    public String getAsString(List<T> list) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
    }

    @SneakyThrows
    @Override
    public String getAsString(T value) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    }

    @SneakyThrows
    @Override
    public void writeToConsole(List<T> list) {
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
    }

    @SneakyThrows
    @Override
    public void writeToConsole(T value) {
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value));
    }

    @SneakyThrows
    @Override
    public void writeToFile(String fileName, List<T> list) {
        File f = new File(fileName);
        f.createNewFile();
        try (OutputStream outputStream =
                     new FileOutputStream(f)) {
            PrintStream ps = new PrintStream(outputStream);
            ps.println(getAsString(list));
        }
    }

    @SneakyThrows
    @Override
    public void writeToFile(String fileName, T value) {
        File f = new File(fileName);
        f.createNewFile();
        try(OutputStream outputStream =
                    new FileOutputStream(f)){
            PrintStream ps = new PrintStream(outputStream);
            ps.println(getAsString(value));
        }
    }

    @SneakyThrows
    @Override
    public T convertFromString(String string) {
        return objectMapper.readValue(string, new TypeReference<T>(){});
    }

    @SneakyThrows
    @Override
    public List<T> convertAllFromString(String string) {
        return objectMapper.readValue(string, new TypeReference<List<T>>() {});
    }

    @Override
    public void create(List<T> list) {
        getDataBaseAbstractServers().saveOrUpdateAll(list);
    }

    @Override
    public void create(T value) {
        getDataBaseAbstractServers().saveOrUpdate(value);
    }

    @Override
    public List<T> getAll() {
        return getDataBaseAbstractServers().readAll();
    }

    @Override
    public T getById(K id) {
        return getDataBaseAbstractServers().read(id);
    }

    @Override
    public void deleteAll() {
        getDataBaseAbstractServers().deleteAll();
    }

    @Override
    public void deleteById(K id) {
        getDataBaseAbstractServers().delete(id);
    }

    @Override
    public void deleteAll(List<K> ids) {
        ids.forEach(this::deleteById);
    }

    protected abstract DataBaseAbstractDao<T, K> getDataBaseAbstractServers();
}
