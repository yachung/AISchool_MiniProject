package com.minigame.core_module.service.state;


import com.minigame.core_module.GameData;

public class MapState implements IGameState {
    @Override
    public void OnStateEnter(GameData gameData) {
        System.out.println("MapState OnStateEnter");
    }

    @Override
    public IGameState processCommand(GameData gameData, String command) {
        switch (command) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
        }

        return this;
    }

    @Override
    public void OnStateExit(GameData gameData) {

    }
}
