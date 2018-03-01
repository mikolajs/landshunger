package pl.edu.osp.tiles;

import pl.edu.osp.objects.StaticObject;

abstract class Land implements Tile, java.io.Serializable {
    protected byte bio = 0;
    protected byte aType = 0; 
    protected StaticObject object;
    protected byte lev = 1;
    
    public byte getType() { return aType;}
    
    public void setStaticObject(StaticObject o) {object = o;}
    
}
