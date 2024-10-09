package io.github.OPTCGSIM;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private Assets assets;

    @Override
    public void create() {
        assets = new Assets();
        assets.loadAll();
        assets.gAssetManager().finishLoading();

        // Pass `this` (Main game instance) to the TitleScreen
        setScreen(new TitleScreen(this, assets.gAssetManager()));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        assets.gAssetManager().dispose();
    }

    // Add this method to return the Assets object
    public Assets getAssets() {
        return assets;
    }
}
