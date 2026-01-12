package com.example.game_battle_sheep.model;

public class Cell {

    private boolean shot;
    private Ship ship;

    public Cell() {
        shot = false;
        ship = null;
    }

    public boolean hasShip() {
        return ship != null;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isShot() {
        return shot;
    }

    public void shoot() {
        shot = true;
    }
}
