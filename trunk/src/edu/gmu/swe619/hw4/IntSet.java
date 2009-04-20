package edu.gmu.swe619.hw4;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class IntSet {
	// OVERVIEW: IntSets are unbounded, mutable sets of integers
	private static final int THRESHOLD = 100;
	private boolean[] els;
	private Vector otherEls;
	private int sz;
	
	// constructors
	public IntSet() {
		// EFFECTS: Initializes this to be empty.
		otherEls = new Vector();
		els = new boolean[THRESHOLD];
		for(int i = 0; i < THRESHOLD; i++) {
			els[i] = false;
		}
		sz = 0;
	}
	
	// methods
	public void insert(int x) {
		// MODIFIES: this
		// EFFECTS: Adds x to the elements of this.
		if(x >= 0 && x < THRESHOLD) {
			if(!els[x]) {
				els[x] = true;
				// FAULT HERE
				// Comment out the line below to apply fault.
				// FAULT HERE
				sz++;
			}
		}
		else {
			Integer y = new Integer(x);
			if(getIndex(y) < 0) {
				otherEls.add(y);
				sz++;
			}
		}
	}
	
	public void remove(int x) {
		// MODIFIES: this
		// EFFECTS: Removes x from this
		if(x >= 0 && x < THRESHOLD) {
			if(els[x]) {
				els[x] = false;
				sz--;
			}
		}
		else {
			int i = getIndex(new Integer(x));
			if(i < 0) return;
		
			otherEls.set(i, otherEls.lastElement());
			otherEls.remove(otherEls.size() - 1);
			sz--;
		}
	}
	
	public boolean isIn(int x) {
		// EFFECTS: Returns true if x is in this else returns false
		if(x >= 0 && x < THRESHOLD) {
			return els[x];
		}
		
		return getIndex(new Integer(x)) >= 0;
	}
	
	public int getIndex(Integer x) {
		if(x >= 0 && x < THRESHOLD) {
			if(els[x]) {
				return x;
			}
			else {
				return -1;
			}
		}
		// EFFECTS: If x is in this returns index where x appears else returns -1
		for(int i = 0; i < otherEls.size(); i++) {
			if(x.equals(otherEls.get(i))) {
				int numTrues = 0;
				for(int j = 0; j < els.length; j++) {
					if(els[j]) { 
						numTrues++;
					}
				}
				return i + numTrues;
			}
		}
		
		return -1;
	}
	
	public int size() {
		// EFFECTS: Returns the cardinality of this
		return sz;
	}
	
	public int choose() throws Exception {
		// EFFECTS: If this is empty throws EmptyException else
		// returns an arbitrary element of this.
		if(sz == 0) throw new Exception("IntSet.choose");
		if(otherEls.size() > 0) { return (Integer)otherEls.lastElement(); }
		int num = -1;
		for(int i = 0; i < els.length; i++) {
			if(els[i]) {
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean repOk() {
		// EFFECTS: Returns true if the rep invariant holds for this;
		// otherwise returns false.
		if(els == null) { return false; }
		if(otherEls == null) { return false; }
		if(els.length != THRESHOLD) { return false; }
		
		for(Object i : otherEls) {
			if(!(i instanceof Integer)) { return false; }
			Integer num = (Integer)i;
			if(num >= 0 && num <= THRESHOLD) { return false; }
		}
		
		Set set = new HashSet();
		set.addAll(otherEls);
		if(set.size() != otherEls.size()) { return false; }
		
		int numTrues = 0;
		for(int i = 0; i < els.length; i++) {
			if(els[i]) { 
				numTrues++;
			}
		}
		
		if(sz != (otherEls.size() + numTrues)) { return false; }
		
		return true;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		for(int i = 0; i < els.length; i++) {
			if(els[i]) {
				sb.append(i + ", ");
			}
		}
		
		for(Object obj : otherEls) {
			Integer num = (Integer)obj;
			sb.append(num.toString() + ", ");
		}
		
		if(sb.toString().endsWith(", ")) {
			sb.delete(sb.length() - 2, sb.length());
		}
		
		sb.append("}");
		return sb.toString();
	}
}