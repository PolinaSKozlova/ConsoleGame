package ex00.Game;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class ColoredPrinter {
    private static Attribute enemyColor;
    private static Attribute playerColor;
    private static Attribute wallsColor;
    private static Attribute goalColor;
    private static Attribute emptyColor;

    public static void showGame(Cell[][] field) {
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field[i].length; ++j) {
                int pixel = image.getRGB(j, i);
                if ((pixel & 0x00FFFFFF) == 0) {
                    System.out.print(Ansi.colorize(" ", enemyColor));
                } else {
                    System.out.print(Ansi.colorize(" ", playerColor));
                }
            }
            System.out.println();
        }

    }

    private static Attribute resolveColor(String color) {
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