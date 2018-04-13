package pl.edu.osp;

import java.util.Random;

import pl.edu.osp.tiles.*;

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
    
    
    public void  game(int turns) {
        for(int i = 0; i < turns; i++) {
           grown();
           showMap();
           try {
               Thread.sleep(1000);
           } catch(Throwable e) {
               System.out.println("Sleep error");
           }
        }
    }

    private void createRandomMap() {
        Random rand = new Random();
        int r;
        for (int i = 0; i < lon; i++) {
            for (int j = 0; j < lat; j++) {
                r = rand.nextInt(10);
                if (r < 4)
                    map[i][j] = new Water();
                else if (r < 8)
                    map[i][j] = new Land();
                else if (r < 9)
                    map[i][j] = new Hill();
                else
                    map[i][j] = new Mountain();
            }
        }
    }

    private void showMap() {
        char letter;
        byte lev;
        System.out.println("==========================================================");
        for (int i = 0; i < lon; i++) {
            for (int j = 0; j < lat; j++) {
                lev = map[i][j].getLev();
                if (lev == 0)
                    letter = 'W';
                else if (lev == 1)
                    letter = 'L';
                else if (lev == 2)
                    letter = 'H';
                else
                    letter = 'M';
                System.out.print(letter + "\t");
            }
            System.out.println();
        }
    }

    private void grown() {
        for (int i = 0; i < lon; i++) {
            for (int j = 0; j < lat; j++) {
                map[i][j].grow();
            }
        }
    }
    
    
  

}
