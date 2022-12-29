package com.vsu.ru.service;

import com.vsu.ru.model.Item;
import com.vsu.ru.dao.DataBaseAbstractDao;
import com.vsu.ru.dao.ItemsDao;

public class ItemServiceImpl extends ModelServiceAbs<Long, Item>{
    private final ItemsDao itemsServers = new ItemsDao();

    @Override
    public Long getIdFromString(String stringId) {
        return Long.valueOf(stringId);
    }

    @Override
    protected DataBaseAbstractDao<Item, Long> getDataBaseAbstractServers() {
        return itemsServers;
    }
}
