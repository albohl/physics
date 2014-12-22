package toodee.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import toodee.Game;
import toodee.Vector;
import toodee.graphics.Screen;

public abstract class Entity {
	
	protected double x, y;
	protected Vector pos;
	protected List<Vector> vertices;
	protected List<Triangle> triangles;
	protected boolean solid;
	protected boolean movable;
	protected double speed;
	protected Vector direction;
	protected double rotationalSpeed; //revolutions per second
	protected double mass;
	protected double rotation;
	protected Screen screen;
	protected Game game;
	protected int colour;
	
	public Entity(double xpos, double ypos, Screen screen, Game game) {
		x = xpos;
		y = ypos;
		this.game = game;
		this.screen = screen;
		this.pos = new Vector(xpos, ypos);
		vertices = new ArrayList<Vector>();
		triangles = new ArrayList<Triangle>();
	}
	
	protected void addTriangles(){
		for (int i = 1; i < vertices.size() - 2; i++){
			triangles.add(new Triangle(vertices.get(0), vertices.get(i), vertices.get(i + 1), colour, screen));
		}
	}
	
	public boolean pointInEntity(int px, int py){
		boolean inEntity = false;
		for (Triangle t : triangles){
			if (t.pointInTriangle(px, py)){
				inEntity = true;
			}
		}
		return inEntity;
	}
	
	public void update() {};
	
	public void render() {
		for( Triangle t : triangles){
			t.render();
		}
	};
	
	public void add(){
		game.entities.add(this);
	};
}