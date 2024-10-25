package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;

import io.github.OPTCGSIM.player.Player;

public class DonCard extends Card{
    public DonCard(Texture texture, String name, int cardNumber, String cardType, String color, String effect) {
        super(texture, name, cardNumber, cardType, null, null);
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
