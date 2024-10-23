package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;
import io.github.OPTCGSIM.player.Player;

public class CharacterCard extends Card {
    private int cost;
    private int power;
    private int counter;
    private String attribute;
    private String color;
    private String effect;
    private String set;
    private String type;
    private String cardType;

    public CharacterCard(Texture texture, String name, int cardNumber, String cardType, int cost, int power, int counter, String color, String type, String effect, String set, String attribute) {
        super(texture, name, cardNumber, cardType);
        this.cost = cost;
        this.power = power;
        this.counter = counter;
        this.color = color;
        this.type = type;
        this.effect = effect;
        this.set = set;
        this.attribute = attribute;
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
