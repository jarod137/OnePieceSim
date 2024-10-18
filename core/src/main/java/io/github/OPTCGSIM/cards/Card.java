package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;
import io.github.OPTCGSIM.player.*;

public abstract class Card {
    protected String name;
    Texture texture;

    public Card(Texture texture, String name) {
        this.texture = texture;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }

    void dispose() {
        texture.dispose();
    }

    public abstract void play(Player player);
}
