package pl.edu.osp.tiles;

import pl.edu.osp.objects.Mineral;

public class Mountain  extends AbstractTile {
	private byte aType = 40;
	final byte lev = 3;
	
	public Mountain() {
		staticObject = Mineral.get();
	}
	@Override
	public byte getaType() {return aType;}
	@Override
	public void grow() {return;}
	public byte getLev() {return lev;}
	@Override
	public byte getFertility() {return 0;}

}
