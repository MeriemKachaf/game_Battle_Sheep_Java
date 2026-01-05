package com.example.game_battle_sheep.model;

import java.util.Random;

public class Grid {

    private Cell[][] cells;
    private Random random;

    public Grid() {
        cells = new Cell[10][10];
        random = new Random();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col] = new Cell();
            }
        }

        // Bateau de test (1 seule case)
        placeTestShip();
    }

    private void placeTestShip() {
        int row = random.nextInt(10);
        int col = random.nextInt(10);
        cells[row][col].setShip(true);
    }

    public ShotResult shoot(int row, int col) {

        Cell cell = cells[row][col];

        if (cell.isShot()) {
            return ShotResult.PLOUF;
        }

        cell.shoot();

        if (cell.hasShip()) {
            return ShotResult.TOUCHE;
        }

        return ShotResult.PLOUF;
    }
}
