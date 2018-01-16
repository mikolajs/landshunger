package pl.edu.osp;

import java.io.*;
import java.util.*;

public class Main {

    private int X, Y;
    MapWorld world;
    
    public Main() {
        MapWorld world = new MapWorld(100, 100);
    }

    public static void main(String[] args) {
        

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
