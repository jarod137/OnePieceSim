package io.github.OPTCGSIM.game;

import java.util.List;

import io.github.OPTCGSIM.player.Player;

public class TurnManager {
    private int currentPlayerIndex;
    private List<Player> players;

    public TurnManager(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void endTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
