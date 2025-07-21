package com.minigame.core_module.service.state;

import com.minigame.core_module.GameData;

public interface IGameState {
    void OnStateEnter(GameData gameData);
    IGameState processCommand(GameData gameData, String command);
    void OnStateExit(GameData gameData);
}
