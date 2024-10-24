package io.github.OPTCGSIM.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Net.*;
import com.badlogic.gdx.net.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.OPTCGSIM.Main;
import io.github.OPTCGSIM.cards.Card;
import io.github.OPTCGSIM.cards.CardParse;
import io.github.OPTCGSIM.util.AssetHandler;

import java.util.ArrayList;

public class TitleScreen extends ScreenAdapter {

    private Stage stage;
    private AssetManager assetManager;
    private Skin skin;
    private Main game;

    private Table mainTable;

    //we can declare all connection variables locally too
    Protocol protocol;
    int port, connect;
    ServerSocket server;
    Socket socket;
    ServerSocketHints s_hints;
    SocketHints c_hints;
    String host;

    public TitleScreen(Main game, AssetManager assetManager) {
        this.game = game;
        this.assetManager = assetManager;
        skin = assetManager.get(AssetHandler.SKIN);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        CardParse parse = new CardParse(assetManager);
        ArrayList<Card> cards = new ArrayList<>();
        cards = parse.parseJSON();

        // Create the "Play" button
        TextButton playButton = new TextButton("Play", skin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameBoard());  // Directly switch to the GameBoard screen
            }
        });

        // Create the "Options" button
        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsMenu(game));  // Go to the options menu
            }
        });

        // Create Deck Builder Buttons
        TextButton deckBuilderButton = new TextButton("Deck Builder", skin);
        deckBuilderButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new DeckBuilder(game));  // Go to the deck builder
            }
        });

        // Create the "Exit" button
        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        float buttonWidth = Gdx.graphics.getWidth() * 0.2f;
        float buttonHeight = Gdx.graphics.getWidth() * 0.1f;

        // Add buttons to the table
        table.add(playButton).width(buttonWidth).height(buttonHeight).padBottom(20);
        table.row();
        table.add(deckBuilderButton).width(buttonWidth).height(buttonHeight).padBottom(20);
        table.row();
        table.add(optionsButton).width(buttonWidth).height(buttonHeight).padBottom(20);
        table.row();
        table.add(exitButton).width(buttonWidth).height(buttonHeight);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
