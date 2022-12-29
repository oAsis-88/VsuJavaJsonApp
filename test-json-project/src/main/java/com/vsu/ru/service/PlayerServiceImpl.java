package com.vsu.ru.service;

import com.vsu.ru.model.Player;
import com.vsu.ru.dao.DataBaseAbstractDao;
import com.vsu.ru.dao.PlayersDao;

public class PlayerServiceImpl extends ModelServiceAbs<Long, Player>{
    private final PlayersDao playersServers = new PlayersDao();


    @Override
    public Long getIdFromString(String stringId) {
        return Long.valueOf(stringId);
    }

    @Override
    protected DataBaseAbstractDao<Player, Long> getDataBaseAbstractServers() {
        return playersServers;
    }
}
