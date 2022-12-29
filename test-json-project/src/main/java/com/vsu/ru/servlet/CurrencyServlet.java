package com.vsu.ru.servlet;

import com.vsu.ru.model.Currency;
import com.vsu.ru.service.CurrencyServiceImpl;
import com.vsu.ru.service.ModelService;

public class CurrencyServlet extends ModelServletAbs<Long, Currency>{
    private static final ModelService<Long, Currency> currencyService = new CurrencyServiceImpl();
    @Override
    protected ModelService<Long, Currency> getModelService() {
        return currencyService;
    }
}
