package PacMan_Games.Map;

import java.util.Random;
import java.util.logging.*;

import CustomExceptions.GameExceptions.NoMapsAvailableException;
import Utils.LoggerUtility;

public class MapSelector {

    private static final Logger logger = LoggerUtility.getLogger("MapSelector", "MapSelector.log");

    public static Map selectRandomMap(Maps maps) {
        if (maps.getMapCount() == 0) {
            // Log the error when no maps are available
            logger.log(Level.SEVERE, "No maps available to select from.");
            throw new NoMapsAvailableException("No maps available to select from.");
        }

        Random random = new Random(System.currentTimeMillis());
        int randomIndex = random.nextInt(maps.getMapCount());
        Map selectedMap = maps.getMap(randomIndex);

        // Log the map selection event
        logger.log(Level.INFO, "Randomly selected map: " + selectedMap);

        return selectedMap;
    }

 
}
