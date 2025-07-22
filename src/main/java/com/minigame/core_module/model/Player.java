package com.minigame.core_module.model;

import java.util.List;

public class Player {
	private String name;
	private int money;
	private List<Pokemon> pokemons;
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
