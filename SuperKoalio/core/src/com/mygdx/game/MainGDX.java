/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.MainScreen;

public class MainGDX extends Game {
	public void create() {
		this.setScreen(new MainScreen());
	}
}
