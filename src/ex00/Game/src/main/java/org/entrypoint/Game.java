package org.entrypoint;

import org.logic.*;

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
        String filePath = new String();
        if (gameParameters.getProfileMode().equals("production")) {
            filePath = "src/ex00/Game/src/main/resources/application-production.properties";
        }
        ColoredPrinter game = new ColoredPrinter(filePath);
        Field field = new Field(gameParameters.getSize(),
                gameParameters.getEnemiesCount(),
                gameParameters.getWallsCount());
        game.showGame(field.getCells());
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

        public static int getEnemiesCount() {
            return enemiesCount;
        }

        public static int getSize() {
            return size;
        }

        public static int getWallsCount() {
            return wallsCount;
        }

        public String getProfileMode() {
            return profileMode;
        }

        public static void run() {


        }
    }
}
