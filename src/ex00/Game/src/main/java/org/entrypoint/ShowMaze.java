package org.entrypoint;

import org.logic.IllegalParametersException;
import org.logic.enums.*;
import org.logic.Coordinate;
import org.logic.Field;

import java.util.Scanner;

public class ShowMaze {
    private Field field;
    private ColoredPrinter fieldPrinter;

    public ShowMaze(int enemiesCount, int wallsCount, int size,
                    String filePath) {
        fieldPrinter = new ColoredPrinter(filePath);
        try {
            field = new Field(size, wallsCount, enemiesCount);
        } catch (IllegalParametersException e) {
            System.out.println(e.getMessage());;
            System.exit(-1);
        }

    }

    public void runGame() {
        fieldPrinter.showGame(field.getCells());
        while (true) {
            playRound();
        }
    }

    private void playRound() {

        Scanner scanner = new Scanner(System.in);
        // ход игрока
        boolean playerMoved = false;
        while (!playerMoved) {
            Direction direction = Direction.NONE;

            // здесь надо считать направление для игрока
            // или 9 чтобы сдаться
            switch (getInput(scanner)) {
                case 'W':
                    direction = Direction.UP;
                    break;
                case 'A':
                    direction = Direction.LEFT;
                    break;
                case 'S':
                    direction = Direction.DOWN;
                    break;
                case 'D':
                    direction = Direction.RIGHT;
                    break;
                case '9':
                    System.out.println("You surrender!");
                    System.exit(0);
                    break;
                default:
                    break;
            }
            playerMoved = field.playerTurn(direction);
        }

        fieldPrinter.showGame(field.getCells());

        // ходы врагов
        for (Coordinate enemy : field.getEnemies()) {
            // для dev режима надо добавить ожидание ввода 8 внутри цикла
            System.out.println("Enemy: " + enemy);
            field.enemyTurn(enemy);
            fieldPrinter.showGame(field.getCells());
        }
    }

    private static char getInput(Scanner scanner) {
        char result = '\0';
        while (result == '\0') {
            String rawInputData = scanner.nextLine().toUpperCase();
            if (rawInputData.length() != 1) continue;
            result = rawInputData.charAt(0);
        }
        return result;
    }
}
