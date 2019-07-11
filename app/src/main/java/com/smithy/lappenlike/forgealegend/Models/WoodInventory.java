package com.smithy.lappenlike.forgealegend.Models;

public class WoodInventory {

    private int cedar;
    private int lark;
    private int pine;
    private int mahogany;
    private int oak;
    private int beech;

    private int cedarBark;
    private int larkBark;
    private int pineBark;
    private int mahoganyBark;
    private int oakBark;
    private int beechBark;


    public WoodInventory() { }

    public WoodInventory(int cedar, int lark, int pine, int mahogany, int oak, int beech, int cedarBark, int larkBark, int pineBark, int mahoganyBark, int oakBark, int beechBark) {
        this.cedar = cedar;
        this.lark = lark;
        this.pine = pine;
        this.mahogany = mahogany;
        this.oak = oak;
        this.beech = beech;
        this.cedarBark = cedarBark;
        this.larkBark = larkBark;
        this.pineBark = pineBark;
        this.mahoganyBark = mahoganyBark;
        this.oakBark = oakBark;
        this.beechBark = beechBark;
    }

    public int getCedar() {
        return cedar;
    }

    public void setCedar(int cedar) {
        this.cedar = cedar;
    }

    public int getLark() {
        return lark;
    }

    public void setLark(int lark) {
        this.lark = lark;
    }

    public int getPine() {
        return pine;
    }

    public void setPine(int pine) {
        this.pine = pine;
    }

    public int getMahogany() {
        return mahogany;
    }

    public void setMahogany(int mahogany) {
        this.mahogany = mahogany;
    }

    public int getOak() {
        return oak;
    }

    public void setOak(int oak) {
        this.oak = oak;
    }

    public int getBeech() {
        return beech;
    }

    public void setBeech(int beech) {
        this.beech = beech;
    }

    public int getCedarBark() {
        return cedarBark;
    }

    public void setCedarBark(int cedarBark) {
        this.cedarBark = cedarBark;
    }

    public int getLarkBark() {
        return larkBark;
    }

    public void setLarkBark(int larkBark) {
        this.larkBark = larkBark;
    }

    public int getPineBark() {
        return pineBark;
    }

    public void setPineBark(int pineBark) {
        this.pineBark = pineBark;
    }

    public int getMahoganyBark() {
        return mahoganyBark;
    }

    public void setMahoganyBark(int mahoganyBark) {
        this.mahoganyBark = mahoganyBark;
    }

    public int getOakBark() {
        return oakBark;
    }

    public void setOakBark(int oakBark) {
        this.oakBark = oakBark;
    }

    public int getBeechBark() {
        return beechBark;
    }

    public void setBeechBark(int beechBark) {
        this.beechBark = beechBark;
    }
}
