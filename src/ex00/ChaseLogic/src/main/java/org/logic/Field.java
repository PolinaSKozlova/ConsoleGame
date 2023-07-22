package org.logic;

import java.util.*;

public class Field {
    private final int size;
    private final int obstaclesCount;
    private final int enemiesCount;
    private Cell[][] cells;
    private Coordinate player;
    private Coordinate target;
    private List<Coordinate> enemies;

    public Field(int size, int obstaclesCount, int enemiesCount) {
        if (size < 5) {
            throw new IllegalArgumentException(
                    "Field size must be at least 5");
        }
        if (size > 50) {
            throw new IllegalArgumentException(
                    "Field size must be less than 50");
        }
        if ((size * size) <= (2 + obstaclesCount + enemiesCount)) {
            throw new IllegalArgumentException(
                    "Input data is incorrect: no free space");
        }

        this.size = size;
        this.obstaclesCount = obstaclesCount;
        this.enemiesCount = enemiesCount;

        this.cells = new Cell[size][size];

        initializeField();

        do {
            setObject(obstaclesCount, Cell.OBSTACLE);
            setObject(1, Cell.PLAYER);
            setObject(1, Cell.TARGET);
            setObject(enemiesCount, Cell.ENEMY);
        } while (checkPathForPlayer(player, target));

    }

    public Cell[][] getCells() {
        return cells;
    }

    private void initializeField() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                cells[i][j] = Cell.EMPTY;
            }
        }
    }

    private void setObject(int objectCount, Cell objectType) {
        for (int i = 0; i < objectCount; ) {
            int x = new Random().nextInt(this.size);
            int y = new Random().nextInt(this.size);
            if (cells[x][y] == Cell.EMPTY) {
                cells[x][y] = objectType;
                ++i;
                switch (objectType) {
                    case PLAYER:
                        player = new Coordinate(x, y);
                        break;
                    case TARGET:
                        target = new Coordinate(x, y);
                        break;
                    case ENEMY:
                        enemies.add(new Coordinate(x, y));
                        break;
                }
            }
        }
    }

    private boolean checkPathForPlayer(Coordinate start,
                                       Coordinate end) {
        return ChaseLogic.findPath(this.cells, start, end) != null;
    }

}
