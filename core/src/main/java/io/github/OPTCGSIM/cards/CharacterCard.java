package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;
import io.github.OPTCGSIM.player.Player;

public class CharacterCard extends Card {
    private int cost;
    private int power;
    private int counter;
    private String attribute;
    private String set;
    private String type;

    public CharacterCard(Texture texture, String name, int cardNumber, String cardType, String color, int cost, int power, int counter, String type, String effect, String set, String attribute) {
        super(texture, name, cardNumber, cardType, color, effect);
        this.cost = cost;
        this.power = power;
        this.counter = counter;
        this.type = type;
        this.set = set;
        this.attribute = attribute;
    }

    public void isActive() {
        //TODO make an is active function
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
