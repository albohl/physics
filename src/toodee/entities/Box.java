package toodee.entities;

import java.awt.Point;

import toodee.Game;
import toodee.graphics.Screen;

public class Box extends Entity{

	public Box(double xpos, double ypos, double size, boolean movable, boolean solid, double mass, Screen screen, Game game){
		super(xpos, ypos, screen, game);
		this.movable = movable;
		this.solid = solid;
		this.mass = mass;
		this.triangles.add(new Triangle(x + size / 2, y + size / 2, x + size / 2, y - size / 2, x - size / 2, y - size/2, 0xff0000, screen));
		this.triangles.add(new Triangle(x + size / 2, y + size / 2, x - size / 2, y - size/2, x - size / 2, y + size / 2,  0x00ff00, screen));
	}
	
	public void update(){
		
	}
	
}
