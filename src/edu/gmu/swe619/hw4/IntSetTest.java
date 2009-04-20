package edu.gmu.swe619.hw4;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntSetTest {

	@Test
	public void repOk() {
		// See the method insert() for how to apply the intentional fault
		// that will break the rep-invariant.
		IntSet intSet = new IntSet();
		intSet.insert(4);
		intSet.insert(1000);
		intSet.insert(10);
		
		assertEquals(true, intSet.repOk());
	}
}
