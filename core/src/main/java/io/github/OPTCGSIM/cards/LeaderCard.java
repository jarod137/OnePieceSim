package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;

import io.github.OPTCGSIM.player.Player;

public class LeaderCard extends Card {

    private int power;
    private String attribute;
    private String effects;
    private String color;
    private String cardCategory;
    private int life;
    private String type;
    private String cardNumber;

    public LeaderCard(Texture texture, String name, int power, String attribute, String effects, String color, String cardCategory, int life, String type, String cardNumber) {
        super(texture, name);
        this.power = power;
        this.attribute = attribute;
        this.effects = effects;
        this.color = color;
        this.cardCategory = cardCategory;
        this.life = life;
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
