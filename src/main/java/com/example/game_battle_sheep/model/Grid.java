package com.example.game_battle_sheep.model;

import java.util.Random;

public class Grid {

    private Cell[][] cells;
    private int shipsRemaining;
    private final Random random;

    public Grid() {
        cells = new Cell[10][10];
        random = new Random();
        shipsRemaining = 5;

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                cells[r][c] = new Cell();
            }
        }

        placeAllShips();
    }

    private void placeAllShips() {
        placeShip(new Ship(5));
        placeShip(new Ship(4));
        placeShip(new Ship(3));
        placeShip(new Ship(3));
        placeShip(new Ship(2));
    }

    private void placeShip(Ship ship) {
        boolean placed = false;

        while (!placed) {
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            boolean horizontal = random.nextBoolean();

            if (canPlace(ship, row, col, horizontal)) {
                for (int i = 0; i < ship.getSize(); i++) {
                    int r = row + (horizontal ? 0 : i);
                    int c = col + (horizontal ? i : 0);
                    cells[r][c].setShip(ship);
                }
                placed = true;
            }
        }
    }

    private boolean canPlace(Ship ship, int row, int col, boolean horizontal) {
        for (int i = 0; i < ship.getSize(); i++) {
            int r = row + (horizontal ? 0 : i);
            int c = col + (horizontal ? i : 0);

            if (r >= 10 || c >= 10) return false;
            if (cells[r][c].hasShip()) return false;
        }
        return true;
    }

    public ShotResult shoot(int row, int col) {

        Cell cell = cells[row][col];

        if (cell.isShot()) return ShotResult.PLOUF;

        cell.shoot();

        if (cell.hasShip()) {
            Ship ship = cell.getShip();
            ship.hit();

            if (ship.isSunk()) {
                shipsRemaining--;
                return ShotResult.TOUCHE_COULE;
            }
            return ShotResult.TOUCHE;
        }

        return ShotResult.PLOUF;
    }

    public boolean isGameOver() {
        return shipsRemaining == 0;
    }
}
