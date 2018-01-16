package pl.edu.osp;

public class Land implements Tile, java.io.Serializable {
    final static byte maxBio = 127; 
    
    
    private byte grass = 0;
    public byte getGrass() {
        return grass;
    }
    public void setGrass(byte grass) {
        this.grass = grass;
    }
    public byte getaType() {
        return aType;
    }
    public void setaType(byte aType) {
        this.aType = aType;
    }
    public byte getWood() {
        return wood;
    }
    public void setWood(byte wood) {
        this.wood = wood;
    }
    public static byte getMaxbio() {
        return maxBio;
    }
    private byte aType = 1; 
    private byte wood = 0;
    /* for levels 0 water, 1 land, 2 hills, 3 mountains */
    private byte lev = 1;
    
}
