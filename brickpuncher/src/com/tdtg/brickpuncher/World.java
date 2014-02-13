package com.tdtg.brickpuncher;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	public static final String LOG_TAG = "BrickPuncher";
	/** The blocks making up the world **/
	List<Block> blocks = new ArrayList<Block>();
	List<EnemyBlock> enemyBlocks = new ArrayList<EnemyBlock>();

	/** Our player controlled hero **/
	Player player;
	/** A world has a level through which Bob needs to go through **/
	Level level;
	/** The collision boxes **/
	Array<Rectangle> collisionRects = new Array<Rectangle>();
	private long playerScore = 0;
	private int width, height;

	public Array<Rectangle> getCollisionRects() {
		return collisionRects;
	}

	public Level getLevel() {
		return level;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public List<EnemyBlock> getEnemyBlocks() {
		return enemyBlocks;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public long getPlayerScore() {
		return playerScore;
	}

	public void increasePlayerScore(long scoreIncrease) {
		this.playerScore += scoreIncrease;
	}

	public void decreasePlayerScore(long scoreDescrease) {
		if (this.playerScore > 0) {
			this.playerScore += scoreDescrease;
		}
	}

	public void addEnemyBlock(EnemyBlock enemyBlock) {
		this.enemyBlocks.add(enemyBlock);
	}

	/** Return only the blocks that need to be drawn **/
	public void getDrawableBlocks(int width, int height) {

		Block block;

		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				block = level.getBlocks()[col][row];

				if (block != null) {
					blocks.add(block);
				}
			}
		}
	}

	public World() {
		createWorld();
	}

	public void createWorld() {
		level = new Level();
		getDrawableBlocks(level.getWidth(), level.getHeight());
		player = new Player(new Vector2(-Player.X_SIZE, 0));
	}
}
