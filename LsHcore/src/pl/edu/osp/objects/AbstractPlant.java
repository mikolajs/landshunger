package pl.edu.osp.objects;

public class AbstractPlant implements StaticObject {
	 protected byte bio = 0;
	    protected byte maxBio = 0;
	    protected byte growLev = 0;
	    protected byte aType = 0;
	    protected String name = "Plant";
	    
	    
	    public byte getBio() { return bio; }
	    public void setBio(byte b) { bio = b; }
	    
	    public void grow() { 
	        bio += growLev; 
	        if(bio > maxBio && maxBio < 0)
	            bio = maxBio;
	    }

}
