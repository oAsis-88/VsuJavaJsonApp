package com.vsu.ru;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.*;
import java.util.List;

public class PlayerServiceImpl implements PlayerService{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PlayersServers playersServers = new PlayersServers();

    @Override
    public List<Player> readPlayersFromFile(String fileName) throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(fileName);
        return objectMapper.readValue(resourceAsStream, new TypeReference<>() {});
    }

    @SneakyThrows
    @Override
    public void writeToConsole(List<Player> players) {
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(players));
    }

    @SneakyThrows
    @Override
    public void writeToConsole(Player player) {
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(player));
    }

    @SneakyThrows
    @Override
    public void writeToFile(String fileName, Player player) {
        File f = new File(fileName);
        f.createNewFile();
        try(OutputStream outputStream =
                    new FileOutputStream(f)){
            PrintStream ps = new PrintStream(outputStream);
            ps.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(player));
        }
    }

    @SneakyThrows
    @Override
    public void writeToFile(String fileName, List<Player> players) {
        File f = new File(fileName);
        f.createNewFile();
        try(OutputStream outputStream =
                    new FileOutputStream(f)){
            PrintStream ps = new PrintStream(outputStream);
            ps.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(players));
        }
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
