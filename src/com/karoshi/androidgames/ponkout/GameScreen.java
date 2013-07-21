package com.karoshi.androidgames.ponkout;

import java.util.List;

import android.graphics.Color;

import com.karoshi.androidgames.framework.Game;
import com.karoshi.androidgames.framework.Graphics;
import com.karoshi.androidgames.framework.Input.TouchEvent;
import com.karoshi.androidgames.framework.Pixmap;
import com.karoshi.androidgames.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";

	public GameScreen(Game game) {
		super(game);
		world = new World();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		if (state == GameState.Ready)
			updateReady(touchEvents);

		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);

		// Currently not using paused and game over states
		/*
		 * if (state == GameState.Paused) updatePaused(touchEvents); if (state
		 * == GameState.GameOver) updateGameOver(touchEvents);
		 */
	}

	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			// Handle the paddle controls
			Paddle p = world.pPaddle;
			if (event.type == TouchEvent.TOUCH_DRAGGED) {
				// Only drag paddle when touching the boundaries of the control
				// area
				if (event.y < 480 && event.y > 400) {
					// Check if ends of paddle are moving out of the x
					// boundaries
					if ((event.x - p.getWidth() / 2) < 0) {
						p.setX(40);
					} else if ((event.x + p.getWidth() / 2) > 320) {
						p.setX(280);
					} else
						p.setX(event.x);
				}
			}
		}
		
		// Check for ball bounce and play sound
		if (world.bounce)
			Assets.sClick.play(1);
		
		world.update(deltaTime);
	}

	/*
	 * private void updatePaused(List<TouchEvent> touchEvents) { int len =
	 * touchEvents.size(); for (int i = 0; i < len; i++) { TouchEvent event =
	 * touchEvents.get(i); if (event.type == TouchEvent.TOUCH_UP) { if (event.x
	 * > 80 && event.x <= 240) { if (event.y > 100 && event.y <= 148) {
	 * 
	 * Assets.click.play(1); state = GameState.Running; return; } if (event.y >
	 * 148 && event.y < 196) {
	 * 
	 * Assets.click.play(1); game.setScreen(new MainMenuScreen(game)); return; }
	 * } } } }
	 * 
	 * private void updateGameOver(List<TouchEvent> touchEvents) { int len =
	 * touchEvents.size(); for (int i = 0; i < len; i++) { TouchEvent event =
	 * touchEvents.get(i); if (event.type == TouchEvent.TOUCH_UP) { if (event.x
	 * >= 128 && event.x <= 192 && event.y >= 200 && event.y <= 264) {
	 * 
	 * Assets.click.play(1); game.setScreen(new MainMenuScreen(game)); return; }
	 * } } }
	 */

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.pmBackground, 0, 0);
		drawWorld(world);

		/*
		 * Drawing UIs, not used
		 * 
		 * if (state == GameState.Ready) drawReadyUI(); if (state ==
		 * GameState.Running) drawRunningUI(); if (state == GameState.Paused)
		 * drawPausedUI(); if (state == GameState.GameOver) drawGameOverUI();
		 * 
		 * drawText(g, score, g.getWidth() / 2 - score.length() * 20 / 2,
		 * g.getHeight() - 42);
		 */

	}

	private void drawWorld(World world) {
		Graphics g = game.getGraphics();

		// Draw the paddle
		Paddle p = world.pPaddle;
		g.drawPixmap(Assets.pmPaddle, (int) (p.getX() - (p.getWidth() / 2)),
				(int) (p.getY() - (p.getHeight() / 2)));

		// Draw the marble
		Ball b = world.bMarble;
		g.drawPixmap(Assets.pmMarble, (int) (b.getX() - (b.getWidth() / 2)),
				(int) (b.getY() - (b.getHeight() / 2)));
	}

	/*
	 * private void drawReadyUI() { Graphics g = game.getGraphics();
	 * 
	 * g.drawPixmap(Assets.ready, 47, 100); g.drawLine(0, 416, 480, 416,
	 * Color.BLACK); }
	 * 
	 * private void drawRunningUI() { Graphics g = game.getGraphics();
	 * 
	 * g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64); g.drawLine(0, 416,
	 * 480, 416, Color.BLACK); g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64,
	 * 64); g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64); }
	 * 
	 * private void drawPausedUI() { Graphics g = game.getGraphics();
	 * 
	 * g.drawPixmap(Assets.pause, 80, 100); g.drawLine(0, 416, 480, 416,
	 * Color.BLACK); }
	 * 
	 * private void drawGameOverUI() { Graphics g = game.getGraphics();
	 * 
	 * g.drawPixmap(Assets.gameOver, 62, 100); g.drawPixmap(Assets.buttons, 128,
	 * 200, 0, 128, 64, 64); g.drawLine(0, 416, 480, 416, Color.BLACK); }
	 * 
	 * public void drawText(Graphics g, String line, int x, int y) { int len =
	 * line.length(); for (int i = 0; i < len; i++) { char character =
	 * line.charAt(i);
	 * 
	 * if (character == ' ') { x += 20; continue; }
	 * 
	 * int srcX = 0; int srcWidth = 0; if (character == '.') { srcX = 200;
	 * srcWidth = 10; } else { srcX = (character - '0') * 20; srcWidth = 20; }
	 * 
	 * g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32); x += srcWidth;
	 * } }
	 */

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}