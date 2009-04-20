package edu.gmu.swe619.hw11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class ForwardingListTest {
	
	@Test
	public void add() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Luke Skywalker");
		assertEquals(true, list.contains("Luke Skywalker"));
	}

	@Test
	public void addWithIndex() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Luke Skywalker");
		list.add("Darth Vader");
		list.add(1, "Millenium Falcon");
		assertEquals("Millenium Falcon", list.get(1));
	}

	@Test
	public void addAll() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		
		Collection<String> addedList = new ArrayList<String>();
		addedList.add("Luke");
		addedList.add("Skywalker");
		addedList.add("Darth");
		addedList.add("Vader");
		list.addAll(addedList);
		
		assertEquals(4, list.size());
		assertEquals(true, list.contains("Luke"));
		assertEquals(true, list.contains("Skywalker"));
		assertEquals(true, list.contains("Darth"));
		assertEquals(true, list.contains("Vader"));
	}

	@Test
	public void addAllWithIndex() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Jabba");
		list.add("The Hut");
		list.add("Leah");
		list.add("Skywalker");
		
		Collection<String> addedList = new ArrayList<String>();
		addedList.add("Luke");
		addedList.add("Skywalker");
		addedList.add("Darth");
		addedList.add("Vader");
		list.addAll(2, addedList);
		
		assertEquals(8, list.size());
		assertEquals("Jabba", list.get(0));
		assertEquals("The Hut", list.get(1));
		assertEquals("Luke", list.get(2));
		assertEquals("Skywalker", list.get(3));
		assertEquals("Darth", list.get(4));
		assertEquals("Vader", list.get(5));
		assertEquals("Leah", list.get(6));
		assertEquals("Skywalker", list.get(7));
	}

	@Test
	public void clear() {
		List<String> list = new ArrayList<String>();
		list.add("Luke Skywalker");
		ForwardingList<String> fList = new ForwardingList<String>(list);
		
		fList.clear();
		assertEquals(0, fList.size());
	}

	@Test
	public void contains() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Luke Skywalker");
		
		assertTrue(list.contains("Luke Skywalker"));
		assertFalse(list.contains("Luke"));
		list.clear();
		assertFalse(list.contains("Luke Skywalker"));
	}

	@Test
	public void get() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Jabba");
		list.add("The Hut");
		list.add("Leah");
		list.add("Skywalker");
		
		assertEquals("Jabba", list.get(0));
		assertEquals("The Hut", list.get(1));
		assertEquals("Leah", list.get(2));
		assertEquals("Skywalker", list.get(3));
	}

	@Test
	public void indexOf() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Jabba");
		list.add("The Hut");
		list.add("Leah");
		list.add("Skywalker");
		
		assertEquals(0, list.indexOf("Jabba"));
		assertEquals(1, list.indexOf("The Hut"));
		assertEquals(2, list.indexOf("Leah"));
		assertEquals(3, list.indexOf("Skywalker"));
	}

	@Test
	public void isEmpty() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		assertTrue(list.isEmpty());
		list.add("Luke Skywalker");
		assertFalse(list.isEmpty());
	}

	@Test
	public void lastIndexOf() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Jabba");
		list.add("The Hut");
		list.add("Leah");
		list.add("Skywalker");
		list.add("Jabba");
		list.add("The Hut");
		list.add("Leah");
		list.add("Skywalker");
		assertEquals(4, list.lastIndexOf("Jabba"));
		assertEquals(5, list.lastIndexOf("The Hut"));
		assertEquals(6, list.lastIndexOf("Leah"));
		assertEquals(7, list.lastIndexOf("Skywalker"));
	}

	@Test
	public void remove() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Jabba");
		list.add("The Hut");
		list.add("Leah");
		list.add("Skywalker");

		list.remove(0);
		assertEquals(Arrays.asList("The Hut", "Leah", "Skywalker"), list);
		list.remove(1);
		assertEquals(Arrays.asList("The Hut", "Skywalker"), list);
	}

	@Test
	public void removeUsingObject() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		list.add("Jabba");
		list.add("The Hut");
		list.add("Leah");
		list.add("Skywalker");

		list.remove("Hello");
		assertEquals(Arrays.asList("Jabba", "The Hut", "Leah", "Skywalker"), list);
		list.remove("Leah");
		assertEquals(Arrays.asList("Jabba", "The Hut", "Skywalker"), list);
		list.remove("The Hut");
		assertEquals(Arrays.asList("Jabba", "Skywalker"), list);
		list.remove("Jabba");
		assertEquals(Arrays.asList("Skywalker"), list);
		list.remove("Skywalker");
		assertEquals(0, list.size());
	}

	@Test
	public void size() {
		ForwardingList<String> list = new ForwardingList<String>(new ArrayList<String>());
		assertEquals(0, list.size());
		list.add("Jabba");
		assertEquals(1, list.size());
		list.add("The Hut");
		assertEquals(2, list.size());
		list.add("Leah");
		assertEquals(3, list.size());
		list.add("Skywalker");
		assertEquals(4, list.size());
		list.remove(0);
		assertEquals(3, list.size());
		list.remove(0);
		assertEquals(2, list.size());
		list.remove(0);
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}
}
