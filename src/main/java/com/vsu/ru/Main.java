package com.vsu.ru;

import com.vsu.ru.config.ServerConfig;
import com.vsu.ru.console.ConsoleReader;

public class Main {

    public static void main(String[] args) throws Exception {
       if(args.length == 0){
           ServerConfig.initServer();
       }else{
           ConsoleReader.read(args);
       }
    }
}