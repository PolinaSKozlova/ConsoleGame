package org.entrypoint;

import org.logic.IllegalParametersException;
import org.logic.enums.*;
import org.logic.Field;
import org.entrypoint.Game.Args;

import java.util.Scanner;

public class ShowMaze {
    private Field field;
    private ColoredPrinter fieldPrinter;
    private String mode;

    public ShowMaze(int enemiesCount, int wallsCount, int size,
                    String filePath,String profileMode) {
        fieldPrinter = new ColoredPrinter(filePath);
        mode = profileMode;
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

        boolean playerMoved = false;
        while (!playerMoved) {
            Direction direction = Direction.NONE;

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
        clearConsole();
        fieldPrinter.showGame(field.getCells());

        for (int i = 0; i < field.getEnemies().size(); ++i) {

            if(mode.equals("development")){
                while (getInput(scanner) != '8') {
                }
            }

            field.enemyTurn(field.getEnemies().poll());
            clearConsole();
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
    private static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
