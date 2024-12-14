package Map;

import java.util.Random;

public class MapSelector {

    public static Map selectRandomMap(Maps maps) {
        if (maps.getMapCount() == 0) {
            throw new IllegalStateException("No maps available to select from.");
        }
        // System.out.println("Hye");
        Random random = new Random(System.currentTimeMillis());

        int randomIndex = random.nextInt(maps.getMapCount());
        return maps.getMap(randomIndex);
    }
}
