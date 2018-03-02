package pl.edu.osp.tiles;

public class Mountain implements Tile {
	 private byte aType = 40;
	    final public byte maxBio = 0;
	    final byte lev = 3;
	@Override
	public byte getaType() {
		return aType;
	}
	@Override
	public void grow() {
		return;
	}
	
	public byte getLev() {return lev;}



}
