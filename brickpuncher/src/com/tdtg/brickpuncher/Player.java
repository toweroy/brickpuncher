package com.tdtg.brickpuncher;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {

	public enum State {
		IDLE, PUNCHING
	}

	public static final float X_SIZE = 56f;
	public static final float Y_SIZE = 63f;
	public static final float HIT_BOX_PERCENTAGE = 50;

	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2(100, 0);
	Rectangle bounds = new Rectangle();
	Rectangle hitBox = new Rectangle();
	State state = State.IDLE;
	boolean facingLeft = true;
	boolean uppercut = false;
	boolean punching = false;
	float stateTime = 0;

	public Player(Vector2 position) {
		this.position = position;
		this.bounds.width = X_SIZE;
		this.bounds.height = Y_SIZE;
		this.hitBox.width = X_SIZE + X_SIZE * (HIT_BOX_PERCENTAGE / 100);
		this.hitBox.height = Y_SIZE + Y_SIZE * (HIT_BOX_PERCENTAGE / 100);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public void setState(State newState) {
		this.state = newState;
	}

	public void update(float delta) {
		stateTime += delta;
	}

	public void setFacingLeft(boolean b) {
		this.facingLeft = b;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}

	public boolean isPunching() {
		return punching;
	}

	public void setPunching(boolean punching) {
		this.punching = punching;
	}

	public boolean isUppercuting() {
		return uppercut;
	}

	public void setUppercuting(boolean uppercuting) {
		this.uppercut = uppercuting;
	}

	public State getState() {
		return state;
	}

	public float getStateTime() {
		return stateTime;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
}
