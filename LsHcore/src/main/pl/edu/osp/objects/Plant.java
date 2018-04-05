package pl.edu.osp.objects;

public abstract class Plant implements StaticObject {
    protected byte bio = 0;
    final protected byte maxBio;
    final protected byte growLev;
    final protected byte aType;
    final protected String name;
    
    public Plant(byte maxBio, byte growLev, byte aType, String name) {
        this.maxBio = maxBio;
        this.growLev = growLev;
        this.aType = aType;
        this.name = name;
    }
    
    public byte getBio() {
        return bio;
    }
    
    public void setBio(byte b) { bio = b; }
    
    public void grow() { 
        bio += growLev; 
        if(bio > maxBio && maxBio < 0)
            bio = maxBio;
    }
    
}
