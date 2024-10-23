package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;

import io.github.OPTCGSIM.player.Player;

public class CharacterCard extends Card {
    private int cost;
    private int power;
    private String attribute;
    private String color;
    private int counter;
    private String effects;
    private String triggerEffect;
    private String type;
    private String cardNumber;


    public CharacterCard(Texture texture, String name, int cost, int power, String attribute, String color, int counter, String effects, String triggerEffect, String type, String cardNumber) {
        super(texture, name);
        this.cost = cost;
        this.power = power;
        this.attribute = attribute;
        this.color = color;
        this.counter = counter;
        this.effects = effects;
        this.triggerEffect = triggerEffect;
        this.type = type;
        this.cardNumber = cardNumber;

    }

    @Override
    public void play(Player player){
        // if(player.getActiveDon() /*unimplemented*/ >= cost) {
        //     player.restDon(cost); // unimplemented
        // }
    }

    void dispose() {
        texture.dispose();
    }
}
