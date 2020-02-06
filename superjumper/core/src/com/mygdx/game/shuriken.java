/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author lolac
 */
public class shuriken extends DynamicGameObject{
    public static final float SQUIRREL_WIDTH = 1;
    public static final float SQUIRREL_HEIGHT = 0.6f;
    public static final float SQUIRREL_VELOCITY_X = 3f;
    public static final float SQUIRREL_VELOCITY_Y = 3f;

    float stateTime = 0;

    public shuriken (float x, float y) {
            super(x, y, SQUIRREL_WIDTH, SQUIRREL_HEIGHT);
            velocity.set(SQUIRREL_VELOCITY_X, 0);
    }

    public void update (float deltaTime) {
            position.add(velocity.x * deltaTime, velocity.y * deltaTime);
            bounds.x = position.x - SQUIRREL_WIDTH / 2;
            bounds.y = position.y - SQUIRREL_HEIGHT / 2;

            if (position.x < SQUIRREL_WIDTH / 2) {
                    position.x = SQUIRREL_WIDTH / 2;
                    velocity.x = SQUIRREL_VELOCITY_X;
            }
            if (position.x > World.WORLD_WIDTH - SQUIRREL_WIDTH / 2) {
                    position.x = World.WORLD_WIDTH - SQUIRREL_WIDTH / 2;
                    velocity.x = -SQUIRREL_VELOCITY_X;
            }
            stateTime += deltaTime;
    }
}
