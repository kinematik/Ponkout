package com.karoshi.androidgames.ponkout;

import com.karoshi.androidgames.framework.Game;
import com.karoshi.androidgames.framework.Graphics;
import com.karoshi.androidgames.framework.Screen;
import com.karoshi.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

		// Load the images
		Assets.pmBackground = g.newPixmap("Background.png", PixmapFormat.RGB565);
		Assets.pmMarble = g.newPixmap("marble.png", PixmapFormat.ARGB4444);
		Assets.pmPaddle = g.newPixmap("paddle.png", PixmapFormat.ARGB4444);
		
		// Load the sounds
		Assets.sClick = game.getAudio().newSound("click.ogg");

		game.setScreen(new GameScreen(game));
	}

	@Override
	public void present(float deltaTime) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}