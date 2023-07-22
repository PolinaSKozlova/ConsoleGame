package org.entrypoint;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser {
    private String enemySymbol;
    private String playerSymbol;
    private String wallSymbol;
    private String goalSymbol;
    private String emptySymbol;
    private String enemyColor;
    private String playerColor;
    private String wallColor;
    private String goalColor;
    private String emptyColor;

    public void getValues() {
        System.out.println("enemy_symbol " + enemySymbol);
        System.out.println("player_symbol " + playerSymbol);
        System.out.println("wall_symbol " + wallSymbol);
        System.out.println("goal_symbol " + goalSymbol);
        System.out.println("empty_symbol " + emptySymbol);
        System.out.println("enemy_color " + enemyColor);
        System.out.println("player_color " + playerColor);
        System.out.println("wall_color " + wallColor);
        System.out.println("goal_color " + goalColor);
        System.out.println("empty_color " + emptyColor);
    }

    public String getEmptySymbol() {
        return emptySymbol;
    }

    public String getEnemySymbol() {
        return enemySymbol;
    }

    public String getGoalSymbol() {
        return goalSymbol;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public String getWallSymbol() {
        return wallSymbol;
    }

    public String getEnemyColor() {
        return enemyColor;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getGoalColor() {
        return goalColor;
    }

    public String getEmptyColor() {
        return emptyColor;
    }

    public String getWallColor() {
        return wallColor;
    }

    public void getProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream
                ("/Users/verenach/IdeaProjects/Java_Bootcamp.Team00-1/src/ex00/Game/src/main/resources/application-production.properties")) {
            properties.load(input);
            enemySymbol = properties.getProperty("enemy.char").trim();
            playerSymbol = properties.getProperty("player.char").trim();
            wallSymbol = properties.getProperty("wall.char").trim();
            goalSymbol = properties.getProperty("goal.char").trim();
            if (!properties.getProperty("empty.char").equals("")) {
                emptySymbol = properties.getProperty("empty.char").trim();
            } else {
                emptySymbol = " ";
            }
            enemyColor = properties.getProperty("enemy.color").trim();
            playerColor = properties.getProperty("player.color").trim();
            wallColor = properties.getProperty("wall.color").trim();
            goalColor = properties.getProperty("goal.color").trim();
            emptyColor = properties.getProperty("empty.color").trim();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
