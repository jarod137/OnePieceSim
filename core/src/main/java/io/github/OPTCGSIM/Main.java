package io.github.OPTCGSIM;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import io.github.OPTCGSIM.cards.Card;
import io.github.OPTCGSIM.cards.CardParse;
import io.github.OPTCGSIM.screens.TitleScreen;
import io.github.OPTCGSIM.util.AssetHandler;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private AssetHandler assets;

    @Override
    public void create() {
        assets = new AssetHandler();
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

    public AssetHandler getAssets() {
        return assets;
    }

}
