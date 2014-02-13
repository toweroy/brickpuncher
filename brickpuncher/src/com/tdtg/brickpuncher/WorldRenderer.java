package com.tdtg.brickpuncher;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tdtg.brickpuncher.Player.State;

public class WorldRenderer {

	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	// Textures
	private TextureRegion playerIdle;
	private TextureRegion playerPunchRight;
	private TextureRegion playerunchLeft;
	private TextureRegion playerUppercut;
	private TextureRegion bobFrame;
	private TextureRegion blockTexture;
	private SpriteBatch spriteBatch;

	private boolean debug = false;
	private int width;
	private int height;

	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}

	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(width, height);
		this.cam.position.set(width / 2f, height / 2f, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}

	private void loadTextures() {
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("images/textures.pack"));
		playerIdle = atlas.findRegion("idle");
		playerPunchRight = atlas.findRegion("punch");
		playerunchLeft = new TextureRegion(playerPunchRight);
		playerunchLeft.flip(true, false);
		playerUppercut = atlas.findRegion("uppercut");
		blockTexture = atlas.findRegion("staypuft");
	}
	
	public void render() {
		spriteBatch.begin();
		drawBlocks();
		drawPlayer();
		drawEnemyBlocks();
		spriteBatch.end();

		if (debug) {
			drawDebug();
		}
	}

	private void drawBlocks() {
		for (Block block : world.getBlocks()) {
			spriteBatch.draw(blockTexture, block.getPosition().x,
					block.getPosition().y, Block.SIZE, Block.SIZE);
		}
	}

	private void drawPlayer() {
		
		Player bob = world.getPlayer();

		if (bob.isUppercuting()) {
			bobFrame = playerUppercut;
		} else if (bob.isPunching()) {
			if (bob.isFacingLeft()) {
				bobFrame = playerunchLeft;
			} else {
				bobFrame = playerPunchRight;
			}
		} else {
			bobFrame = playerIdle;
		}

		spriteBatch.draw(bobFrame, bob.getPosition().x, bob.getPosition().y, Player.X_SIZE, Player.Y_SIZE);
	}

	public void drawEnemyBlocks() {
		Iterator<EnemyBlock> enemyBlocks = world.getEnemyBlocks().iterator();

		while (enemyBlocks.hasNext()) {
			EnemyBlock enemyBlock = enemyBlocks.next();
			spriteBatch.draw(blockTexture, enemyBlock.getPosition().x,
					enemyBlock.getPosition().y, Block.SIZE, Block.SIZE);
		}
	}

	public void drawDebug() {
		// render blocks
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		for (Block block : world.getBlocks()) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render Bob
		Player bob = world.getPlayer();
		Rectangle rect = bob.getBounds();
		float x1 = bob.getPosition().x + rect.x;
		float y1 = bob.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
}
