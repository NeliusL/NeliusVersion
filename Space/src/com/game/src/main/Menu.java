package com.game.src.main;

import java.awt.Graphics;

public class Menu {
	
	private Textures tex ;

	public Menu(Textures tex) {
		
		this.tex = tex ;
	}
	
	public void render(Graphics g, int state){
		g.drawImage(tex.title, 168, 50, null) ;
		if (state == 1) {
			g.drawImage(tex.play2, 248, 300, null) ;
			g.drawImage(tex.settings1, 170, 500, null) ;
			g.drawImage(tex.exit1, 260, 700, null) ;
		} else if (state == 2) {
			g.drawImage(tex.play1, 248, 300, null) ;
			g.drawImage(tex.settings2, 170, 500, null) ;
			g.drawImage(tex.exit1, 260, 700, null) ;
		} else if (state == 3) {
			g.drawImage(tex.play1, 248, 300, null) ;
			g.drawImage(tex.settings1, 170, 500, null) ;
			g.drawImage(tex.exit2, 260, 700, null) ;
		}
	}
	
}
