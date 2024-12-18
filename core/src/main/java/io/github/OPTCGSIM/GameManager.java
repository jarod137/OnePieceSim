package io.github.OPTCGSIM;

import io.github.OPTCGSIM.cards.Deck;
import io.github.OPTCGSIM.player.Player;

import java.util.List;


public class GameManager {
    private List<Player> players;
    private Deck deck;

    public GameManager(List<Player> playerList) {
        this.players = playerList;
        this.deck = new Deck();
        initializeGame();
    }

    public void initializeGame(){
      //  deck.shuffle();
        drawPlayerHand();
    }

    private void drawPlayerHand() {
        for (Player player : players) {
            for(int i = 0; i < 5; i++) { // draw starting hand
//                deck.drawCard();
            }
        }
    }

    public void gameOver() {

    }

    public void startGame() {
        // main game loop
        initializeGame();

    }

}
