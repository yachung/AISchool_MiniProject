package com.module_cli;

import com.module_core.GameData;
import com.module_core.service.state.*;

import java.util.Scanner;

public class GameManager {
    private IGameState currentState;
    private boolean isGameRunning;
    private GameData gameData;
    private Scanner scanner;

    public GameManager()
    {
        this.gameData = new GameData();
        this.scanner = new Scanner(System.in);
    }

    public void gameStart() {
        isGameRunning = true;
        ChangeState(new StartState());
        run();
    }

    public void gameOver() {
        isGameRunning = false;
    }

    private void run()
    {
        while (isGameRunning) {
            System.out.print("> "); // 사용자 입력 대기 프롬프트
            String command = scanner.nextLine();

            IGameState nextState = currentState.processCommand(gameData, command);

            if (nextState != currentState)
                ChangeState(nextState);
        }
    }

    public void ChangeState(IGameState nextState) {
        if (currentState != null)
            currentState.OnStateExit(gameData);

        currentState = nextState;

        currentState.OnStateEnter(gameData);
    }
}
