package com.vsu.ru.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vsu.ru.model.Player;
import com.vsu.ru.dao.DataBaseAbstractDao;
import com.vsu.ru.dao.PlayersDao;

import java.util.List;

public class PlayerServiceImpl extends ModelServiceAbs<Long, Player>{
    private final PlayersDao playersServers = new PlayersDao();


    @Override
    public Long getIdFromString(String stringId) {
        return Long.valueOf(stringId);
    }

    @Override
    protected DataBaseAbstractDao<Player, Long> getDataBaseAbstractDao() {
        return playersServers;
    }

    @Override
    protected TypeReference<List<Player>> getTypeReference() {
        return new TypeReference<>() {};
    }

    @Override
    protected Class<Player> getDataBaseItemClass() {
        return Player.class;
    }
}
