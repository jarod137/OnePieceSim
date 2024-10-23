package io.github.OPTCGSIM.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.OPTCGSIM.Main;
import io.github.OPTCGSIM.cards.Card;
import io.github.OPTCGSIM.cards.CardParse;
import io.github.OPTCGSIM.util.AssetHandler;

import java.util.ArrayList;

public class DeckBuilder extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private Main game;
    private AssetManager assetManager;
    private int columns = 7;

    private ArrayList<Card> deck = new ArrayList<>();

    public DeckBuilder(Main game) {
        this.game = game;
        this.assetManager = game.getAssets().gAssetManager();
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Ensure that the skin is loaded and assigned
        skin = assetManager.get(AssetHandler.SKIN);

        // Sets up the UI table layout
        Table table = new Table();
        table.setDebug(true);
        table.setSkin(skin);
        table.center();

        // Sets up the scroll view for the cards
        ScrollPane scrollPane = new ScrollPane(table, skin);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.layout();
        stage.addActor(scrollPane);

        // Create a button to return to the main menu
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TitleScreen(game, assetManager));
            }
        });

        float buttonWidth = Gdx.graphics.getWidth() * 0.2f;
        float buttonHeight = Gdx.graphics.getWidth() * 0.1f;

        table.add(backButton).width(buttonWidth).height(buttonHeight).colspan(7);
        table.row();

        //Fetches the cards from the JSON (Could probably move this somewhere else.
        CardParse parse = new CardParse();
        ArrayList<Card> cards = parse.parseJSON();
        cards = parse.filterCards(cards);

        for (int i = 1; i <= cards.size(); i++) {
            String texturePath = "cards/" + i + ".jpg";
            assetManager.load(texturePath, Texture.class);
        }

        assetManager.finishLoading();

        int index = 1;
        for (Card card : cards) {
            String textureKey = "card" + index;
            Texture texture = assetManager.get("cards/" + index + ".jpg", Texture.class);
            skin.add(textureKey, texture);
            Drawable drawable = skin.getDrawable(textureKey);
            ImageButton cardButton = new ImageButton(drawable);

            table.add(cardButton).width(buttonWidth).height(buttonHeight);

            //Formatting the layout
            if (index % columns == 0) {
                table.row();
            }

            //Makes each of the cards clickable (This is probably not optimal, but works for initial implementation...I think)
            cardButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println(card.getName() + " clicked!");
                    deck.add(card);
                }
            });

            index++;
        }
    }

    @Override
    public void render(float delta) {
        // Clears the screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draws the UI
        stage.act(delta);
        stage.draw();
    }

    //TODO: needs to be updated to also reposition stage actors
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
