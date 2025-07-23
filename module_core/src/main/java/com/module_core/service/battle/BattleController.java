package com.module_core.service.battle;

import com.module_core.model.Player;
import com.module_core.model.Pokemon;

import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

public class BattleController {
// 랜덤으로 상대 포켓몬 고르기
// 무조건 나부터 시작(일단)
// mypoke에서 현재 상태 기술들 4개 list가져와서
// 선택하기 (1,2,3,4 중 입력받게)
// 상대 포켓몬에게 해당 기술 적용 -> enemy.hp -= 내가선택한 공격 만큼 적용
// 상대 턴에서는 해당 포켓몬의 skill에서 random으로 선택해서
// mypoke.hp -= enemy.skill 적용.
// 이것을 enemy또는 mypoke의 hp가 0 이하가 될 때 까지 진행.
// 만약 상대의 hp가 0이라면 돈 획득
// 내가 0이라면 null반환


    private final Player player;
    private final Supplier<Pokemon> Pokemon;
    private Pokemon enemy;
    private final Random rng = new Random(); // 사용자 입력
    private final Scanner scanner = new Scanner(System.in);


    public BattleController(Player player, Supplier<Pokemon> Pokemon) {
        this.player = player;
        this.Pokemon = Pokemon;
    }

    public Player battle() {
        // 1.전투시작
        enemy = Pokemon.get();
        System.out.println("야생의 " + enemy.getName() + "이(가) 나타났다!");

        // 2. 배틀루프
        while (true) {
            // ---------- Player turn ----------
            //takePlayerTurn();

            if (enemy.getCurHp() <= 0) {
                System.out.println(enemy.getName() + "가 기절했다!");
                //rewardPlayer();
                return player; // victory
            }

            // ---------- Enemy turn ----------
            //takeEnemyTurn();

            //if (player.getMyPokemon().getCurHp() <= 0) {
                //System.out.println(player.getMyPokemon().getName() + "가 기절했다!");
                return null; // defeat
            }
        }
    }