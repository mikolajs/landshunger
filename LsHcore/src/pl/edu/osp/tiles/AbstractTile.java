package pl.edu.osp.tiles;

import pl.edu.osp.objects.StaticObject;

abstract class AbstractTile implements Tile {
    
    protected StaticObject staticObject;
    final protected byte aType = 60; 
    final protected byte lev = 0;
    
    public byte getaType() {return aType;}   
    public void grow() { if(staticObject != null) staticObject.grow() ;} 
    public byte getLev() {return lev;}
    
}
