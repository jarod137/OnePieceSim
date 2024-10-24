package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;

import io.github.OPTCGSIM.player.Player;

public class LeaderCard extends Card {

    private int power;
    private int life;
    private String attribute;
    private String effect;
    private String type;
    private String set;
    public LeaderCard(Texture texture, String name, int cardNumber, String cardType, String color, int life, int power,  String type, String effect, String set, String attribute) {
        super(texture, name, cardNumber, cardType, color);
        this.life = life;
        this.power = power;
        this.type = type;
        this.effect = effect;
        this.set = set;
        this.attribute = attribute;
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
