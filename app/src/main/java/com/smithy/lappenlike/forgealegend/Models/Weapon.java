package com.smithy.lappenlike.forgealegend.Models;

public class Weapon {

    private String name;
    private String type;
    private int damage;

    public Weapon() {}

    public Weapon(String name, String type, int damage) {
        this.name = name;
        this.type = type;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
