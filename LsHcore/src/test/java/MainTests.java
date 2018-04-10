

import org.junit.jupiter.api.Test;

import pl.edu.osp.objects.LeafWood;

import static org.junit.jupiter.api.Assertions.*;

public class MainTests {
	@Test
	void justAnExample() {
        System.out.println("This test method should be run");
        assertEquals(2 , 1 + 1);
    }
	@Test
	void getMaxBioInChild() {
	    LeafWood lw = new LeafWood();
	    lw.nextDay();
	    assertEquals(10, lw.getBio());
	}
}
