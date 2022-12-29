package com.vsu.ru.servlet;

import com.vsu.ru.model.Progress;
import com.vsu.ru.service.ModelService;
import com.vsu.ru.service.ProgressServiceImpl;

public class ProgressServlet extends ModelServletAbs<Long, Progress>{
    private static final ModelService<Long, Progress> progress = new ProgressServiceImpl();
    @Override
    protected ModelService<Long, Progress> getModelService() {
        return progress;
    }
}
