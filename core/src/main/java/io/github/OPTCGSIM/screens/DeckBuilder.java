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
import io.github.OPTCGSIM.cards.Deck;
import io.github.OPTCGSIM.cards.LeaderCard;
import io.github.OPTCGSIM.util.AssetHandler;

import java.util.ArrayList;

public class DeckBuilder extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private Main game;
    private AssetManager assetManager;
    private static final int COLUMNS = 7;
    private ArrayList<Card> cards = new ArrayList<>();
    private Deck deck = new Deck();
    private CardParse parse;
    private Table table;
    private Table handTable;

    public DeckBuilder(Main game) {
        this.game = game;
        this.assetManager = game.getAssets().gAssetManager();
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = assetManager.get(AssetHandler.SKIN);
        parse = new CardParse(assetManager);

        initializeUI();
        loadCards();
    }

    private void initializeUI() {
        table = new Table();
        table.setDebug(true);
        table.setSkin(skin);
        table.center();

        ScrollPane scrollPane = new ScrollPane(table, skin);
        scrollPane.setFillParent(true);
        scrollPane.setDebug(true);
        scrollPane.setScrollingDisabled(true, false);
        stage.addActor(scrollPane);

        // Create the hand table
        handTable = new Table();
        handTable.setSkin(skin);
        stage.addActor(handTable);

        addBackButton(table);
        updateHandTable();
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
        cards = parse.parseJSON();

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

        if (index % COLUMNS == 0) {
            table.row();
        }

        table.add(cardButton).width(Gdx.graphics.getWidth() * 0.2f).height(Gdx.graphics.getWidth() * 0.1f);
    }

    private ClickListener createCardClickListener(Card card) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(card.getName() + " clicked!");
                checkCardReqs(card);
                updateHandTable();
            }
        };
    }

    //Handles interactions with Hand.java
    //FIXME: (issues)
    // 1. Event card is able to be selected even if no leader is picked
    // 2. Sometimes allows for multiple leaders to be picked (noticed when working will yellow deck)
    //
    private void checkCardReqs(Card card){

        if (card.getCardType().equalsIgnoreCase("LEADER")){
            if (deck.hasLeader()){
                System.out.println("Error: Already has a leader");
            } else {
                deck.setLeader((LeaderCard) card);
                deck.addToHand(card);
            }
        } else {
            if (!deck.hasLeader()){
                System.out.println("Error: no leader picked yet");
            } else if (deck.rightColor(card)){
                deck.addToHand(card);
            } else {
                System.out.println("Error: Wrong color selected (does not match leader card color)");
            }
        }
    }


    // Update the hand table to show the cards in the deck (hand)
    private void updateHandTable() {
        handTable.clear();

        handTable.row();
        float cardHeight = 0;
        for (Card card : deck.getDeck()) {
            Texture cardTexture = card.getTexture();
            Image cardImage = new Image(cardTexture);
            float cardWidth = Gdx.graphics.getWidth() * 0.15f;
            cardHeight = cardWidth * (3.0f / 2.0f);

            handTable.add(cardImage).width(cardWidth).height(cardHeight);
        }

        handTable.setDebug(true);
        float handTableYPosition = cardHeight + Gdx.graphics.getHeight() / 10f;
        handTable.setPosition(Gdx.graphics.getWidth() / 2f, handTableYPosition);
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
