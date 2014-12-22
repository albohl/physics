package toodee;

public class Vector {
	public double[] coords;
	public int size;
	
	public Vector(double ... doubles){
		coords = new double[doubles.length];
		for (int i = 0; i < doubles.length; i++){
			coords[i] = doubles[i];
		}
		size = doubles.length;
	}
	
	public double dot(Vector v){
		if (size != v.size){
			throw new IllegalArgumentException();
		}
		double result = 0;
		for (int i = 0; i < size; i++){
			result += coords[i] * v.coords[i];
		}
		return result;
	}
	
	public void print(){
		for (double d : coords){
			System.out.println(d);
		}
	}
}
