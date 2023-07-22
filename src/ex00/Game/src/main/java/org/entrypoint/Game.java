package org.entrypoint;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class Game {
    public static void main(String[] args) {
        Args gameParameters = new Args();
        JCommander.newBuilder().
                addObject(gameParameters).
                build().
                parse(args);
        ;
        gameParameters.run();
        ColoredPrinter game = new ColoredPrinter();
        ColoredPrinter.Cell[][] example = new ColoredPrinter.Cell[5][5];
        example[0][0] = ColoredPrinter.Cell.EMPTY;
        example[0][1] = ColoredPrinter.Cell.PLAYER;
        example[0][2] = ColoredPrinter.Cell.ENEMY;
        example[0][3] = ColoredPrinter.Cell.EMPTY;
        example[0][4] = ColoredPrinter.Cell.PLAYER;
        example[1][0] = ColoredPrinter.Cell.EMPTY;
        example[1][1] = ColoredPrinter.Cell.PLAYER;
        example[1][2] = ColoredPrinter.Cell.TARGET;
        example[1][3] = ColoredPrinter.Cell.EMPTY;
        example[1][4] = ColoredPrinter.Cell.PLAYER;
        example[2][0] = ColoredPrinter.Cell.EMPTY;
        example[2][1] = ColoredPrinter.Cell.PLAYER;
        example[2][2] = ColoredPrinter.Cell.OBSTACLE;
        example[2][3] = ColoredPrinter.Cell.EMPTY;
        example[2][4] = ColoredPrinter.Cell.PLAYER;
        example[3][0] = ColoredPrinter.Cell.EMPTY;
        example[3][1] = ColoredPrinter.Cell.PLAYER;
        example[3][2] = ColoredPrinter.Cell.OBSTACLE;
        example[3][3] = ColoredPrinter.Cell.EMPTY;
        example[3][4] = ColoredPrinter.Cell.PLAYER;
        example[4][0] = ColoredPrinter.Cell.EMPTY;
        example[4][1] = ColoredPrinter.Cell.PLAYER;
        example[4][2] = ColoredPrinter.Cell.OBSTACLE;
        example[4][3] = ColoredPrinter.Cell.EMPTY;
        example[4][4] = ColoredPrinter.Cell.PLAYER;
        game.showGame(example);
    }

    @Parameters(separators = "=")

    public static class Args {
        @Parameter(names = {"--enemiesCount"})
        private static int enemiesCount;
        @Parameter(names = {"--wallsCount"})
        private static int wallsCount;
        @Parameter(names = {"--size"})
        private static int size;
        @Parameter(names = {"--profile"})
        private static String profileMode;

        public static void run() {
            ShowMaze mazeWindow =
                    new ShowMaze(enemiesCount, wallsCount, size, profileMode);
            mazeWindow.printMaze();


        }
    }
}
