package com.vsu.ru.servlet;

import com.vsu.ru.model.Item;
import com.vsu.ru.service.ItemServiceImpl;
import com.vsu.ru.service.ModelService;


public class ItemServlet extends ModelServletAbs<Long, Item>{
    private static final ModelService<Long, Item> itemService = new ItemServiceImpl();
    @Override
    protected ModelService<Long, Item> getModelService() {
        return itemService;
    }
}
