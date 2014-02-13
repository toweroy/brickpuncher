package com.tdtg.brickpuncher;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyBlock {
	public static final float SIZE = 116f;

	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	float stateTime = 0;
	
	public EnemyBlock(Vector2 pos, Vector2 velocity) {
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.velocity = velocity;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void update(float delta) {
		stateTime += delta;
		position.add(velocity.cpy().scl(delta));
	}
}
