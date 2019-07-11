package com.smithy.lappenlike.forgealegend.Models;

public class Fire {

    private int bark = 0;
    private int wood = 0;
    private int lifetime = 0;

    public Fire() {
    }

    public Fire(int bark, int wood, int lifetime) {
        this.bark = bark;
        this.wood = wood;
        this.lifetime = lifetime;

    }

    public int getBark() {
        return bark;
    }

    public void setBark(int bark) {
        this.bark = bark;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}
