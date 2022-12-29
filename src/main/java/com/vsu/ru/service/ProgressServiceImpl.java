package com.vsu.ru.service;

import com.vsu.ru.model.Progress;
import com.vsu.ru.dao.DataBaseAbstractDao;
import com.vsu.ru.dao.ProgressDao;

public class ProgressServiceImpl extends ModelServiceAbs<Long, Progress>{
    private final ProgressDao progressServers = new ProgressDao();

    @Override
    public Long getIdFromString(String stringId) {
        return Long.valueOf(stringId);
    }

    @Override
    protected DataBaseAbstractDao<Progress, Long> getDataBaseAbstractServers() {
        return progressServers;
    }
}
