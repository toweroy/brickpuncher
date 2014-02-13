package com.tdtg.brickpuncher.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.tdtg.brickpuncher.Block;
import com.tdtg.brickpuncher.Player;
import com.tdtg.brickpuncher.World;

public class PlayerController {

	enum Keys {
		LEFT, RIGHT, UPPERCUT
	}

	private World world;
	private Player player;

	static Map<Keys, Boolean> keys = new HashMap<PlayerController.Keys, Boolean>();

	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UPPERCUT, false);
	};

	public PlayerController(World world) {
		this.world = world;
		this.player = world.getPlayer();
	}

	public void initializePlayerPosition() {
		world.getPlayer().setPosition(new Vector2((world.getWidth() / 2) - (Player.X_SIZE / 2), Block.SIZE)); 
	}
	
	// Key presses and touches
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void uppercutPressed() {
		keys.get(keys.put(Keys.UPPERCUT, true));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void uppercutReleased() {
		keys.get(keys.put(Keys.UPPERCUT, false));
	}

	/** The main update method **/
	public void update(float delta) {
		if (player != null) {
			processInput();
			player.update(delta);
		}
	}

	/** Change Bob's state and parameters based on input controls **/
	private boolean processInput() {
		if (keys.get(Keys.UPPERCUT)) {
			player.setUppercuting(true);
		} else if (keys.get(Keys.LEFT)) {
			// left is pressed
			player.setFacingLeft(true);
			player.setPunching(true);
		} else if (keys.get(Keys.RIGHT)) {
			// left is pressed
			player.setFacingLeft(false);
			player.setPunching(true);
		} else {
			player.setPunching(false);
			player.setUppercuting(false);
		}

		return false;
	}
}
