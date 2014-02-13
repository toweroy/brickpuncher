package com.tdtg.brickpuncher.controller;

import com.badlogic.gdx.math.Vector2;
import com.tdtg.brickpuncher.EnemyBlock;
import com.tdtg.brickpuncher.World;

public class WorldController {
	private static final int MAX_ENEMY_BLOCKS = 1;
	private static final long ENEMY_BLOCKS_DELAY_SECS = 3;
	private long START_TIME = System.currentTimeMillis() / 1000;

	private World world;
	private long gameTime = 0;
	private boolean movePlayer = false;
	private float whereToX = 1;
	private int sign = 1;
	private float SPEED = 2;
	private long delayToBlock = 0;
	
	public WorldController(World world) {
		this.world = world;
	}

	public long getGameTime() {
		return gameTime;
	}

	public void updateGameTime() {
		gameTime = (System.currentTimeMillis() / 1000) - START_TIME;
	}

	public void update(float delta) {
//		if (!movePlayer) {
//			sign *= -1;
//			startMoving();
//		} else {
//			if ((sign > 0 && !(world.getPlayer().getPosition().x >= whereToX)) 
//					|| (sign < 0 && !(world.getPlayer().getPosition().x <= whereToX))) {
//				world.getPlayer().getPosition().add(world.getPlayer().getVelocity().cpy().scl(delta));
//			} else {
//				movePlayer = false;
//			}
//		}

		if (gameTime > 1 && delayToBlock <= 0) {
			if (world.getEnemyBlocks().size() < MAX_ENEMY_BLOCKS) {
				throwBlock();
			}
		}

		updateGameTime();
	}

	private void startMoving() {
		if (gameTime > 1) {
//			float sign = (float) whereToX;
//			sign = sign / sign;
			whereToX = world.getPlayer().getPosition().x + ((float) sign * ((world.getWidth() / 4)
					- EnemyBlock.SIZE));
			movePlayer = true;
		} else {
			return;
		}
	}

	private void throwBlock() {
		EnemyBlock enemyBlock = new EnemyBlock(new Vector2(-EnemyBlock.SIZE, EnemyBlock.SIZE),
				new Vector2(100, 0));
		world.addEnemyBlock(enemyBlock);
		delayToBlock = ENEMY_BLOCKS_DELAY_SECS;
	}
}
