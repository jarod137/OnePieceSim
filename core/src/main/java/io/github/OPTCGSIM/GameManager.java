package io.github.OPTCGSIM;

import io.github.OPTCGSIM.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.github.OPTCGSIM.cards.Deck;


public class GameManager {
    private List<Player> players;
    private Deck deck;

    public GameManager(List<Player> playerList) {
        this.players = playerList;
        this.deck = new Deck(null);
        initializeGame();
    }

    public void initializeGame(){
      //  deck.shuffle();
        drawPlayerHand();
    }

    private void drawPlayerHand() {
        for (Player player : players) {
            player.drawCard(/*deck.draw(5)*/);
        }
    }

    public void startGame() {
        // main game loop
    }

}
