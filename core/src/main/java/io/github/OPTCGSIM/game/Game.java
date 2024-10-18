package io.github.OPTCGSIM.game;


public class Game {
    private GameState gameState;

    public void nextPhase() {
        gameState.advancePhase();
    }

    public void startGame(Player player1, Player player2) {
        gameState.setPlayer(player1, player2);
        gameState.setCurrentPhase(Phase.DRAW);
    }
   
}
