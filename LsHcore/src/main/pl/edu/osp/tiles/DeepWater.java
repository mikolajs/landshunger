package pl.edu.osp.tiles;

import pl.edu.osp.objects.Plankton;

public class DeepWater extends Water {
	final private byte aType = 61;
	final protected byte fertility = 60;
	
	public DeepWater() {
		staticObject = new Plankton();
	}

}
