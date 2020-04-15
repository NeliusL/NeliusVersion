package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 700 ;
	public static final int HEIGHT = 10000 ;
	public final String TITLE = "2D Space Game" ;
	
	private boolean running = false ;
	private Thread thread ;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB) ;
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null ;	
	
	private boolean is_shooting = false ;
	
	private Player p ;
	private Controller c ;
	private Textures tex ;
	private Menu menu ;
	
	private boolean upPressed = false ;
	private boolean downPressed = false ;
	private boolean leftPressed = false ;
	private boolean rightPressed = false ;
	
	public LinkedList<EntityA> ea ;
	public LinkedList<EntityB> eb ;
	
	private enum STATE {
		MENU,
		GAME
	} ;
	
	private STATE State = STATE.GAME ;
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader() ;
		try {
			spriteSheet = loader.loadImage("/Sprite_sheet.png") ;
			background = loader.loadImage("/background.png.jpg") ;
		}catch(IOException e) {
			e.printStackTrace() ;
		}
		
		addKeyListener(new KeyInput(this)) ;
		
		tex = new Textures(this) ;
		p = new Player(312, 878, tex) ;
		c = new Controller(this, tex) ;
		menu = new Menu() ;
		
		ea = c.getEntityA() ;
		eb = c.getEntityB() ;
		
	}
	
	private synchronized void start(){
		
		if (running){
			return ;
		}
		running = true ;
		thread = new Thread(this) ;
		thread.start() ;
	}
	
	private synchronized void stop(){
		
		if(!running){
			return ;
		}
		running = false ;
		try {
			thread.join() ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1) ;
	}
	
	public void run(){
		
		init() ;
		
		long lastTime = System.nanoTime() ;
		final double ammountOfTicks = 120.0 ;
		double ns = 1000000000 / ammountOfTicks ;
		double delta = 0 ;
		int updates = 0 ;
		int frames = 0 ;
		long timer = System.currentTimeMillis() ;		
		while(running){
			long now = System.nanoTime() ;
			delta += (now - lastTime) / ns ;
			lastTime = now ;
			if (delta >= 1){
				tick() ;
				updates++ ;
				delta-- ;
			}
			render() ;
			frames++ ;
			
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000 ;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0 ;
				frames = 0 ;
			}
		}
		stop() ;
	}
	
	private void tick(){
		if (State == STATE.GAME){
			p.tick();
			c.tick() ;
		}
	}
	
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy() ;
		
		if (bs == null){
			createBufferStrategy(3) ;
			return ;
		}
		
		Graphics g = bs.getDrawGraphics() ;
		/////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this) ;
		
		g.drawImage(background,0,0,null) ;

		if (State == STATE.GAME){
			p.render(g);
			c.render(g);
		} else if (State == STATE.MENU){
			menu.render(g);
		}
		////////////
		g.dispose() ;
		bs.show() ;

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode() ;
		if (State == STATE.GAME){
			if (key == KeyEvent.VK_D) {
				p.setVelX(2) ;
				rightPressed = true ;
			} else if (key == KeyEvent.VK_A) {
				p.setVelX(-2) ;
				leftPressed = true ;
			} else if (key == KeyEvent.VK_S) {
				p.setVelY(2) ;
				downPressed = true ;
			} else if (key == KeyEvent.VK_W) {
				p.setVelY(-2) ;
				upPressed = true ;
			} else if (key == KeyEvent.VK_SPACE && !is_shooting) {
				c.addEntity(new Bullet(p.getX()+17,p.getY(),tex, this));
				is_shooting = true ;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode() ;

		if (key == KeyEvent.VK_D) {
			rightPressed = false ;
			if (!leftPressed) {
				p.setVelX(0);
			} else {
				p.setVelX(-2) ;
			}
		} else if (key == KeyEvent.VK_A) {
			leftPressed = false ;
			if (!rightPressed) {
				p.setVelX(0);
			} else {
				p.setVelX(2) ;
			}
		} else if (key == KeyEvent.VK_S) {
			downPressed = false ;
			if (!upPressed) {
				p.setVelY(0);
			} else {
				p.setVelY(-2) ;
			}
		} else if (key == KeyEvent.VK_W) {
			upPressed = false ;
			if (!downPressed) {
				p.setVelY(0);
			} else {
				p.setVelY(2) ;
			}
		} else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false ;
		}
	}

	public static void main(String args[]) {

		Game game = new Game() ;

		game.setPreferredSize(new Dimension(WIDTH, HEIGHT)) ;
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT)) ;
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT)) ;
		
		JFrame frame = new JFrame(game.TITLE) ;
		frame.add(game) ;
		frame.pack() ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		frame.setResizable(false) ;
		frame.setLocationRelativeTo(null) ;
		frame.setVisible(true) ;
		
		game.start() ;
				
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet ;
	}

}
