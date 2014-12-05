package toodee.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
	
	protected double x, y;
	protected List<Point> vertices;
	protected boolean solid;
	protected boolean movable;
	protected double speed;
	protected double direction;
	protected double rotationalSpeed;
	protected double mass;
	protected double rotation;
	
	
	public Entity(double xpos, double ypos) {
		this.x = xpos;
		this.y = ypos;
		vertices = new ArrayList<Point>();
	}
	
	public void update() {};
	
	public void render() {};
}