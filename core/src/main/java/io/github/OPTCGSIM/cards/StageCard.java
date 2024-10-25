package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;
import io.github.OPTCGSIM.player.Player;

public class StageCard extends Card {
    private int cost;
    private String effect;
    private String type;
    private String set;

    public StageCard(Texture texture, String name, int cardNumber, String cardType, String color, int cost, String type, String effect, String set) {
        super(texture, name, cardNumber, cardType, color);
        this.cost = cost;
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
