package io.github.OPTCGSIM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

class TitleScreen extends ScreenAdapter {

    private Stage stage;
    private AssetManager assetManager;
    private Skin skin;
    private Image backgroundImage;

    private Table mainTable;

    public TitleScreen(AssetManager assetManager) {
        this.assetManager = assetManager;
        skin = assetManager.get(Assets.SKIN);
    }

    @Override
    public void show() {

        ExtendViewport viewport = new ExtendViewport(16, 9);
        stage = new Stage(viewport); 

        // Load the background image
        Texture backgroundTexture = new Texture(Gdx.files.internal("title.jpeg"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage); // Add background image first

        // Create the main table for buttons
        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center(); // Center the buttons

        // Add buttons to the table
        mainTable.add(addButton("Play")).fillX().padBottom(10);
        mainTable.row();
        mainTable.add(addButton("Decks")).fillX().padBottom(10);
        mainTable.row();
        mainTable.add(addButton("Options")).fillX().padBottom(10);
        mainTable.row();
        mainTable.add(addButton("Exit")).fillX().padBottom(10);

        // Add the main table to the stage (after the background)
        stage.addActor(mainTable);

        // Set input processor
        Gdx.input.setInputProcessor(stage);
        System.out.println("Viewport Width: " + viewport.getWorldWidth());
        System.out.println("Viewport Height: " + viewport.getWorldHeight());

    }

    // Method to create buttons
    private TextButton addButton(String name) {
       return new TextButton(name, skin);
    
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        Viewport viewport;
        viewport.update(width, height);
        backgroundImage.setSize(viewport.getWorldWidth(), viewport.getWorldHeight()); // Ensure background fits viewport
    }
}
