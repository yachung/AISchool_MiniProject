package com.module_core.service.shop;

import com.module_core.model.Player;
import com.module_core.model.Pokemon;
import com.module_core.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Shop {

    private final Map<String, Product> productList;
    private final Scanner scanner;

    public Shop() {
        this.productList = new HashMap<>();
        this.scanner = new Scanner(System.in);

        // 상품 초기화 예시
        productList.put("potion", new Product("potion", "HP 20 회복", 100));
        productList.put("bigpotion", new Product("bigpotion", "HP 50 회복", 250));
    }

    public void buy(Player player) {
        System.out.println("=== 상점 ===");
        for (Map.Entry<String, Product> entry : productList.entrySet()) {
            Product p = entry.getValue();
            System.out.println("- " + p.getName() + ": " + p.getDescription() + " (" + p.getPrice() + "원)");
        }

        System.out.print("구매할 아이템 이름 입력 (또는 'exit' 입력): ");
        String input = scanner.nextLine();

        if (input.equals("exit")) {
            return;
        }

        Product selected = productList.get(input);
        if (selected == null) {
            System.out.println("존재하지 않는 아이템입니다.");
            return;
        }

        if (player.getMoney() < selected.getPrice()) {
            System.out.println("돈이 부족합니다.");
            return;
        }

        List<Pokemon> pokemons = player.getPokemons();
        if (pokemons == null || pokemons.isEmpty()) {
            System.out.println("플레이어가 포켓몬을 가지고 있지 않습니다.");
            return;
        }

        System.out.println("회복할 포켓몬을 선택하세요:");
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon p = pokemons.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (" + p.getCurHp() + "/" + p.getMaxHp() + ")");
        }

        System.out.print("선택: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if (choice < 1 || choice > pokemons.size()) {
            System.out.println("잘못된 선택입니다.");
            return;
        }

        Pokemon target = pokemons.get(choice - 1);
        player.setMoney(player.getMoney() - selected.getPrice());
        selected.use(target);

        System.out.println(target.getName() + "에게 " + selected.getName() + "을 사용했습니다!");
    }
}
