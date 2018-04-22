package pl.edu.osp;

import java.util.Random;

import pl.edu.osp.tiles.*;

public class MapWorld {
	public Tile[][] map;
	final int lon;
	final int lat;
	final TileManager tm;
	
	public MapWorld(int lon, int lat) {
		this.lon = lon;
		this.lat = lat;
		map = new Tile[lon][lat];
		tm = new TileManager();
		createRandomMap();
	}

	public void createRandomMap() {
		Random rand = new Random();
		int r;
		for (int i = 0; i < lon; i++) {
			for (int j = 0; j < lat; j++) {
				r = rand.nextInt(10);
				if (r < 4)
					map[i][j] = tm.getTile("DeepWater");
				else if (r < 8)
					map[i][j] = tm.getTile("PlainLand");
				else if (r < 9)
					map[i][j] = tm.getTile("Hill");
				else
					map[i][j] = tm.getTile("Mountain");
			}
		}
	}

	public void showMap() {
		char letter;
		byte lev;
		for (int i = 0; i < lon; i++) {
			for (int j = 0; j < lat; j++) {
				lev = map[i][j].getLev();
				if(lev == 0) letter = 'W';
				else if(lev == 1) letter = 'L';
				else if(lev == 2) letter = 'H';
				else letter = 'M';
				System.out.print(letter + "\t");
			}
			System.out.println();
		}
	}

}
