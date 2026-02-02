package com.example.game_battle_sheep.model;

import java.util.Random;

public class Grid {

    private final int[][] grid = new int[10][10];

    // Tailles des bateaux
    private final int[] ships = {5, 4, 3, 3, 2};

    private int remainingCells;   // nombre total de cases bateau restantes
    private int remainingShips;   // nombre de bateaux non coulés

    public Grid() {
        Random random = new Random();
        remainingCells = 0;
        remainingShips = ships.length;

        for (int shipSize : ships) {
            placeShip(random, shipSize);
            remainingCells += shipSize;
        }
    }

    private void placeShip(Random random, int size) {
        boolean placed = false;

        while (!placed) {
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            boolean horizontal = random.nextBoolean();

            if (canPlaceShip(row, col, size, horizontal)) {
                for (int i = 0; i < size; i++) {
                    if (horizontal) {
                        grid[row][col + i] = size;
                    } else {
                        grid[row + i][col] = size;
                    }
                }
                placed = true;
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (grid[row][col + i] != 0) return false;
            }
        } else {
            if (row + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (grid[row + i][col] != 0) return false;
            }
        }
        return true;
    }

    public ShotResult shoot(int row, int col) {

        int cell = grid[row][col];

        if (cell > 0) {
            grid[row][col] = -1; // touché
            remainingCells--;

            if (isShipSunk(cell)) {
                remainingShips--;
            }

            if (remainingCells == 0) {
                return ShotResult.TOUCHE_COULE;
            }
            return ShotResult.TOUCHE;
        }

        return ShotResult.PLOUF;
    }

    private boolean isShipSunk(int size) {
        for (int[] rows : grid) {
            for (int cell : rows) {
                if (cell == size) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return remainingCells == 0;
    }

    public int getRemainingShips() {
        return remainingShips;
    }
}
