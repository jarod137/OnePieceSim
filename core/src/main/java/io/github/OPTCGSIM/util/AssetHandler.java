package io.github.OPTCGSIM.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetHandler {
    private AssetManager assetManager = new AssetManager();

    public static final AssetDescriptor<TextureAtlas> TILESET_01 = new AssetDescriptor<>("clean-crispy-ui.atlas", TextureAtlas.class);
    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<>("clean-crispy-ui.json", Skin.class, new SkinLoader.SkinParameter("clean-crispy-ui.atlas"));

    public void loadAll() { 
        assetManager.load(TILESET_01);
        assetManager.load(SKIN); 
    }


    public AssetManager gAssetManager()
    {
        return assetManager;
    }
}
