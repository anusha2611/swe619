package edu.gmu.swe619.hw10;

public class Point3 extends Point2 {

	private int z;
	
	public boolean equals(Object p) {
		if(p instanceof Point3) return equals((Point3)p);
		return super.equals(p);
	}
	
	public boolean equals(Point2 p) {
		if(p instanceof Point3) return equals((Point3)p);
		return super.equals(p);
	}
	
	public boolean equals(Point3 p) {
		if(p == null || z != p.z) return false;
		return super.equals(p);
	}
}
