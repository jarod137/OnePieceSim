package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import io.github.OPTCGSIM.cards.Card;

// Basic Deck class to manage cards
public class Deck {
    private Array<Card> cards;
    public Deck(String string) {
        Texture cardBackTexture = new Texture("card_back.png"); 
        cards = new Array<>();
        for (int i = 0; i < 50; i++) { // Assume a deck of 50 cards
            //add card to the deck based on info
        }
    }
    public Card drawCard() {
        if (cards.size > 0) {
            return cards.pop();
        }
        else {deckEmpty(); return null;}
    }

    public void deckEmpty() {

    }
  
       public void shuffle() {
        // for David
    }
}

