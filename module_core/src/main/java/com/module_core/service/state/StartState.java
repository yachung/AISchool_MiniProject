package com.module_core.service.state;

import com.module_core.GameData;

public class StartState implements IGameState {

    public enum ProcessStep {
        SET_NAME,
        SET_STARTING,
        EXIT,
    }

    private ProcessStep currentStep;

    public StartState() {
        currentStep = ProcessStep.SET_NAME;
    }

    @Override
    public void OnStateEnter(GameData gameData) {
        System.out.println("Entered StartState");

        displayPrompt();
    }

    /// 들어온 입력을 처리
    /// 다음 스텝이 있다면 다음 스텝으로 변경하고 다음 스텝에 대한 내용을 콘솔에 출력하고 this 리턴
    /// 없으면 다음 상태 리턴
    @Override
    public IGameState processCommand(GameData gameData, String command) {
        IGameState nextState;

        switch (currentStep) {
            case SET_NAME -> nextState = setName(gameData, command);
            case SET_STARTING -> nextState = setStarting(gameData, command);
            default -> {
                System.out.println("Invalid Command");
                nextState = this;
            }
        }

        displayPrompt();

        return nextState;
    }

    @Override
    public void OnStateExit(GameData gameData) {

    }



    private IGameState setName(GameData gameData, String command) {
        gameData.getPlayer().setName(command);
        currentStep = ProcessStep.SET_STARTING;
        return this;
    }

    private IGameState setStarting(GameData gameData, String command) {
        System.out.println("Entered SetStartingIndex 1 2 3");
        switch (command) {
            case "1":
                System.out.println("1번 선택");
                break;
            case "2":
                System.out.println("2번 선택");
                break;
            case "3":
                System.out.println("3번 선택");
                break;
            default:
                System.out.println("Invalid Command");
                displayPrompt();
                return this;
        }

        currentStep = ProcessStep.EXIT;
        return new MapState();
    }

    public void displayPrompt() {
        switch (currentStep) {
            case SET_NAME -> System.out.println("Enter Name:");
            case SET_STARTING -> System.out.println("Enter StartingIndex:");
            case EXIT -> System.out.println("Exit");
        }
    }
}
