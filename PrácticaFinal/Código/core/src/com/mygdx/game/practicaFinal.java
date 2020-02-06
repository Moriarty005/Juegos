package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

public class practicaFinal extends Game{
	SpriteBatch batch;
	BitmapFont font;

	public void create() {
            
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		font = new BitmapFont(Gdx.files.internal("jojo.fnt"));
                
                this.setScreen(new lvl1(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
