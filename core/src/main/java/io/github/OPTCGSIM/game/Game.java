package io.github.OPTCGSIM.game;

import io.github.OPTCGSIM.player.Player;

public class Game {
    

    public void nextPhase(String phase) {
        
        switch(phase){
            case "Refresh":
               // refreshPhase();
            case "Draw":
               // drawPhase();
            case "Don":
               // donPhase();
            case "Main":
               // mainPhase();
            case "End":
               // endPhase();
            default:
                throw new Error("nextPhase() is broken");
        }
    }

    public void startGame(Player player1, Player player2) {
        // where the game loop will now be implemented
    }
   
}
