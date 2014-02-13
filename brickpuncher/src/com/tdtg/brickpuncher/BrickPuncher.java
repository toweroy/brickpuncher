package com.tdtg.brickpuncher;

import com.badlogic.gdx.Game;

public class BrickPuncher extends Game {
	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
