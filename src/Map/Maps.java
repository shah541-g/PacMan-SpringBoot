package Map;


import java.util.ArrayList;
import java.util.List;

public class Maps {

    private List<Map> maps;

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
        this.maps.add(map);
    }

    public Map getMap(int index) {
        if (index >= 0 && index < maps.size()) {
            return maps.get(index);
        }
        throw new IndexOutOfBoundsException("Invalid map index");
    }

    public int getMapCount() {
        return maps.size();
    }
}
