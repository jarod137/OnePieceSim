package io.github.OPTCGSIM;

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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class TitleScreen extends ScreenAdapter {

    private Stage stage;
    private Viewport viewport;
    private AssetManager assetManager;
    private Skin skin;

    private Table mainTable;

    //we can declare all connection variables locally too
    Protocol protocol;
    int port, connect;
    ServerSocket server;
    Socket socket;
    ServerSocketHints s_hints;
    SocketHints c_hints;
    String host;

    public TitleScreen(AssetManager assetManager){
        this.assetManager = assetManager;
        skin = assetManager.get(Assets.SKIN);
    }

    @Override
    public void show() {
        stage = new Stage();
        viewport = new ExtendViewport(16, 9);

        mainTable = new Table();
        mainTable.setFillParent(true);

        stage.addActor(mainTable);

        addButton("Play").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("play has been clicked");
            }
        });
        addButton("Decks");
        addButton("Options");
        addButton("Exit").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Quit was clicked");
                Gdx.app.exit();
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private TextButton addButton(String name){
            TextButton button = new TextButton(name, skin);
            mainTable.add(button).fillX().padBottom(10);
            mainTable.row();
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
        viewport.update(width, height);
    }


}