package pl.edu.osp.objects;


public abstract class Plant implements StaticObject {
    protected byte bio = 0;
    protected short hp = 0;
    final PlantConstans plantConst;
    
    public Plant(PlantConstans pc) {
    	plantConst = pc;
    }
    
    public void nextDay() {
        yields();
    }
       
    public byte getBio() { return bio; }
    public void setBio(byte b) { bio = b; }
    public short getHP() {return hp;}
	public byte maxBio() {return plantConst.mBio;}
	public byte growLev() {return plantConst.gLev;}
	public short maxHP() {	return plantConst.mHP;}
	public String name() {return plantConst.name;}
	@Override
	public String toString() {
	    return plantConst.name + " " + hp + " " + bio;
	}
    
    public void yields() {
        bio += plantConst.gLev;
        if(bio > plantConst.mBio)
            bio = plantConst.mBio;     
    }
    
    
    
}
