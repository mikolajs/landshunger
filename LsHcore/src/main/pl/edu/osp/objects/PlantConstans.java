package pl.edu.osp.objects;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PlantConstans {
	final public byte mBio;
	final public byte gLev;
	final public  short mHP;
	final public String name;
	
	public PlantConstans(String className) {
		String line = getLineFromFile(className);
		String[] data = line.split("\\w+");
		mBio = Byte.parseByte(data[4]);
		gLev = Byte.parseByte(data[3]);
		mHP = Short.parseShort(data[2]);
		name = data[1];
	}
	
	protected String getLineFromFile(String className) {
    	File file = new File("configdata/plants.cfg");
    	Scanner sc = null;
    	String line;
    	try {
    		sc = new Scanner(file);
    		while(sc.hasNextLine()) {
    			line = sc.nextLine();
    			if(line.startsWith(className)) {
    				sc.close();
    				return line;
    			}
    		}
    	} catch(IOException e) {
    		System.out.println("Cant load file for config plants");
    	} finally {
    		if(sc!= null) sc.close();
    	}
    	return "";
    }

}
