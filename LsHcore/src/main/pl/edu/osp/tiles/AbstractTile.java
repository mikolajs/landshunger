package pl.edu.osp.tiles;

import pl.edu.osp.objects.StaticObject;

abstract class AbstractTile implements Tile {
    
    protected StaticObject staticObject = null;
    final protected TileConstant tileConst;
    
    
    public AbstractTile(TileConstant constant) {
    	tileConst = constant;
    }
    
    
    public byte getaType() {return tileConst.aType;}   
    public void nextDay() { if(staticObject != null) staticObject.nextDay();} 
    public byte getLev() {return tileConst.lev;}
    public byte getFertility() {return tileConst.fertility;}
    public void setStaticObject(StaticObject o) {staticObject = o;}
    public String getName() { return tileConst.name; }
    
    
}
