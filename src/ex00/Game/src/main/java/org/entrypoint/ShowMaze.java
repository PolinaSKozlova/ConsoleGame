package src.main.java.org.entrypoint;

public class ShowMaze {
    private int enemiesCount;
    private int wallsCount;
    private int size;
    private String profileMode;

    public ShowMaze(int enemiesCount,
                    int wallsCount,
                    int size,
                    String profileMode) {
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
        this.size = size;
        this.profileMode = profileMode;
    }

    public void printMaze() {
        System.out.println("enemiesCount " + enemiesCount);
        System.out.println("wallsCount " + wallsCount);
        System.out.println("size " + size);
        System.out.println("profileMode " + profileMode);
    }


}
