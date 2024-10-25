package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;
import io.github.OPTCGSIM.player.Player;

public abstract class Card {
    protected String name;
    protected Texture texture;
    protected int cardNumber;
    protected String cardType;
    protected String color;

    public Card(Texture texture, String name, int cardNumber, String cardType, String color) {
        this.texture = texture;
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.color = color;
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

    public String getColor(){
        return this.color;
    }

    void dispose() {
        texture.dispose();
    }

    public abstract void play(Player player);
}
