package toodee.entities;

import java.awt.Point;

import toodee.Game;
import toodee.Vector;
import toodee.graphics.Screen;

public class Box extends Entity{
	
//	public Box(double xpos, double ypos, double size, boolean movable,/* boolean solid, double mass,*/ Screen screen, Game game){
//		super(xpos, ypos, screen, game);
//		this.movable = movable;
//		this.solid = solid;
//		this.mass = mass;
//		vertices.add(new Vector(x + size/2, y + size / 2));
//		topHalf = new Triangle(x + size / 2, y + size / 2, x + size / 2, y - size / 2, x - size / 2, y - size/2, 0xff0000, screen);
//		bottomHalf = new Triangle(x + size / 2, y + size / 2, x - size / 2, y - size/2, x - size / 2, y + size / 2,  0x00ff00, screen); 
//		this.triangles.add(topHalf);
//		this.triangles.add(bottomHalf);
//		topHalf.calcLimits();
//		bottomHalf.calcLimits();
//		direction = new Vector(0, 0);
//	}	
	
	public Box(double xpos, double ypos, double size, boolean movable,/* boolean solid, double mass,*/ Screen screen, Game game){
		super(xpos, ypos, screen, game);
		this.movable = movable;
		x = xpos;
		y = ypos;
		colour = 0xffffff;
		vertices.add(new Vector(x + size / 2, y + size / 2));
		vertices.add(new Vector(x + size / 2, y - size / 2));
		vertices.add(new Vector(x - size / 2, y + size / 2));
		vertices.add(new Vector(x - size / 2, y - size / 2));
		for (Vector v : vertices){
			v.print();
		}
		addTriangles();
		direction = new Vector(0, 0);
	}
	
	public void update(){
		if (movable) move();
	}
	
	public void click(){
	}
	
	public void move(){
		direction.coords[1] += 9.82 / game.tickRate;
		for (Triangle t : triangles){
			t.translate(x, y, direction);
		}
	}
	
}
