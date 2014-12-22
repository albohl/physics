package toodee;

public class Matrix {
	
	public double[][] matrix;
	
	public Matrix(int rows, int columns){
		matrix = new double[rows][columns];
	}
	
	public Vector matrixMultiplication(Vector v){
		if (v.size < matrix[0].length){
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < matrix.length; i++){
			double newCoord = 0;
			for (int j = 0; j < matrix[i].length; j++){
				newCoord += v.coords[j] * matrix[i][j];
			}
			v.coords[i] = newCoord;
		}
		
		return v;
	}
}
