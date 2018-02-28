package pl.edu.osp.tiles;

import pl.edu.osp.objects.StaticObject;

abstract class Land implements Tile, java.io.Serializable {
    protected byte bio = 0;
    protected byte aType = 0; 
    protected StaticObject object;
    public byte getType() { return aType;}
    
}
