package pl.edu.osp.tiles;

import pl.edu.osp.objects.StaticObject;

abstract class AbstractTile implements Tile {
    
    protected StaticObject staticObject = null;
    final protected byte aType = 60; 
    final protected byte lev = 0;
    final protected byte fertility = 0;
    
    public byte getaType() {return aType;}   
    public byte getFertility() {return fertility;}
    public void grow() { if(staticObject != null) staticObject.grow() ;} 
    public byte getLev() {return lev;}
    public void setStaticObject(StaticObject o) {staticObject = o;}
    
    
}
