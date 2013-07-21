package com.karoshi.androidgames.ponkout;

public class Paddle {
	// Center coordinates
    private float fXCoord, fYCoord;
    // Dimensions of the paddle
    private float fWidth, fHeight;
    
    public Paddle(float x, float y, float w, float h) {        
        this.fXCoord = x;
        this.fYCoord = y;
        this.fWidth = w;
        this.fHeight = h;
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
    
    public void setX(float x) {
    	this.fXCoord = x;
    } 
}
