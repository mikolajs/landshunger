package pl.edu.osp;

import java.io.*;
import java.util.*;

public class Main {

    private int X, Y;
    final MapWorld world;
    
    public Main() {
        world = new MapWorld(10, 10);
    }

    public static void main(String[] args) {
    	Main game = new Main();
        game.game();

    }
    
    public void game() {
    	world.showMap();
    }

    public void loadFile() {
        try {
            Scanner sc = new Scanner(new File("mapa.data"));
            X = sc.nextInt();
            Y = sc.nextInt();
            byte aType = 0;
            byte lev;
            while(sc.hasNextLine()) {
                for(int i = 0; i < X*Y; i++) {
                   lev = sc.nextByte();
                   aType = sc.nextByte();
                   
                }
            }

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

}
