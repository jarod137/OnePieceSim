package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;

import io.github.OPTCGSIM.player.Player;

public class EventCard extends Card {

    private int cost;
    private String effects;
    private String triggerEffect;
    private String color;
    private String cardCategory;
    private String cardNumber;
    private String type;

    public EventCard(Texture texture, String name, int cost, String effects,String type, String triggerEffect, String color, String cardCategory, String cardNumber) {
        super(texture, name);
        this.cost = cost;
        this.effects = effects;
        this.triggerEffect = triggerEffect;
        this.color = color;
        this.cardCategory = cardCategory;
        this.cardNumber = cardNumber;
        this.type = type;
        
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
