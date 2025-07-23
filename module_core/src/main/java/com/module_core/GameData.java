package com.module_core;

import com.module_core.model.Player;
import com.module_core.model.Pokemon;
import com.module_core.service.shop.Shop;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    public GameData() {
        this.player = new Player();
        this.shop = new Shop();
        this.pokemons = new ArrayList<Pokemon>();
        // pokemons.add(new Pokemon());
    }

    public Player getPlayer() {
        return player;
    }

    public Shop getShop() {
        return shop;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    private final Player player;
    private final Shop shop;
    private final List<Pokemon> pokemons;
}
