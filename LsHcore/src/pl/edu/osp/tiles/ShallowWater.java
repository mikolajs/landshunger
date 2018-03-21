package pl.edu.osp.tiles;

import pl.edu.osp.objects.SeaFood;

public class ShallowWater extends Water {
	final private byte aType = 63;
	final protected byte fertility = 100;
	
	public ShallowWater() {
		staticObject = SeaFood.get(fertility);
	}
}
