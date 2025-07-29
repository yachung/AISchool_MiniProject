package com.module_core.model;

public class Product {
    private final String name;
    private final String description;
    private final int price;

    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void use(Pokemon target) {
        // 예시: potion 효과 적용
        float healAmount;
        switch (name) {
            case "potion" -> healAmount = 20;
            case "bigpotion" -> healAmount = 50;
            default -> {
                System.out.println("효과 없음");
                return;
            }
        }

        float newHp = Math.min(target.getCurHp() + healAmount, target.getMaxHp());
        target.setCurHp(newHp);
        System.out.println(target.getName() + "의 체력이 " + healAmount + " 회복되어 " + newHp + "/" + target.getMaxHp() + "가 되었습니다.");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
