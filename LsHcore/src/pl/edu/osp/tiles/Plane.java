package pl.edu.osp.tiles;

public class Plane extends Land {
    private byte aType = 1;
    final public byte maxBio = 120;
    final byte lev = 1;
	@Override
	public byte getaType() {
		// TODO Auto-generated method stub
		return aType;
	}
	
    @Override 
    public void grow() {
    	bio += 5;
    }
    
}
