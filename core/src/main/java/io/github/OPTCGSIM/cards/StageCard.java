package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;

import io.github.OPTCGSIM.player.Player;

public class StageCard extends Card {

    private int cost;
    private String effects;
    private String color;
    private String cardCategory;
    private String type;
    private String cardNumber;

    public StageCard(Texture texture, String name, int cost, String effects, String color, String cardCategory, String type, String cardNumber) {
        super(texture, name);
        this.cost = cost;
        this.effects = effects;
        this.color = color;
        this.cardCategory = cardCategory;
        this.type = type;
        this.cardNumber = cardNumber;
    }

    @Override
    public void play(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'play'");
    }
    
    void dispose() {
        texture.dispose();
    }
}
