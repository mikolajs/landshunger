package pl.edu.osp.tiles;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TileManager {

	final private String tileConfigPath =
			"configdata/tiledata.cfg";

	final private Map<String, TileConstant> tileConsts = new TreeMap<>();

	public TileManager() {
		loadTileData();
	}
	
	public Tile getTile(String className) {
		String fullClassName = "pl.edu.osp.tiles."+className;
		Tile t = null;
		try {
		  Constructor<?> c = Class.forName(fullClassName)
				.getConstructor(TileConstant.class);
			t = (Tile) c.newInstance(getTileConst(className));
		}
		catch(Exception e) {
			System.out.println("Error initialize while Tile: " + e.toString());
		}
		return t;
	}
	
	public TileConstant getTileConst(String className) {
		return tileConsts.get(className);
	}

	private void loadTileData() {
		File file = new File(tileConfigPath);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			TileConstant tc;
			sc.nextLine();
			while (sc.hasNext()) {
				tc = new TileConstant(
						sc.nextByte(), 
						sc.nextByte(), 
						sc.nextByte(),
						sc.next());
				tileConsts.put(tc.name, tc);
				//System.out.println(tc.toString());

			}
		} catch (IOException e) {
			System.out.println("Cant load file for config tiles");
		} finally {
			if (sc != null)
				sc.close();
		}
	}

}
