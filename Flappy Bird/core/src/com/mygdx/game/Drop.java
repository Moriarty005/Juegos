package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

public class Drop extends Game {

  	SpriteBatch batch;
	BitmapFont font;

	public void create() {
            
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		font = new BitmapFont(Gdx.files.internal("jojo.fnt"));
                
              try {
                  
                  this.setScreen(new MainMenuScreen(this));
                  
              } catch (SAXException ex) {
                  Logger.getLogger(Drop.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                  Logger.getLogger(Drop.class.getName()).log(Level.SEVERE, null, ex);
              }
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}