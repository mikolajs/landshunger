package pl.edu.osp.objects;

public abstract class Plant implements StaticObject {
    protected byte bio = 0;
    protected short hp = 0;
    static protected short HP = 0;
    static protected short GROW = 0;
    static protected byte BIO = 0;
    static protected byte ATYPE = 0;
    static protected String NAME = "";
    
    public byte getBio() {
        return bio;
    }
    
    public void setBio(byte b) { bio = b; }
    
    public void grow() { 
       hp += GROW;
       if(hp > HP) hp = HP;
    }
    
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
