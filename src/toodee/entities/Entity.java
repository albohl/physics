package toodee.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import toodee.Game;
import toodee.graphics.Screen;

public abstract class Entity {
	
	protected double x, y;
	protected List<Triangle> triangles;
	protected boolean solid;
	protected boolean movable;
	protected double speed;
	protected double direction;
	protected double rotationalSpeed;
	protected double mass;
	protected double rotation;
	protected Screen screen;
	protected Game game;
	
	
	public Entity(double xpos, double ypos, Screen screen, Game game) {
		this.x = xpos;
		this.y = ypos;
		this.screen = screen;
		triangles = new ArrayList<Triangle>();
		this.game = game;
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