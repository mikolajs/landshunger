package pl.edu.osp.objects;


public class SeaFood extends AbstractPlant {
	
	public static SeaFood get(byte fertility) {
		return new SeaFood(fertility, (byte) (fertility /  10));
	}
	
	private SeaFood(byte maxBio, byte growLev) {
		this.maxBio = maxBio;
		this.growLev = growLev;
	}

}
