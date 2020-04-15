package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage player, bullet, enemy, title, play1, play2, settings1, settings2, exit1, exit2 ;
	
	private SpriteSheet ss ;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet()) ;
		
		getTextures() ;
		
	}
	
	private void getTextures() {
		player = ss.grabImage(0, 0, 37, 85) ;
		bullet = ss.grabImage(0, 327, 3, 10) ;
		enemy = ss.grabImage(0, 172, 53, 47) ;
		title = ss.grabImage(0, 338, 364, 148) ;
		play1 = ss.grabImage(0, 487, 203, 64) ;
		play2 = ss.grabImage(204, 487, 203, 64) ;
		settings1 = ss.grabImage(0, 552, 360, 64) ;
		settings2 = ss.grabImage(361, 552, 360, 64) ;
		exit1 = ss.grabImage(0, 617, 180, 64) ;
		exit2 = ss.grabImage(181, 617, 180, 64) ;
		
	}
}
