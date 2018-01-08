package pl.edu.osp;

import java.util.Random;

public class MapWorld {
    public Tile[][] map;
    final int lon;
    final int lat;
    public MapWorld(int lon, int lat) {
        this.lon = lon;
        this.lat = lat;
        map = new Tile[lon][lat];
        createRandomMap();
    }
    
    public void createRandomMap() {
        Random rand = new Random();
        for(int i = 0; i < lon; i++) {
            for(int j = 0; j < lat; j++) {
                if(rand.nextInt(2) == 0)
                    map[i][j] = new Water();
                else map[i][j] = new Land();
            }
        }
    }
    
}
