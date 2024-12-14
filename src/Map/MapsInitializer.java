package Map;

import java.util.List;

public class MapsInitializer {

    public static void initializeMultiplayerMaps(Maps maps) {
       
        maps.addMap(new Map( new String[] {
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
        }));

        maps.addMap(new Map(new String[]{
            "OOXXXX XXXXX XXXXOO",
            "OX   X       X   XO",
            "XX X X XXXXX X X XX",
            "X       XXX     P X",
            "X XX X XrOoX X XX X",
            "X   X    Q    X   X",
            "X XX X X X X X XX X",
            "X        X        X",
            "XX X X XXXXX X X XX",
            "OX p X       X b XO",
            "OOXXXX XXXXX XXXXOO"
        }));

        maps.addMap(new Map(new String[]{
            "OOXXXX XXXXX XXXXOO",
            "OX  X         X  XO",
            "XX X X  X X  X X XX",
            "X      XX XX      X",
            "X X  XbX   XpX  X X",
            "X X Xr X Q X oX X X",
            "X X X XX   XX X X X",
            "X      XX XX      X",
            "XX X X  X X  X X XX",
            "OX  X    P    X  XO",
            "OOXXXX XXXXX XXXXOO"
        }));

    }


    
    public static void initializeSinglePlayerMaps(Maps maps) {
       
        maps.addMap(new Map( new String[] {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX" 
        }));

        maps.addMap(new Map(new String[]{
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXXX XXXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X                 X",
            "XXXX XXXX  XXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XX XX X XXXX",
            "OOOX X       X XOOO",
            "XXXX XXXX  XXX XXXX ",
            "X                 X",
            "X XX XXXX XXXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
        }));

        maps.addMap(new Map(new String[]{
            "XXXXXXXXXXXXXXXXXXX",
            "X                 X",
            "X XXX XXX XXX XXX X",
            "X                 X",
            "X XXX XXXXXXX XXX X",
            "X    X       X    X",
            "XXXX X XX XX X XXXX",
            "OOOX X  rpb  X XOOO",
            "XXXX X XXoXX X XXXX",
            "O                 O",
            "XXXX   XX XX   XXXX",
            "OOOX           XOOO",
            "XXXX X XX XX X XXXX",
            "X    X       X    X",
            "X    X       X    X",
            "X    X   P   X    X",
            "X XXX XXXXXXX XXX X",
            "X                 X",
            "X XXX XXX XXX XXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
        }));

    }

    public static void initializeCustomMaps(List<String[]> customMapData, Maps maps) {
        
        for (String[] mapData : customMapData) {
            maps.addMap(new Map(mapData));
        }

    }
}
