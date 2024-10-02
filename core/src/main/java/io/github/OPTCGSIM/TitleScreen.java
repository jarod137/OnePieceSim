package io.github.OPTCGSIM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class TitleScreen extends ScreenAdapter {

    private Stage stage;
    private Viewport viewport;

    @Override
    public void show() {
        stage = new Stage();
        viewport = new ExtendViewport(16, 9);
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
        viewport.update(width, height);
    }


}