package com.example.game_battle_sheep.model;

public class Cell {

    private boolean hasShip;
    private boolean shot;

    public Cell() {
        this.hasShip = false;
        this.shot = false;
    }

    public boolean hasShip()  {
        return hasShip;
    }

    public void setShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public boolean isShot() {
        return shot;
    }

    public void shoot() {
        this.shot = true;
    }
}
