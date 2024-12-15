package PacMan_Games.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import CustomExceptions.GameExceptions.MapNotFoundException;
import Utils.LoggerUtility;

public class Maps {

    private List<Map> maps;
    private static final Logger logger = LoggerUtility.getLogger("Maps", "Maps.log");

    public Maps() {
        this.maps = new ArrayList<>();
    }

    public Maps(List<Map> maps) {
        this.maps = maps;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }

    public void addMap(Map map) {
        if (map == null) {
            logger.log(Level.WARNING, "Attempted to add a null map.");
            throw new IllegalArgumentException("Map cannot be null");
        }
        this.maps.add(map);
    }

    public Map getMap(int index) {
        try {
            if (index >= 0 && index < maps.size()) {
                return maps.get(index);
            } else {
                throw new MapNotFoundException("Invalid map index: " + index);
            }
        } catch (MapNotFoundException e) {
            logger.log(Level.SEVERE, "Map not found for index: " + index, e);
            throw e;  
        }
    }

    public int getMapCount() {
        return maps.size();
    }

}
