package pl.edu.osp.tiles;

import pl.edu.osp.objects.SeaFood;

public class Water implements Tile {
   // private byte grass = 0;
	protected byte bio = 0;;
	protected byte maxBio = 80;
	protected byte growLev = 0;
    private byte aType = 60; 
    protected byte lev = 0;
    protected SeaFood seaFood;
    public byte getaType() {return aType;}
    
   public void grow() { bio += growLev; } 
   public byte getLev() {return lev;}
   
}
