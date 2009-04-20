package edu.gmu.swe619.hw11;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class InstrumentedListTest {
	
	@Test
	public void add() {
		InstrumentedList<String> list = 
			new InstrumentedList<String>(new ArrayList<String>());
		
		list.add("Luke");
		list.add("Skywalker");
		assertEquals(2, list.size());
		assertEquals(true, list.contains("Luke"));
		assertEquals(true, list.contains("Skywalker"));
	}
	
	@Test
	public void addAll() {
		InstrumentedList<String> list = 
			new InstrumentedList<String>(new ArrayList<String>());
		
		Collection<String> addedList = new ArrayList<String>();
		addedList.add("Luke");
		addedList.add("Skywalker");
		addedList.add("Darth");
		addedList.add("Vader");
		list.addAll(addedList);
		
		assertEquals(4, list.size());
		assertEquals(4, list.getAddCount());
		assertEquals(true, list.contains("Luke"));
		assertEquals(true, list.contains("Skywalker"));
		assertEquals(true, list.contains("Darth"));
		assertEquals(true, list.contains("Vader"));
	}

	@Test
	public void getAddCount() {
		InstrumentedList<String> list = 
			new InstrumentedList<String>(new ArrayList<String>());
		
		assertEquals(0, list.getAddCount());
		list.add("Luke");
		list.add("Skywalker");
		list.clear();
		assertEquals(2, list.getAddCount());
		list.add("Darth");
		list.add("Vader");
		list.clear();
		assertEquals(4, list.getAddCount());
	}
}
