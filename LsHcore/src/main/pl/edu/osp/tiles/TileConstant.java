package pl.edu.osp.tiles;

public class TileConstant {
	final protected byte aType; 
    final protected byte lev;
    final protected byte fertility;
    final protected String name;
    
    public TileConstant(byte type, byte lev, byte fertility, String name){
    	aType = type;
    	this.lev = lev;
    	this.fertility = fertility;
    	this.name = name;
    }
    
    @Override
    public String toString() {
    	return name + " t:" + aType + " l:" + lev + " f:" + fertility;
    }
}
