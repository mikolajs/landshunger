package pl.edu.osp;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		MapWorld e = new MapWorld(10,10);
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("landmap.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(e);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      } catch (IOException i) {
	    	  i.printStackTrace();
	      }
		
		
	}

}
