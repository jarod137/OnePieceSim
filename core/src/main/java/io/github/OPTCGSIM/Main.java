package io.github.OPTCGSIM;

import com.badlogic.gdx.Game;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        Assets assets = new Assets();
        assets.loadAll();
        assets.gAssetManager().finishLoading();

        setScreen(new TitleScreen(assets.gAssetManager()));
        //setScreen(new GameBoard());
    }

    public void render() {
        super.render();
    } 

    public void dispose() {

    }
}

