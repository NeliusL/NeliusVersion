package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Controller {
	
	private LinkedList<EntityA> ea = new LinkedList<EntityA>() ;
	private LinkedList<EntityB> eb = new LinkedList<EntityB>() ;
	
	Game game ;
	Textures tex ;
	
	public Controller(Game game, Textures tex) {
		this.game = game ;
		this.tex = tex ;
		
		for (int x = 0 ; x < (Game.WIDTH -160) ; x+=70) {
			addEntity(new Enemy(x, 0, tex, this, game)) ;
		}
	}
	
	EntityA enta ;
	EntityB entb ;
	
	public void tick() {
		//A class
		for (int i = 0 ; i < ea.size() ; i++) {
			enta = ea.get(i) ;
			
			enta.tick();
		}
		//B class
				for (int i = 0 ; i < eb.size() ; i++) {
					entb = eb.get(i) ;
					
					entb.tick();
				}
	}
	public void render(Graphics g) {
		//A class
		for (int i = 0 ; i < ea.size() ; i++) {
			enta = ea.get(i) ;
			
			enta.render(g);
		}
		//B class
				for (int i = 0 ; i < eb.size() ; i++) {
					entb = eb.get(i) ;
					
					entb.render(g);
				}
	}
	
	public void addEntity(EntityA block) {
		
		ea.add(block) ;
		
	}
	
	public void removeEntity(EntityA block) {
		
		ea.remove(block) ;

	}

	public void addEntity(EntityB block) {

		eb.add(block) ;

	}

	public void removeEntity(EntityB block) {

		eb.remove(block) ;

	}

	public LinkedList<EntityA> getEntityA(){
		return ea ;
	}
	
	public LinkedList<EntityB> getEntityB(){
		return eb ;
	}

}
