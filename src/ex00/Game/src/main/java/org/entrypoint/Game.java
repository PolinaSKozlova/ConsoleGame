package src.main.java.org.entrypoint;

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
