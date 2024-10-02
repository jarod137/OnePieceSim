package io.github.OPTCGSIM;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private AssetManager assetManager = new AssetManager();

    public static final AssetDescriptor<TextureAtlas> TILESET_01 = new AssetDescriptor<>("clean-crispy-ui.atlas", TextureAtlas.class);
    public static final

    public void loadAll() { assetManager.load(TILESET_01); }

    public AssetManager gAssetManager()
    {
        return assetManager;
    }
}
