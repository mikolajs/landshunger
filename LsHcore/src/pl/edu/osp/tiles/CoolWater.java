package pl.edu.osp.tiles;

import pl.edu.osp.objects.SeaFood;

public class CoolWater  extends Water {

		final private byte aType = 62;
		final protected byte fertility = 125;
		
		public CoolWater() {
			staticObject = SeaFood.get(fertility);
	}

}
