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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TitleScreen extends ScreenAdapter {

    private Stage stage;
    private AssetManager assetManager;
    private Skin skin;
    private Viewport viewport;
    private Image backgroundImage;
    private Table mainTable;

    public TitleScreen(AssetManager assetManager) {
        this.assetManager = assetManager;

        // Ensure assets are fully loaded
        assetManager.finishLoading();

        skin = assetManager.get(Assets.SKIN);
        viewport = new ExtendViewport(16, 9); // Initialize the viewport here
    }

    @Override
    public void show() {
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
        mainTable.setDebug(true); // Debugging for button layout

        // Add buttons to the table with listeners
        mainTable.add(addButton("Play", () -> {
            Gdx.app.log("Button", "Play button pressed");
        }, 200, 50)).padBottom(10);
        
        mainTable.row();
        mainTable.add(addButton("Decks", () -> {
            Gdx.app.log("Button", "Decks button pressed");
        }, 200, 50)).padBottom(10);

        mainTable.row();
        mainTable.add(addButton("Options", () -> {
            Gdx.app.log("Button", "Options button pressed");
        }, 200, 50)).padBottom(10);

        mainTable.row();
        mainTable.add(addButton("Exit", () -> {
            Gdx.app.exit();
        }, 200, 50)).padBottom(10);

        // Add the main table to the stage (after the background)
        stage.addActor(mainTable);

        // Set input processor and log to confirm
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log("Input", "Input processor set to stage");

        // Confirm the skin is loaded
        if (skin != null) {
            Gdx.app.log("TitleScreen", "Skin loaded successfully.");
        } else {
            Gdx.app.log("TitleScreen", "Failed to load skin.");
        }
    }

    // Method to create buttons with a click listener and size control
    private TextButton addButton(String name, Runnable action, float width, float height) {
        TextButton button = new TextButton(name, skin);
        button.setSize(width, height); // Set a fixed size for the button
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Button", name + " clicked"); // Debug log for click detection
                action.run(); // Execute the provided action when the button is clicked
            }
        });
        return button;
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
        viewport.update(width, height, true); // Update the viewport dimensions
        backgroundImage.setSize(viewport.getWorldWidth(), viewport.getWorldHeight()); // Ensure background fits viewport
    }

    @Override
    public void dispose() {
        stage.dispose(); // Dispose of the stage to prevent memory leaks
    }
}


