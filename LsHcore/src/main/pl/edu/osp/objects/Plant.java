package pl.edu.osp.objects;


public abstract class Plant implements StaticObject {
    protected byte bio = 0;
    protected short hp = 0;
    final PlantConstans plantConst;
    
    public Plant(PlantConstans pc) {
    	plantConst = pc;
    }
    
    public void nextDay() {
    	grow();
    }
       
    public byte getBio() { return bio; }
    public void setBio(byte b) { bio = b; }
    public short getHP() {return hp;}
	public byte maxBio() {return plantConst.mBio;}
	public byte growLev() {return plantConst.gLev;}
	public short maxHP() {	return plantConst.mHP;}
	public String name() {return plantConst.name;}
    
    
    public void yields() {
        bio += GROW;
        if(bio > BIO)
            bio = BIO;     
    }
    
    public short getHP() {
        return hp;
    }
    
    public static byte getMaxBio() {
        return  BIO;
    }
    
    public static byte getGrowLev() {
        return GROW;
    }
    
    public static byte getAType() {
        return ATYPE;
    }
    
    public static String getName() {
        return NAME;
    }
    
}
