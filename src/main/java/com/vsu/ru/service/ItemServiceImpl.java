package com.vsu.ru.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vsu.ru.model.Item;
import com.vsu.ru.dao.DataBaseAbstractDao;
import com.vsu.ru.dao.ItemsDao;

import java.util.List;

public class ItemServiceImpl extends ModelServiceAbs<Long, Item>{
    private final ItemsDao itemsServers = new ItemsDao();

    @Override
    public Long getIdFromString(String stringId) {
        return Long.valueOf(stringId);
    }

    @Override
    protected DataBaseAbstractDao<Item, Long> getDataBaseAbstractDao() {
        return itemsServers;
    }

    @Override
    protected TypeReference<List<Item>> getTypeReference() {
        return new TypeReference<>() {};
    }

    @Override
    protected Class<Item> getDataBaseItemClass() {
        return Item.class;
    }
}
