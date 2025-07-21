package com.minigame.service.state;

import com.minigame.main.GameData;

public interface IGameState {
    void OnStateEnter(GameData gameData);
    IGameState processCommand(GameData gameData, String command);
    void OnStateExit(GameData gameData);
}
