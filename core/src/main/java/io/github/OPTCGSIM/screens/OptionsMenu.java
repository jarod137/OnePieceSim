package io.github.OPTCGSIM.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.OPTCGSIM.Main;
import io.github.OPTCGSIM.util.AssetHandler;

import com.badlogic.gdx.assets.AssetManager;

public class OptionsMenu extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private Main game;
    private AssetManager assetManager;

    private SelectBox<String> resolutionSelectBox;

    // List of resolutions (width x height)
    private final String[] resolutions = {"1280x720", "1920x1080", "2560x1440", "3840x2160"};

    public OptionsMenu(Main game) {
        this.game = game;
        this.assetManager = game.getAssets().gAssetManager();  // Get the AssetManager
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Ensure that the skin is loaded and assigned
        skin = assetManager.get(AssetHandler.SKIN);  // Make sure to load the skin

        // Setup the UI table layout
        Table table = new Table();
        table.setFillParent(true);
        table.setSkin(skin);  // Set the skin for the table

        stage.addActor(table);

        // Create the resolution dropdown menu
        resolutionSelectBox = new SelectBox<>(skin);
        resolutionSelectBox.setItems(resolutions);

        // Set the current resolution based on the current window size
        resolutionSelectBox.setSelected(getCurrentResolution());

        // Create a button to apply the selected resolution
        TextButton applyButton = new TextButton("Apply", skin);
        applyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                applyResolution();
            }
        });

        // Create a button to return to the main menu
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TitleScreen(game, assetManager));  // Back to main menu
            }
        });

        float buttonWidth = Gdx.graphics.getWidth() * 0.2f;
        float buttonHeight = Gdx.graphics.getWidth() * 0.1f;

        // Add components to the table
        table.add("Resolution:").padBottom(20);
        table.row();
        table.add(resolutionSelectBox).padBottom(20);
        table.row();
        table.add(applyButton).width(buttonWidth).height(buttonHeight).padBottom(20);
        table.row();
        table.add(backButton).width(buttonWidth).height(buttonHeight);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the UI
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

    // Method to get the current resolution as a string (e.g., "1920x1080")
    private String getCurrentResolution() {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        return width + "x" + height;
    }

    // Method to apply the selected resolution
    private void applyResolution() {
        String selectedResolution = resolutionSelectBox.getSelected();
        String[] dimensions = selectedResolution.split("x");

        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);

        // Change the resolution using LibGDX's setWindowedMode method
        Gdx.graphics.setWindowedMode(width, height);
    }
}
