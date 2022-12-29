package com.vsu.ru.console;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.vsu.ru.model.Player;
import com.vsu.ru.service.ModelService;
import com.vsu.ru.service.PlayerServiceImpl;

import java.util.List;

public class ConsoleReader {

    private final static ModelService<Long, Player> playerService = new PlayerServiceImpl();


    public static void read(String[] args){
        Args consoleArgs = new Args();
        JCommander jc = new JCommander();
        jc.addObject(consoleArgs);
        try {
            jc.parse(args);
        }catch (ParameterException parameterException){
            jc.usage();
            return;
        }
        if(consoleArgs.getHelp()){
            jc.usage();
            return;
        }
        switch (consoleArgs.getCrudOperations()) {
            case GET -> {
                if (consoleArgs.getId() == null) {
                    List<Player> allPlayers = playerService.getAll();
                    playerService.writeToConsole(allPlayers);
                } else {
                    Player playerById = playerService.getById(consoleArgs.getId());
                    playerService.writeToConsole(playerById);
                }
            }
            case SAVE -> {
                if (consoleArgs.getInputFile() == null) {
                    System.err.println("input file is required if save crud operation");
                } else {
                    List<Player> playerList = playerService.readListFromFile(consoleArgs.getInputFile());
                    playerService.create(playerList);
                }
            }
            case DELETE -> {
                if (consoleArgs.getId() == null) {
                    playerService.deleteAll();
                } else {
                    playerService.deleteById(consoleArgs.getId());
                }
            }
        }
    }
}
