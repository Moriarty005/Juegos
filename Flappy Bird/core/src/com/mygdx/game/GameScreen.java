/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
  	final Drop game;

	Texture dropImage1;
        Texture dropImage2;
	Texture bucketImage;
        Texture bucket_up;
        Texture bucket_down;
        Texture fondo;
        
        Sound saltito;
	Music rainMusic;
	OrthographicCamera camera;
	Rectangle bucket;
	Array<Rectangle> raindrops;
	long lastDropTime;
	int dropsGathered;
        
        int separacion;
        
        int tiempo_de_aparicion;
        
        double res;
        
        final float GRAVITY = -30f;
        float yVelocity = 350;
        final float DAMPING = 0.87f;
        final float MAX_VELOCITY = 10f;

	public GameScreen(final Drop gam) {
		this.game = gam;

                fondo = new Texture(Gdx.files.internal("fondo.jpg"));
                
		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage1 = new Texture(Gdx.files.internal("bottomtube.png"));
                
                dropImage2 = new Texture(Gdx.files.internal("toptube.png"));
                
		bucketImage = new Texture(Gdx.files.internal("cucumbo.png"));
                bucket_up = new Texture(Gdx.files.internal("cucumbo_up.png"));
                bucket_down = new Texture(Gdx.files.internal("cucumbo_down.png"));
                

		// load the drop sound effect and the rain background "music"
		saltito = Gdx.audio.newSound(Gdx.files.internal("saltito.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.setLooping(true);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.y = 480 / 2 - 64 / 2; // center the bucket horizontally
		bucket.x = 50; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		bucket.width = 64;
		bucket.height = 64;
                
                this.tiempo_de_aparicion = 1470000000;
                
                separacion = 600;
                res = 0.0;
                
		// create the raindrops array and spawn the first raindrop
		raindrops = new Array<Rectangle>();
		spawnRaindrop();

	}

	private void spawnRaindrop() {
            Rectangle raindrop = new Rectangle();

            raindrop.y = MathUtils.random(75, 420);
            raindrop.y -= 545;
            raindrop.x = 800;
            raindrop.width = 64;
            raindrop.height = 425;
            raindrops.add(raindrop);
            lastDropTime = TimeUtils.nanoTime();

            Rectangle raindrop2 = new Rectangle();
            raindrop2.y = raindrop.y + separacion;
            raindrop2.x = 800;
            raindrop2.width = 64;
            raindrop2.height = 425;
            raindrops.add(raindrop2);

	}

	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		game.batch.begin();
                game.batch.draw(fondo, 0, 0, 800, 480);
		game.font.draw(game.batch, "Tubos pasados: " +  (int) res, 0, 480);
		//game.batch.draw(bucketImage, bucket.x, bucket.y);
                
                for(int index = 0; index < raindrops.size; index++){
                    
                    if(index % 2 == 0){
                        game.batch.draw(dropImage1, raindrops.get(index).x, raindrops.get(index).y);
                    }else{
                        game.batch.draw(dropImage2, raindrops.get(index).x, raindrops.get(index).y);
                    }
                }
                
		/*for (Rectangle raindrop : raindrops) {
                    
			game.batch.draw(dropImage2, raindrop.x, raindrop.y);
                        //game.batch.draw(dropImage1, raindrop.x, raindrop.y);
                        //raindrop.y += 64;
		}*/
                if(yVelocity > 0){
                    game.batch.draw(bucket_up, bucket.x, bucket.y);
                }else if(yVelocity == 0){
                    game.batch.draw(bucketImage, bucket.x, bucket.y);
                }else if(yVelocity < 0){
                    game.batch.draw(bucket_down, bucket.x, bucket.y);
                }
                
		game.batch.end();

		// process user input
		if (Gdx.input.justTouched()) {
			yVelocity = 450;
                        saltito.play((float) 0.12);
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
			yVelocity = 450;
                        saltito.play((float) 0.12);
                }
                
                yVelocity = yVelocity + GRAVITY;
                
                float y = bucket.getY();
                float yChange = yVelocity * delta;
               
                
                bucket.setPosition(bucket.x, y + yChange);
                
                
                if (Math.abs(yVelocity) < 0.5f) {
                    yVelocity = 0;
                }

		// make sure the bucket stays within the screen bounds
		if (bucket.y < 0){
                    this.rainMusic.pause();
                    this.rainMusic.dispose();
                    bucket.y = 0;
                    game.setScreen(new DeadMenuScreen(game, (int)res));
                }
                
		if (bucket.y > 480 - 64){
                    this.rainMusic.pause();
                    this.rainMusic.dispose();
                    bucket.y = 480 - 64;
                    game.setScreen(new DeadMenuScreen(game, (int)res));
                }
                
                /*if(this.dropsGathered > 15){
                    this.tiempo_de_aparicion -= 300000000;
                }*/
                
                
		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > this.tiempo_de_aparicion)
			spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		Iterator<Rectangle> iter = raindrops.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.x -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.x + 64 < 0){
                            
                            iter.remove();
                            this.dropsGathered++;
                            
                            res = dropsGathered/2;
                            
                            if(this.res > 2){
                                this.tiempo_de_aparicion = 1100000000;
                            }
                        }
                        
			if (raindrop.overlaps(bucket)) {
                            
                            this.rainMusic.pause();
                            this.rainMusic.dispose();
                            
                            iter.remove();
                            
                            game.setScreen(new DeadMenuScreen(game, (int)res));
			}
                        
                        
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		rainMusic.play();
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
		dropImage1.dispose();
		bucketImage.dispose();
		saltito.dispose();
		rainMusic.dispose();
	}

}
