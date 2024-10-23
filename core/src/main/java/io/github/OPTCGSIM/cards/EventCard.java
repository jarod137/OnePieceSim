package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;

import io.github.OPTCGSIM.player.Player;

public class EventCard extends Card {

    private int cost;
    private String effect;
    private String color;
    private String type;
    private String set;
    public EventCard(Texture texture, String name, int cardNumber, String cardType, int cost, String color, String type, String effect, String set) {
        super(texture, name, cardNumber, cardType);
        this.cost = cost;
        this.color = color;
        this.type = type;
        this.effect = effect;
        this.set = set;
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
