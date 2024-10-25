package io.github.OPTCGSIM.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameBoard implements Screen {

    private static final int VIRTUAL_WIDTH = 1920;  // Virtual screen width
    private static final int VIRTUAL_HEIGHT = 1080; // Virtual screen height

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture cardBackTexture;
    private Viewport viewport;


    private static final int BOARD_PADDING = 50;

    // Zone dimensions (these will be scaled based on viewport)
    private final int zoneWidth = 200;
    private final int zoneHeight = 150;

    private final Vector2 player1LifeZone = new Vector2(BOARD_PADDING, BOARD_PADDING + 300);
    private final Vector2 player1DeckZone = new Vector2(BOARD_PADDING + 250, BOARD_PADDING + 300);
    private final Vector2 player1HandZone = new Vector2(BOARD_PADDING, BOARD_PADDING);
    private final Vector2 player1CharacterZone = new Vector2(BOARD_PADDING + 250, BOARD_PADDING + 100);
    private final Vector2 player1DonDeckZone = new Vector2(BOARD_PADDING + 500, BOARD_PADDING + 300);
    private final Vector2 player1LeaderZone = new Vector2(BOARD_PADDING + 750, BOARD_PADDING + 100);
    private final Vector2 player1DonZone = new Vector2(BOARD_PADDING + 500, BOARD_PADDING);
    private final Vector2 player1TrashZone = new Vector2(BOARD_PADDING + 750, BOARD_PADDING + 300);

    private final Vector2 player2LifeZone = new Vector2(BOARD_PADDING, VIRTUAL_HEIGHT - BOARD_PADDING - 300);
    private final Vector2 player2DeckZone = new Vector2(BOARD_PADDING + 250, VIRTUAL_HEIGHT - BOARD_PADDING - 300);
    private final Vector2 player2HandZone = new Vector2(BOARD_PADDING, VIRTUAL_HEIGHT - BOARD_PADDING - 100);
    private final Vector2 player2CharacterZone = new Vector2(BOARD_PADDING + 250, VIRTUAL_HEIGHT - BOARD_PADDING - 200);
    private final Vector2 player2DonDeckZone = new Vector2(BOARD_PADDING + 500, VIRTUAL_HEIGHT - BOARD_PADDING - 300);
    private final Vector2 player2LeaderZone = new Vector2(BOARD_PADDING + 750, VIRTUAL_HEIGHT - BOARD_PADDING - 200);
    private final Vector2 player2DonZone = new Vector2(BOARD_PADDING + 500, VIRTUAL_HEIGHT - BOARD_PADDING - 100);
    private final Vector2 player2TrashZone = new Vector2(BOARD_PADDING + 750, VIRTUAL_HEIGHT - BOARD_PADDING - 300);

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        cardBackTexture = new Texture("card_back.png"); 
    }



    @Override
    public void render(float delta) {

        viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        viewport.apply(); // Apply the viewport

        // Clear the screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the viewport and set the projection matrix
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        // Draw the game board zones
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Draw zones for Player 1 (bottom half)
        drawZone(player1LifeZone, "Life Zone", shapeRenderer);
        drawZone(player1DeckZone, "Deck Zone", shapeRenderer);
        drawZone(player1HandZone, "Hand Zone", shapeRenderer);
        drawZone(player1CharacterZone, "Character Zone", shapeRenderer);
        drawZone(player1DonDeckZone, "Don Deck", shapeRenderer);
        drawZone(player1LeaderZone, "Leader Zone", shapeRenderer);
        drawZone(player1DonZone, "Don Zone", shapeRenderer);
        drawZone(player1TrashZone, "Trash Zone", shapeRenderer);

        // Draw zones for Player 2 (top half)
        drawZone(player2LifeZone, "Life Zone", shapeRenderer);
        drawZone(player2DeckZone, "Deck Zone", shapeRenderer);
        drawZone(player2HandZone, "Hand Zone", shapeRenderer);
        drawZone(player2CharacterZone, "Character Zone", shapeRenderer);
        drawZone(player2DonDeckZone, "Don Deck", shapeRenderer);
        drawZone(player2LeaderZone, "Leader Zone", shapeRenderer);
        drawZone(player2DonZone, "Don Zone", shapeRenderer);
        drawZone(player2TrashZone, "Trash Zone", shapeRenderer);

        shapeRenderer.end();

    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport based on the window size
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        cardBackTexture.dispose();
    }

    // Function to draw a zone rectangle
    private void drawZone(Vector2 zonePosition, String label, ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(zonePosition.x+400, zonePosition.y, zoneWidth, zoneHeight);
    }


}
