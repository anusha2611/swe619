package edu.gmu.swe619.hw11;

import java.util.Collection;
import java.util.List;

public class InstrumentedList<E> extends ForwardingList<E> {

	private int addCount = 0;
	
	public InstrumentedList(List<E> list) {
		super(list);
	}
	
	@Override
	// Effects:  adds an item to the list and increments the add counter
	// Mofifies: this list and the addition counter
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}
	
	// Effects: Returns the number of times an item has been added to this list
	public int getAddCount() {
		return addCount;
	}
}
