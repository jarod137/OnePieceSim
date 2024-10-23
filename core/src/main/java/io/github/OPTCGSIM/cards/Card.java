package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;
import io.github.OPTCGSIM.player.*;

import java.util.ArrayList;

public abstract class Card {
    protected String name;
    protected Texture texture;
    protected int cardNumber;
    protected String cardType;

    public Card(Texture texture, String name, int cardNumber, String cardType) {
        this.texture = texture;
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public String getName() {
        return this.name;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public String getCardType() {
        return this.cardType;
    }

    void dispose() {
        texture.dispose();
    }

    public abstract void play(Player player);
}
