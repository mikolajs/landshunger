package pl.edu.osp;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Land e = new Land();
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("landelement.ser");
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
