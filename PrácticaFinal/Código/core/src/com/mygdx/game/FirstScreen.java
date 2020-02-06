/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author lolac
 */
public class FirstScreen implements Screen{

    final practicaFinal pf;
    
    OrthographicCamera camera;
        
    Texture fondo;
    //Texture logo;
    
    BitmapFont font;
    
    public FirstScreen(final practicaFinal pf){
        
        this.pf = pf;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        fondo = new Texture(Gdx.files.internal("firstScreen.jpg"));

        //logo = new Texture(Gdx.files.internal("logo.png"));
        
        font = new BitmapFont(Gdx.files.internal("jojo.fnt"));

    }
    
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        camera.update();
        this.pf.batch.setProjectionMatrix(camera.combined);

        this.pf.batch.begin();
        this.pf.batch.draw(fondo, 0, 0, 800, 800);

        //this.pf.batch.draw(logo, 175, 600/2);
        this.font.draw(this.pf.batch, "Drop", 350, 600/2);

        //this.font.draw(this.pf.batch, "HighScore: " + highscore, 620/2, 400/2);

        this.font.draw(this.pf.batch, "Toca en cualquier lado para comenzar", 200 / 2, 100);
        this.pf.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                //this.pf.setScreen(new GameScreen(game));
                this.pf.setScreen(new FirstScreen(this.pf));
                this.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
}
