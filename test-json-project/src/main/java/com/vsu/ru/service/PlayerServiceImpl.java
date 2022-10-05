package com.vsu.ru.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsu.ru.database.PlayersServers;
import com.vsu.ru.model.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PlayerServiceImpl implements PlayerService{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PlayersServers playersServers = new PlayersServers();

    @Override
    public List<Player> readPlayersFromFile(String fileName) throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(fileName);
        return objectMapper.readValue(resourceAsStream, new TypeReference<>() {});
    }

    @Override
    public void createPlayers(List<Player> players) {
        playersServers.saveOrUpdateAll(players);
    }

    @Override
    public List<Player> readPlayers() {
        return playersServers.readAll();
    }

    @Override
    public void deleteAll() {
        playersServers.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        playersServers.delete(id);
    }

    @Override
    public Player findById(Long id) {
        return playersServers.read(id);
    }
}
