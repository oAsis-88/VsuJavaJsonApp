package com.vsu.ru.service;

import com.vsu.ru.model.Currency;
import com.vsu.ru.dao.CurrencyDao;
import com.vsu.ru.dao.DataBaseAbstractDao;

public class CurrencyServiceImpl extends ModelServiceAbs<Long, Currency>{
    private final CurrencyDao currencyDao = new CurrencyDao();

    @Override
    public Long getIdFromString(String stringId) {
        return Long.valueOf(stringId);
    }

    @Override
    protected DataBaseAbstractDao<Currency, Long> getDataBaseAbstractServers() {
        return currencyDao;
    }
}
