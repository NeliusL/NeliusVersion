package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage player, bullet, enemy ;
	
	private SpriteSheet ss ;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet()) ;
		
		getTextures() ;
		
	}
	
	private void getTextures() {
		player = ss.grabImage(0, 0, 37, 85) ;
		bullet = ss.grabImage(0, 327, 3, 10) ;
		enemy = ss.grabImage(0, 172, 53, 47) ;
	}
}
