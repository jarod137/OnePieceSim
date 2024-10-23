package io.github.OPTCGSIM.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private static final int COLUMNS = 7;
    private ArrayList<Card> deck = new ArrayList<>();
    private Table table;

    public DeckBuilder(Main game) {
        this.game = game;
        this.assetManager = game.getAssets().gAssetManager();
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = assetManager.get(AssetHandler.SKIN);
        initializeUI();
        loadCards();
    }

    private void initializeUI() {
        table = new Table(); // Initialize the table
        table.setDebug(true);
        table.setSkin(skin);
        table.center();

        ScrollPane scrollPane = new ScrollPane(table, skin);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);
        stage.addActor(scrollPane);

        addBackButton(table);
    }

    private void addBackButton(Table table) {
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TitleScreen(game, assetManager));
            }
        });

        float buttonWidth = Gdx.graphics.getWidth() * 0.2f;
        float buttonHeight = Gdx.graphics.getWidth() * 0.1f;
        table.add(backButton).width(buttonWidth).height(buttonHeight).colspan(COLUMNS);
        table.row();
    }

    private void loadCards() {
        CardParse parse = new CardParse(assetManager);
        ArrayList<Card> cards = parse.filterCards(parse.parseJSON());

        int index = 1;
        for (Card card : cards) {
            addCardToTable(card, index);
            index++;
        }
    }

    private void addCardToTable(Card card, int index) {
        Texture cardTexture = card.getTexture();
        ImageButton cardButton = new ImageButton(new Image(cardTexture).getDrawable());
        cardButton.addListener(createCardClickListener(card));

        // Format layout and add card button
        if (index % COLUMNS == 0) {
            table.row(); // Ensure a new row for every COLUMNS number of cards
        }
        table.add(cardButton).width(Gdx.graphics.getWidth() * 0.2f).height(Gdx.graphics.getWidth() * 0.1f);
    }

    private ClickListener createCardClickListener(Card card) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(card.getName() + " clicked!");
                deck.add(card);
            }
        };
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

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

