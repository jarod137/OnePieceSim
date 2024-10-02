package io.github.OPTCGSIM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameBoard implements Screen {

    private SpriteBatch batch;
    private Texture cardBackTexture;
    private Array<Card> playerHand;
    private Array<Card> playingField;
    private Deck deck;

    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 150;

    @Override
    public void show() {
        // Initialize your screen here.
        batch = new SpriteBatch();
        cardBackTexture = new Texture("card_back.png"); // Assume a card back texture in assets

        // Initialize the deck and hands
        deck = new Deck(cardBackTexture);
        playerHand = new Array<>();
        playingField = new Array<>();

        // Draw initial hand of 5 cards
        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.drawCard());
        }
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a dark green background color
        Gdx.gl.glClearColor(0.0f, 0.5f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw cards
        batch.begin();

        // Draw the player's hand
        for (int i = 0; i < playerHand.size; i++) {
            Card card = playerHand.get(i);
            batch.draw(card.texture, 50 + i * (CARD_WIDTH + 10), 50, CARD_WIDTH, CARD_HEIGHT);
        }

        // Draw the playing field
        for (int i = 0; i < playingField.size; i++) {
            Card card = playingField.get(i);
            batch.draw(card.texture, 200 + i * (CARD_WIDTH + 10), 300, CARD_WIDTH, CARD_HEIGHT);
        }

        batch.end();

        // Handle input (drag and drop, clicks, etc.)
        handleInput();
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
        batch.dispose();
        cardBackTexture.dispose();
        for (Card card : playerHand) {
            card.dispose();
        }
        for (Card card : playingField) {
            card.dispose();
        }
    }

    private void handleInput() {
        // Basic input handling for dragging and dropping cards
        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            for (int i = 0; i < playerHand.size; i++) {
                Card card = playerHand.get(i);
                if (card.contains(touchPos)) {
                    playingField.add(playerHand.removeIndex(i));
                    break;
                }
            }
        }
    }

    // Basic Card class to represent cards in hand or deck
    class Card {
        Texture texture;
        Vector2 position;

        Card(Texture texture, Vector2 position) {
            this.texture = texture;
            this.position = position;
        }

        void dispose() {
            texture.dispose();
        }

        boolean contains(Vector2 point) {
            return point.x >= position.x && point.x <= position.x + CARD_WIDTH &&
                   point.y >= position.y && point.y <= position.y + CARD_HEIGHT;
        }
    }

    // Basic Deck class to manage cards
    class Deck {
        private Array<Card> cards;

        Deck(Texture cardBackTexture) {
            cards = new Array<>();
            for (int i = 0; i < 50; i++) { // Assume a deck of 50 cards
                cards.add(new Card(cardBackTexture, new Vector2(0, 0)));
            }
        }

        Card drawCard() {
            if (cards.size > 0) {
                return cards.pop();
            }
            return null; // Empty deck
        }
    }
}
