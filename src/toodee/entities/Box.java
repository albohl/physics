package toodee.entities;

import java.awt.Point;

public class Box extends Entity{

	public Box(double xpos, double ypos, int size, boolean movable, boolean solid, double mass){
		super(xpos, ypos);
		vertices.add(new Point(size/2, size/2));
		vertices.add(new Point(size/2, -size/2));
		vertices.add(new Point(-size/2, -size/2));
		vertices.add(new Point(-size/2, size/2));
		this.movable = movable;
		this.solid = solid;
		this.mass = mass;
	}
	
	public boolean isWithin(int x, int y) {
		
		return true;
	}
	
	public void update(){
		
	}
	
	public void render(){
		
	}
	
}
