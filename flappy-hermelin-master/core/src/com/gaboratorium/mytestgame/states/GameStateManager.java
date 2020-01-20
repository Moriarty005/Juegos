package com.gaboratorium.mytestgame.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by root on 3/6/17.
 */

public class GameStateManager
{
    private Stack<State> states;

    public GameStateManager()
    {
        states = new Stack<State>();
    }

    public void push(State state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop().dispose();

    }

    public void set(State state)
    {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt)
    {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }

    public State peek()
    {
        return states.peek();
    }
}
