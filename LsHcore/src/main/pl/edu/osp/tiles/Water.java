package pl.edu.osp.tiles;

import pl.edu.osp.objects.Plankton;

public class Water extends AbstractTile {
   // private byte grass = 0;
	
    final byte aType = 60; 
    final protected byte lev = 0;
    final byte fertility = 90;
    
    public Water() {
    	staticObject = Plankton.get(fertility);
    }
}
