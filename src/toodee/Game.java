package toodee;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import toodee.entities.Box;
import toodee.entities.Entity;
import toodee.graphics.Screen;
import toodee.input.Mouse;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static int width = 800;
	public static int height = width / 16 * 9;
	public double tickRate = 60;
	public static int scale = 3;
	private int clickCount;
	private int lastClickCount = 0;
	public List<Entity> entities;
	static Box startbox;
	
	private Thread gameThread;
	public JFrame frame = new JFrame();
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	
	public Game() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		entities = new ArrayList<Entity>();
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Toodee");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
		startbox = new Box((double)width / 2, (double)height * 2 / 3, height/5.0, false, game.screen, game);
		startbox.add();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / tickRate;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("Updates per second: " + updates + ", FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void update() {
		clickCount = Mouse.clickCount;
//		System.out.println("Number of entities: " + entities.size());
		if (lastClickCount < clickCount){
			clicked();
			lastClickCount = clickCount;
		}
		for (Entity e : entities){
			e.update();
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();

		screen.render(0, 0);
		
		for (Entity e : entities) {
			e.render();
		}
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public void clicked(){
		if (startbox.pointInEntity(Mouse.getX(), Mouse.getY())){
			startbox.click();
		}
		Box box = new Box((double)Mouse.getX(), (double)Mouse.getY(), 20.0, true, screen, this);
		box.add();
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "Game");
		gameThread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try{
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
