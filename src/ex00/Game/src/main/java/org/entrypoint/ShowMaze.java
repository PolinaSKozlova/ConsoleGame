package org.entrypoint;

import org.entrypoint.enums.Direction;

public class ShowMaze {
    private Field field;
    private ColoredPrinter fieldPrinter;

    public ShowMaze(int enemiesCount, int wallsCount, int size,
                    String filePath) {
        fieldPrinter = new ColoredPrinter(filePath);
        field = new Field(size, wallsCount, enemiesCount);
    }

    public void runGame(){
        fieldPrinter.showGame(field.getCells());
        while(true){
            playRound();
        }
    }

    private void playRound() {
        // ход игрока
        boolean playerMoved = false;
        while (!playerMoved) {
            // здесь надо считать направление для игрока
            // или 9 чтобы сдаться
            Direction direction = Direction.RIGHT;
            playerMoved = field.playerTurn(direction);
        }

        fieldPrinter.showGame(field.getCells());
        // ходы врагов
        for (Coordinate enemy : field.getEnemies()) {
            // для dev режима надо добавить ожидание ввода 8 внутри цикла
            field.enemyTurn(enemy);
            fieldPrinter.showGame(field.getCells());
        }
    }
}
