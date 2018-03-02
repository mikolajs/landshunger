package pl.edu.osp.tiles;

import pl.edu.osp.objects.Moveable;
import pl.edu.osp.objects.StaticObject;

public class Land implements Tile {
	protected byte bio = 0;
	protected byte maxBio = 100;
	protected byte growLev = 5;
    protected byte aType = 1; 
    protected StaticObject object;
    protected Moveable unit;
    protected byte lev = 1;
    
    public byte getType() { return aType;}
    
    public void setStaticObject(StaticObject o) {object = o;}
    public void setMoveableUnit(Moveable m) {unit = m;}
    public void grow() { bio += growLev;}
    public byte getLev() {return lev;}

	@Override
	public byte getaType() {
		return aType;
	} 
    
}
