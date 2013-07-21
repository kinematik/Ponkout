package com.karoshi.androidgames.ponkout;

import android.graphics.Rect;

public class World {
	static final int WORLD_WIDTH = 320;
	static final int WORLD_HEIGHT = 480;

	static final int SCORE_INCREMENT = 10;
	static final float BALL_SPEED = 300;

	public Rect rPaddle;
	public Rect rMarble;
	public Rect rWorld;

	public Paddle pPaddle;
	public Ball bMarble;

	public boolean gameOver = false;
	public boolean bounce = false;
	public int score = 0;

	public World() {
		// Initial locations of the paddle and ball
		pPaddle = new Paddle(160, 435, 80, 25);
		rPaddle = new Rect();
		bMarble = new Ball(160, 240, 30, 30, BALL_SPEED);
		rMarble = new Rect();
		rWorld = new Rect(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
	}

	public void update(float deltaTime) {
		if (gameOver)
			return;

		bounce = false;

		float fBallX = bMarble.getX();
		float fBallY = bMarble.getY();

		rMarble.set(((int) bMarble.getX() - (int) bMarble.getWidth() / 2),
				((int) bMarble.getY() - (int) bMarble.getHeight() / 2),
				((int) bMarble.getX() + (int) bMarble.getWidth() / 2),
				((int) bMarble.getY() + (int) bMarble.getHeight() / 2));

		rPaddle.set(((int) pPaddle.getX() - (int) pPaddle.getWidth() / 2),
				((int) pPaddle.getY() - (int) pPaddle.getHeight() / 2),
				((int) pPaddle.getX() + (int) pPaddle.getWidth() / 2),
				((int) pPaddle.getY() + (int) pPaddle.getHeight() / 2));

		if (bMarble.getMovement().getxDirection() == Movement.DIRECTION_RIGHT &&
				isBounceRight()) {
			bMarble.getMovement().toggleXDirection();
			bounce = true;
		}

		if (bMarble.getMovement().getxDirection() == Movement.DIRECTION_LEFT &&
				isBounceLeft()) {
			bMarble.getMovement().toggleXDirection();
			bounce = true;
		}

		if (bMarble.getMovement().getyDirection() == Movement.DIRECTION_UP &&
				isBounceTop()) {
			bMarble.getMovement().toggleYDirection();
			bounce = true;
		}

		if (bMarble.getMovement().getyDirection() == Movement.DIRECTION_DOWN &&
				isBounceBottom()) {
			bMarble.getMovement().toggleYDirection();
			bounce = true;
		}
		
		if (Rect.intersects(rMarble, rPaddle)) {
			
			if (bMarble.getX() > pPaddle.getX())
				bMarble.getMovement().toggleXDirection();
			
			bMarble.getMovement().toggleYDirection();
			bounce = true;
		}
		
		fBallX += bMarble.getMovement().getxDirection() * 
				bMarble.getMovement().getXv() * deltaTime;
		
		fBallY += bMarble.getMovement().getyDirection() * 
				bMarble.getMovement().getYv() * deltaTime;
		
		bMarble.setCoords(fBallX, fBallY);
	}

	public boolean isBounceRight() {
		// Check right side of world
		return ((int) ((bMarble.getX() + bMarble.getWidth() / 2)) >= WORLD_WIDTH);
	}
	
	public boolean isBounceLeft() {
		// Check left side of world
		return ((int) ((bMarble.getX() - bMarble.getWidth() / 2)) <= 0);
	}
	
	public boolean isBounceTop() {
		// Check top of world
		return ((int) ((bMarble.getY() - bMarble.getHeight() / 2)) <= 0);	
	}
	
	public boolean isBounceBottom() {
		// Check bottom of world
		return ((int) ((bMarble.getY() + bMarble.getHeight() / 2)) >= WORLD_HEIGHT);	
	}
}
