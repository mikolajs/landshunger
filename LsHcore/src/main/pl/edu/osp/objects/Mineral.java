package pl.edu.osp.objects;

public class Mineral implements StaticObject {
	private byte abudance = 0;
	
	private Mineral(byte abudance) {
		this.abudance = abudance;
	}
	
	public static Mineral get() {
		return new Mineral((byte) (Math.random() * 120));
	}
	
	public static Mineral getEmpty() {
		return new 
				Mineral((byte) 0);
	}
	public byte getAbudance() { return abudance;}

	@Override
	public void grow() {	return;	}

}
