/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;

/**
 *
 * @author lolac
 */
public class DeadMenuScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;

    int puntuasion;

    Texture fondo;

    public DeadMenuScreen(final Drop gam, int highscore) {
            game = gam;

            puntuasion = highscore;
            if(this.puntuasion > MainMenuScreen.highscore){
                MainMenuScreen.highscore = this.puntuasion;
                MainMenuScreen.convertirAXML();
            }

            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 480);

            fondo = new Texture(Gdx.files.internal("fondo_dead_menu.jpg"));

    }

    @Override
    public void render(float delta) {
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();
            game.batch.setProjectionMatrix(camera.combined);

            game.batch.begin();
            game.batch.draw(fondo, 0, 0, 800, 480);
            game.font.draw(game.batch, "¡Has perdido!", 300, 800/2);
            game.font.draw(game.batch, "Score: " + puntuasion, 620 / 2, 100);
            game.font.draw(game.batch, "Toca en cualquier lado para reintentar", 200 / 2, 250);
            game.batch.end();

            if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    game.setScreen(new GameScreen(game));
                    dispose();
            }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
