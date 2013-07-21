package com.karoshi.androidgames.ponkout;

public class Ball {
	
	// Center coordinates
    private float fXCoord, fYCoord;
    // Dimensions of the ball
    private float fWidth, fHeight;
    
    private Movement mVector;
    
    // Directions of movement
    private boolean bMoveRight, bMoveDown;
    
    public Ball(float x, float y, float w, float h, float speed) {       
        this.fXCoord = x;
        this.fYCoord = y;
        this.fWidth = w;
        this.fHeight = h;
        this.mVector = new Movement(speed, speed);
        bMoveRight = true;
        bMoveDown = true;
    }
    
    public float getX() {
    	return fXCoord;
    }
    
    public float getY() {
    	return fYCoord;
    }
    
    public float getWidth() {
    	return fWidth;
    }
    
    public float getHeight() {
    	return fHeight;
    }
    
    public void setCoords(float x, float y) {
    	this.fXCoord = x;
    	this.fYCoord = y;
    }
    
    public boolean isMovingRight() {
    	return bMoveRight;
    }
    
    public boolean isMovingDown() {
    	return bMoveDown;
    }
    
    public void setMoveRight(boolean direction) {
    	this.bMoveRight = direction;
    }
    
    public void setMoveDown(boolean direction) {
    	this.bMoveDown = direction;
    }
    
    public Movement getMovement() {
    	return this.mVector;
    }
}       

