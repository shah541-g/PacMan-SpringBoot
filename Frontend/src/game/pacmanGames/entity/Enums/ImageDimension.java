package game.pacmanGames.entity.Enums;


public enum ImageDimension {
    PACMAN(32, 32),
    GHOST(32, 32),
    WALL(32, 32),
    FOOD(4, 4);

    private final int width;  
    private final int height; 


    ImageDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }
}

