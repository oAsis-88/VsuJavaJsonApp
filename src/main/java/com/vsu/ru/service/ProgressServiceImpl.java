package com.vsu.ru.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vsu.ru.model.Progress;
import com.vsu.ru.dao.DataBaseAbstractDao;
import com.vsu.ru.dao.ProgressDao;

import java.util.List;

public class ProgressServiceImpl extends ModelServiceAbs<Long, Progress>{
    private final ProgressDao progressServers = new ProgressDao();

    @Override
    public Long getIdFromString(String stringId) {
        return Long.valueOf(stringId);
    }

    @Override
    protected DataBaseAbstractDao<Progress, Long> getDataBaseAbstractDao() {
        return progressServers;
    }

    @Override
    protected TypeReference<List<Progress>> getTypeReference() {
        return new TypeReference<>() {};
    }

    @Override
    protected Class<Progress> getDataBaseItemClass() {
        return Progress.class;
    }
}
