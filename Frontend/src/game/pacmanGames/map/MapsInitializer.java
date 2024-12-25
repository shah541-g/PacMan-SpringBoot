package game.pacmanGames.map;

import java.util.List;
import java.util.logging.*;

import customExceptions.gameExceptions.InvalidMapDataException;
import customExceptions.gameExceptions.MapInitializationException;
import utils.loggingUtilities.LoggerUtility;

public class MapsInitializer {

    private static final Logger logger = LoggerUtility.getLogger("MapsInitializer", "MapsInitializer.log");

    public static void initializeMultiplayerMaps(Maps maps) {
        try {
            maps.addMap(new Map(new String[] {
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
            // Log the successful initialization of the map
            logger.log(Level.INFO, "Multiplayer map 1 initialized successfully.");

            maps.addMap(new Map(new String[] {
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
            // Log the successful initialization of the map
            logger.log(Level.INFO, "Multiplayer map 2 initialized successfully.");

            maps.addMap(new Map(new String[] {
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
            // Log the successful initialization of the map
            logger.log(Level.INFO, "Multiplayer map 3 initialized successfully.");

        } catch (Exception e) {
            // Log any errors encountered during map initialization
            logger.log(Level.SEVERE, "Error initializing multiplayer maps: " + e.getMessage(), e);
            throw new MapInitializationException("Error initializing multiplayer maps", e);
        }
    }

    public static void initializeSinglePlayerMaps(Maps maps) {
        try {
            maps.addMap(new Map(new String[] {
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
            // Log the successful initialization of the map
            logger.log(Level.INFO, "Single player map 1 initialized successfully.");

            maps.addMap(new Map(new String[] {
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
            // Log the successful initialization of the map
            logger.log(Level.INFO, "Single player map 2 initialized successfully.");

            maps.addMap(new Map(new String[] {
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
            
            logger.log(Level.INFO, "Single player map 3 initialized successfully.");

        } catch (Exception e) {

            logger.log(Level.SEVERE, "Error initializing single player maps: " + e.getMessage(), e);
            throw new MapInitializationException("Error initializing single player maps", e);
        }
    }

    public static void initializeCustomMaps(List<String[]> customMapData, Maps maps) {
        try {
            if (customMapData == null || customMapData.isEmpty()) {
                logger.log(Level.WARNING, "Custom map data is empty or null.");
                throw new InvalidMapDataException("Custom map data cannot be null or empty.");
            }

            for (String[] mapData : customMapData) {
                maps.addMap(new Map(mapData));
            }

            // Log the successful initialization of custom maps
            logger.log(Level.INFO, "Custom maps initialized successfully.");

        } catch (Exception e) {
            // Log any errors encountered during custom map initialization
            logger.log(Level.SEVERE, "Error initializing custom maps: " + e.getMessage(), e);
            throw new MapInitializationException("Error initializing custom maps", e);
        }
    }

   
}
