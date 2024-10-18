package io.github.OPTCGSIM.player;

import com.badlogic.gdx.scenes.scene2d.ui.List;

import io.github.OPTCGSIM.cards.Card;
import io.github.OPTCGSIM.cards.Deck;
import io.github.OPTCGSIM.game.Game;

public class Player {
    private List<Card> hand;
    private Deck deck;
    private List<CharacterCard> battlefield;
    private List<Card> lifeArea;

    public void drawCard() {
        hand.add(deck.drawCard());
    }

    public void playCard(int cardIndex, Game game) {
        Card card = hand.get(cardIndex);
        card.play(game.getGameState(), this);
        hand.remove(cardIndex);
    }
}
