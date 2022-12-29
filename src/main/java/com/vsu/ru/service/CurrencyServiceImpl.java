package com.vsu.ru.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vsu.ru.model.Currency;
import com.vsu.ru.dao.CurrencyDao;
import com.vsu.ru.dao.DataBaseAbstractDao;

import java.util.List;

public class CurrencyServiceImpl extends ModelServiceAbs<Long, Currency>{
    private final CurrencyDao currencyDao = new CurrencyDao();

    @Override
    public Long getIdFromString(String stringId) {
        return Long.valueOf(stringId);
    }

    @Override
    protected DataBaseAbstractDao<Currency, Long> getDataBaseAbstractDao() {
        return currencyDao;
    }

    @Override
    protected TypeReference<List<Currency>> getTypeReference() {
        return new TypeReference<>() {};
    }

    @Override
    protected Class<Currency> getDataBaseItemClass() {
        return Currency.class;
    }
}
