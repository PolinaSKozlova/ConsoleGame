package src.main.java.org.entrypoint;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class ColoredPrinter {

    public enum Cell {
        EMPTY,
        PLAYER,
        ENEMY,
        TARGET,
        OBSTACLE
    }

    private PropertiesParser propertiesParser;
    private Attribute enemyColor;
    private Attribute playerColor;
    private Attribute wallsColor;
    private Attribute goalColor;
    private Attribute emptyColor;

    public void showGame(Cell[][] field) {
        fillAttributes();
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field[i].length; ++j) {
                Cell pixel = field[i][j];
                switch (pixel) {
                    case EMPTY:
                        System.out.print(Ansi.colorize(
                                propertiesParser.getEmptySymbol(),
                                emptyColor));
                        break;
                    case OBSTACLE:
                        System.out.print(Ansi.colorize(
                                propertiesParser.getWallSymbol(),
                                wallsColor));
                        break;
                    case PLAYER:
                        System.out.print(Ansi.colorize(
                                propertiesParser.getPlayerSymbol(),
                                playerColor));
                        break;
                    case TARGET:
                        System.out.print(Ansi.colorize(
                                propertiesParser.getGoalSymbol(), goalColor));
                        break;
                    case ENEMY:
                        System.out.print(Ansi.colorize(
                                propertiesParser.getEnemySymbol(), enemyColor));
                        break;
                }
            }
            System.out.println();
        }
    }

    public void fillAttributes() {
        propertiesParser.getProperties();
        enemyColor = resolveColor(propertiesParser.getEnemyColor());
        playerColor = resolveColor(propertiesParser.getPlayerColor());
        wallsColor = resolveColor(propertiesParser.getWallColor());
        goalColor = resolveColor(propertiesParser.getGoalColor());
        emptyColor = resolveColor(propertiesParser.getEmptyColor());
    }

    private Attribute resolveColor(String color) {
        switch (color) {
            case "RED":
                return Attribute.RED_BACK();
            case "GREEN":
                return Attribute.GREEN_BACK();
            case "BLUE":
                return Attribute.BLUE_BACK();
            case "BLACK":
                return Attribute.BLACK_BACK();
            case "MAGENTA":
                return Attribute.MAGENTA_BACK();
            case "YELLOW":
                return Attribute.YELLOW_BACK();
            case "BRIGHT_RED":
                return Attribute.BRIGHT_RED_BACK();
            case "BRIGHT_GREEN":
                return Attribute.BRIGHT_GREEN_BACK();
            case "BRIGHT_BLUE":
                return Attribute.BRIGHT_BLUE_BACK();
            case "BRIGHT_BLACK":
                return Attribute.BRIGHT_BLACK_BACK();
            case "BRIGHT_WHITE":
                return Attribute.BRIGHT_WHITE_BACK();
            default:
                return Attribute.WHITE_BACK();
        }
    }
}