package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB{
	
	private Textures tex ;
	private Game game ;
	private Controller c ;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game) {
	
		super(x,y) ;
		this.tex = tex ;
		this.c = c ;
		this.game = game ;
	}
	
	public void tick() {
		y += 0.1 ;
		//x += 0.2 ;
		
		if (Physics.Collision(this, game.ea)){
			c.removeEntity(this);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null) ;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 53, 47) ;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
}
