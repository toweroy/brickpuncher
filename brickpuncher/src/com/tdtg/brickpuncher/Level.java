package com.tdtg.brickpuncher;

import com.badlogic.gdx.math.Vector2;

public class Level {

	private int width;
	private int height;
	private Block[][] blocks;

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

	public Block[][] getBlocks() {
		return blocks;
	}

	public void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
	}

	public Level() {
		loadDemoLevel();
	}

	public Block get(int x, int y) {
		return blocks[x][y];
	}

	private void loadDemoLevel() {
		loadFloor();
	}
	
	private void loadFloor() {
		width = 10;
		height = 1;
		
		blocks = new Block[width][height];
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				blocks[col][row] = null;
			}
		}

		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				blocks[col][row] = new Block(new Vector2(col * Block.SIZE, row * Block.SIZE));
			}
		}
	}
}
