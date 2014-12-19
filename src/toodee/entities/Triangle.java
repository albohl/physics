package toodee.entities;

import java.awt.geom.Point2D;

import toodee.Game;
import toodee.Vector;
import toodee.graphics.Screen;

public class Triangle {
	public Point2D A, B, C;
	public int miny, minx, maxy, maxx;
	private double x1, y1, x2, y2, x3, y3; 
	private Screen screen;
	private int colour;
	
	
	public Triangle(double x1, double y1, double x2, double y2, double x3, double y3, int colour, Screen screen){
//		A = new Point2D.Double(x1, y1);
//		B = new Point2D.Double(x2, y2);
//		C = new Point2D.Double(x3, y3);
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		this.colour = colour;
		this.screen = screen;
		minx = (int) Math.floor(Math.min(x1, Math.min(x2, x3)));
		miny = (int) Math.floor(Math.min(y1, Math.min(y2, y3)));
		maxx = (int) Math.floor(Math.max(x1, Math.max(x2, x3)));
		maxy = (int) Math.floor(Math.max(y1, Math.max(y2, y3)));
		System.out.println(minx + " " + miny + " " + maxx + " " + maxy);
	}
	
	public boolean pointInTriangle(int x, int y){
		Vector v2 = new Vector(x - x1, y - y1);
		Vector v0 = new Vector(x2 - x1, y2 - y1);
		Vector v1 = new Vector(x3 - x1, y3 - y1);
		double dot00 = v0.dot(v0);
		double dot01 = v0.dot(v1);
		double dot02 = v0.dot(v2);
		double dot11 = v1.dot(v1);
		double dot12 = v1.dot(v2);
		double invDenom = 1.0 / (dot00 * dot11 - dot01 * dot01);
		double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
		double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
		return (u >= 0.0) && (v >= 0.0) && (u + v <= 1.0);
	}
	
	public void translate(){}
	
	public void scale(){}
	
	public void rotate(){}
	
	public void render(){
		for(int y = miny; y < screen.height; y++){
			for(int x = minx; x < maxx; x++)
				if (pointInTriangle(x, y)){
					screen.pixels[x + y * screen.width] = colour;
				}
		}	
	}
}
