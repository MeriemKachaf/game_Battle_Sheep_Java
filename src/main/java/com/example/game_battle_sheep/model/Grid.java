package com.example.game_battle_sheep.model;

import java.util.Random;

public class Grid {

    private final int[][] grid = new int[10][10];
    private int shipsRemaining = 5;

    public Grid() {
        Random random = new Random();
        int placed = 0;

        while (placed < 5) {
            int r = random.nextInt(10);
            int c = random.nextInt(10);

            if (grid[r][c] == 0) {
                grid[r][c] = 1; // bateau
                placed++;
            }
        }
    }

    public ShotResult shoot(int row, int col) {
        if (grid[row][col] == 1) {
            grid[row][col] = 2;
            shipsRemaining--;
            return shipsRemaining == 0
                    ? ShotResult.TOUCHE_COULE
                    : ShotResult.TOUCHE;
        }
        return ShotResult.PLOUF;
    }

    public boolean isGameOver() {
        return shipsRemaining == 0;
    }
}
