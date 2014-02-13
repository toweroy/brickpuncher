package com.tdtg.brickpuncher.controller;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.tdtg.brickpuncher.EnemyBlock;
import com.tdtg.brickpuncher.Player;
import com.tdtg.brickpuncher.World;

public class EnemyBlockController {
	private World world;

	public EnemyBlockController(World world) {
		this.world = world;
	}

	public void update(float delta) {
		Iterator<EnemyBlock> enemyBlocks = world.getEnemyBlocks().iterator();

		while (enemyBlocks.hasNext()) {
			EnemyBlock enemyBlock = enemyBlocks.next();

			if (enemyBlock.getPosition().x > world.getWidth()) {
				Gdx.app.log(World.LOG_TAG, "EnemyBlock destroyed! score = "
						+ world.getPlayerScore());
				enemyBlock = null;
				enemyBlocks.remove();
			} else if (checkPlayerCollision(enemyBlock)) {
				enemyBlock = null;
				enemyBlocks.remove();
			} else if (checkPlayerHitBoxCollision(enemyBlock)) {
				enemyBlock = null;
				enemyBlocks.remove();
				world.increasePlayerScore(1);
			} else {
				enemyBlock.update(delta);
			}
		}
	}

	private boolean checkPlayerCollision(EnemyBlock enemyBlock) {
		Player player = world.getPlayer();
		float playerCenter = player.getPosition().x + player.getBounds().width
				/ 2;
		float enemyBlockX2 = enemyBlock.getPosition().x
				+ enemyBlock.getBounds().width;
		
		if (enemyBlock.getVelocity().x > 0) {
			if (enemyBlock.getPosition().x >= playerCenter
					- (player.getBounds().width / 2)) {
				// enemyBlock = null;
				Gdx.app.log(World.LOG_TAG, "Player collision on X1");
				return true;
			}
			
			if (enemyBlockX2 >= playerCenter - (player.getBounds().width / 2)) {
				// enemyBlock = null;
				Gdx.app.log(World.LOG_TAG, "Player collision on X2");
				return true;
			}
		}

		if (enemyBlock.getVelocity().x < 0) {
			if (enemyBlock.getPosition().x <= playerCenter
					+ (player.getBounds().width / 2)) {
				// enemyBlock = null;
				Gdx.app.log(World.LOG_TAG, "Player collision on X1");
				return true;
			}
			
			if (enemyBlockX2 <= playerCenter + (player.getBounds().width / 2)) {
				// enemyBlock = null;
				Gdx.app.log(World.LOG_TAG, "Player collision on X2");
				return true;
			}
		}

		return false;
	}

	private boolean checkPlayerHitBoxCollision(EnemyBlock enemyBlock) {
		Player player = world.getPlayer();
		float playerCenter = player.getPosition().x + player.getHitBox().width
				/ 2;
		float enemyBlockX2 = enemyBlock.getPosition().x
				+ enemyBlock.getBounds().width;
		
		if (enemyBlock.getVelocity().x > 0) {
			if (enemyBlock.getPosition().x >= playerCenter
					- (player.getHitBox().width / 2)) {
				// enemyBlock = null;
				Gdx.app.log(World.LOG_TAG, "Player collision on X1");
				return true;
			}
			
			if (enemyBlockX2 >= playerCenter - (player.getHitBox().width / 2)) {
				// enemyBlock = null;
				Gdx.app.log(World.LOG_TAG, "Player collision on X2");
				return true;
			}
		}

		if (enemyBlock.getVelocity().x < 0) {
			if (enemyBlock.getPosition().x <= playerCenter
					+ (player.getHitBox().width / 2)) {
				// enemyBlock = null;
				Gdx.app.log(World.LOG_TAG, "Player collision on X1");
				return true;
			}
			
			if (enemyBlockX2 <= playerCenter + (player.getHitBox().width / 2)) {
				// enemyBlock = null;
				Gdx.app.log(World.LOG_TAG, "Player collision on X2");
				return true;
			}
		}

		return false;
	}
}
