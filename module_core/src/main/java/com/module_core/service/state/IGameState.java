package com.module_core.service.state;

import com.module_core.GameData;

public interface IGameState {
    void OnStateEnter(GameData gameData);
    IGameState processCommand(GameData gameData, String command);
    void OnStateExit(GameData gameData);
}
