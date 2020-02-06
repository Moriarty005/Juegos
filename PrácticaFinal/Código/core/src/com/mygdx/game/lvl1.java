/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author lolac
 */
public class lvl1 implements Screen{
    
    Stage stage;
    TiledMap mapa;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    
    final practicaFinal pf;
    
    lvl1(final practicaFinal pf){
        this.pf = pf;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
    }
    
    
    @Override
    public void show() {

        mapa = new TmxMapLoader().load("MainMap.tmx");
        final float pixelsPerTile = 1.28f;
        renderer = new OrthogonalTiledMapRenderer(mapa, 1 / pixelsPerTile);
        
        
        
        stage = new Stage();
        stage.getViewport().setCamera(camera);
        
        
    }

    @Override
    public void render(float delta) {
        camera.update();
        this.pf.batch.setProjectionMatrix(camera.combined);        
        
        renderer.setView(camera);
        renderer.render();

        stage.act(delta);
        stage.draw();
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
