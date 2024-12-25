package game.pacmanGames.map;

public class Map {

    private String[] map;

    // Default constructor
    public Map() {
        this.map = new String[] {
            "OOXXXX XXXXX XXXXOO",
            "OX         Q     XO",
            "XX  XX  XXX  XX  XX",
            "X   XX       XX   X",
            "X X    X b X    X X",
            "X X    XropX    X X",
            "X X    XX XX    X X",
            "X   XX       XX   X",
            "XX  XX  XXX  XX  XX",
            "OX         P     XO",
            "OOXXXX XXXXX XXXXOO"
        };
    }

    public Map(String[] map) {
        this.map = map;
    }

    public String[] getMap() {
        return map;
    }

    public void setMap(String[] map) {
        this.map = map;
    }
}
