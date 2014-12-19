package toodee;

public class Vector {
	public double x, y;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double dot(Vector v){
		double result = x * v.x + y * v.y;
		return result;
	}
}
