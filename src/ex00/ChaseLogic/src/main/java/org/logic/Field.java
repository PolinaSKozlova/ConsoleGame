package org.logic;

import java.util.*;

import org.logic.enums.*;



public class Field {
    private final int size;
    private Cell[][] cells;
    private Coordinate player;
    private Coordinate target;

    public List<Coordinate> getEnemies() {
        return enemies;
    }

    private List<Coordinate> enemies;

    public Field(int size, int obstaclesCount, int enemiesCount)
            throws IllegalParametersException {
        if (size < 5) {
            throw new IllegalParametersException(
                    "Field size must be at least 5");
        }
        if (size > 50) {
            throw new IllegalParametersException(
                    "Field size must be less than 50");
        }
        if ((size * size) <= (2 + obstaclesCount + enemiesCount)) {
            throw new IllegalParametersException(
                    "Input data is incorrect: no empty space");
        }

        this.size = size;
        this.cells = new Cell[size][size];
        enemies = new ArrayList<>();
        initializeField();

        do {
            setObject(obstaclesCount, Cell.OBSTACLE);
            setObject(1, Cell.PLAYER);
            setObject(1, Cell.TARGET);
            setObject(enemiesCount, Cell.ENEMY);
        } while (checkPathForPlayer());

    }

    public Cell[][] getCells() {
        return cells;
    }

    public void enemyTurn(Coordinate enemy) {
        Coordinate oldPos = enemy;
        Coordinate newPos = ChaseLogic.enemyStep(this.cells, enemy, player);
        if (newPos == null) {
            return;
        }
        if (newPos.equals(oldPos)) {
            return;
        }
        if (newPos.equals(player)) {
            System.out.println("You lose! =(");
            System.exit(0);
        }
        this.cells[oldPos.getRow()][oldPos.getColumn()] = Cell.EMPTY;
        this.cells[newPos.getRow()][newPos.getColumn()] = Cell.ENEMY;

        this.enemies.remove(oldPos);
        this.enemies.add(newPos);
    }

    public boolean playerTurn(Direction direction) {
        Coordinate newPos = player;
        switch (direction) {
            case LEFT:
                newPos = player.left();
                break;
            case RIGHT:
                newPos = player.right();
                break;
            case UP:
                newPos = player.up();
                break;
            case DOWN:
                newPos = player.down();
                break;
        }

        if (!newPos.isValidRange(0, size)) {
            return false;
        }

        if (this.cells[newPos.getRow()][newPos.getColumn()] == Cell.TARGET) {
            System.out.println("You win! =D");
            System.exit(0);
        }

        if (this.cells[newPos.getRow()][newPos.getColumn()] == Cell.EMPTY) {
            this.cells[player.getRow()][player.getColumn()] = Cell.EMPTY;
            this.cells[newPos.getRow()][newPos.getColumn()] = Cell.PLAYER;
            player = newPos;
            return true;
        }
        return false;
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

    private boolean checkPathForPlayer() {
        Coordinate[] target = {this.target.down(),
                this.target.up(),
                this.target.left(),
                this.target.right()};

        for (Coordinate coordinate : target) {
            List<Coordinate> path =
                    ChaseLogic.findPath(cells, player, coordinate);
            if (path.size() > 1) {
                return true;
            }
        }
        return false;
    }
}
