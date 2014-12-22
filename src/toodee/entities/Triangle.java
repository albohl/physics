package toodee.entities;

import java.awt.geom.Point2D;

import toodee.Game;
import toodee.Matrix;
import toodee.Vector;
import toodee.graphics.Screen;

public class Triangle {
	public int miny, minx, maxy, maxx;
	private double x0, y0, x1, y1, x2, y2;
	public Vector v0, v1, v2;
	private Screen screen;
	private int colour;
	private boolean altered = false;
	public Matrix scaleMatrix = new Matrix(2,2);
	public Matrix rotateMatrix = new Matrix(2,2);
	public Matrix translateMatrix = new Matrix(2, 3);
	
	public Triangle(double x0, double y0, double x1, double y1, double x2, double y2, int colour, Screen screen){
//		A = new Point2D.Double(x0, y0);
//		B = new Point2D.Double(x1, y1);
//		C = new Point2D.Double(x2, y2);
		this.x0 = x0;
		this.x1 = x1;
		this.x2 = x2;
		this.y0 = y0;
		this.y1 = y1;
		this.y2 = y2;
		this.colour = colour;
		this.screen = screen;
	}
	
	public Triangle(Vector v0, Vector v1, Vector v2, int colour, Screen screen){
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		this.colour = colour;
		this.screen = screen;
		calcLimits();
	}
	
	public boolean pointInTriangle(int x, int y){
		Vector vec2 = new Vector(x - v0.coords[0], y - v0.coords[1]);
		Vector vec0 = new Vector(v1.coords[0] - v0.coords[0], v1.coords[1] - v0.coords[1]);
		Vector vec1 = new Vector(v2.coords[0] - v0.coords[0], v2.coords[1] - v0.coords[1]);
		double dot00 = v0.dot(vec0);
		double dot01 = vec0.dot(vec1);
		double dot02 = vec0.dot(vec2);
		double dot11 = vec1.dot(vec1);
		double dot12 = vec1.dot(vec2);
		double invDenom = 1.0 / (dot00 * dot11 - dot01 * dot01);
		double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
		double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
		return (u >= 0.0) && (v >= 0.0) && (u + v <= 1.0);
	}
	
	public void translate(double cx, double cy, Vector direction){
		Vector v0 = new Vector(x0 - cx, y0 - cy, 1);
		Vector v1 = new Vector(x1 - cx, y1 - cy, 1);
		Vector v2 = new Vector(x2 - cx, y2 - cy, 1);
		translateMatrix.matrix[0][0] = 1;
		translateMatrix.matrix[0][2] = direction.coords[0];
		translateMatrix.matrix[1][1] = 1;
		translateMatrix.matrix[1][2] = direction.coords[1];
		v0 = translateMatrix.matrixMultiplication(v0);
		v1 = translateMatrix.matrixMultiplication(v1);
		v2 = translateMatrix.matrixMultiplication(v2);
		x0 = cx + v0.coords[0];
		y0 = cy + v0.coords[1];
		x1 = cx + v1.coords[0];
		y1 = cy + v1.coords[1];
		x2 = cx + v2.coords[0];
		y2 = cy + v2.coords[1];
		calcLimits();
	}
	
	public void scale(double cx, double cy, double amount){
		Vector v0 = new Vector(x0 - cx, y0 - cy);
		Vector v1 = new Vector(x1 - cx, y1 - cy);
		Vector v2 = new Vector(x2 - cx, y2 - cy);
		scaleMatrix.matrix[0][0] = amount;
		scaleMatrix.matrix[1][1] = amount;
		v0 = scaleMatrix.matrixMultiplication(v0);
		v1 = scaleMatrix.matrixMultiplication(v1);
		v2 = scaleMatrix.matrixMultiplication(v2);
		x0 = cx + v0.coords[0];
		y0 = cy + v0.coords[1];
		x1 = cx + v1.coords[0];
		y1 = cy + v1.coords[1];
		x2 = cx + v2.coords[0];
		y2 = cy + v2.coords[1];
		calcLimits();
	}
	
	public void rotate(double cx, double cy, double radians){
		Vector v0 = new Vector(x0 - cx, y0 - cy);
		Vector v1 = new Vector(x1 - cx, y1 - cy);
		Vector v2 = new Vector(x2 - cx, y2 - cy);
		rotateMatrix.matrix[0][0] = Math.cos(radians);
		rotateMatrix.matrix[0][1] = -Math.sin(radians);
		rotateMatrix.matrix[1][0] = Math.sin(radians);
		rotateMatrix.matrix[1][1] = Math.cos(radians);
		v0 = rotateMatrix.matrixMultiplication(v0);
		v1 = rotateMatrix.matrixMultiplication(v1);
		v2 = rotateMatrix.matrixMultiplication(v2);
		x0 = cx + v0.coords[0];
		y0 = cy + v0.coords[1];
		x1 = cx + v1.coords[0];
		y1 = cy + v1.coords[1];
		x2 = cx + v2.coords[0];
		y2 = cy + v2.coords[1];
		calcLimits();
	}
	
	public void calcLimits(){
		minx = (int) Math.floor(Math.min(v0.coords[0], Math.min(v1.coords[0], v2.coords[0])));
		miny = (int) Math.floor(Math.min(v0.coords[1], Math.min(v1.coords[1], v2.coords[1])));
		maxx = (int) Math.floor(Math.max(v0.coords[0], Math.max(v1.coords[0], v2.coords[0])));
		maxy = (int) Math.floor(Math.max(v0.coords[1], Math.max(v1.coords[1], v2.coords[1])));
		altered = false;
	}
	
	public void render(){
//		System.out.println(minx + " " + maxx + " " + maxy + " " + miny);
		for(int y = miny; y < screen.height; y++){
			for(int x = minx; x < maxx; x++){
				if (x >= screen.width || x < 0 || y >= screen.height || y < 0){
					continue;
				}
				if (pointInTriangle(x, y)){
					screen.pixels[x + y * screen.width] = colour;
				}
			}
		}	
	}
}
