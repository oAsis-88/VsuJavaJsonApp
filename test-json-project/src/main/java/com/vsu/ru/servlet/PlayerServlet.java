package com.vsu.ru.servlet;

import com.vsu.ru.model.Player;
import com.vsu.ru.service.ModelService;
import com.vsu.ru.service.PlayerServiceImpl;


public class PlayerServlet extends ModelServletAbs<Long, Player> {
    private static final ModelService<Long, Player> playerService = new PlayerServiceImpl();

    @Override
    protected ModelService<Long, Player> getModelService() {
        return playerService;
    }
}
