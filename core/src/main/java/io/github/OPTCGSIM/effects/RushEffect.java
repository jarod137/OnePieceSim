package io.github.OPTCGSIM.effects;

public class RushEffect implements Effect {
    @Override
    public void activate(CharacterCard card, GameState gameState) {
        card.setCanAttack(true);
    }
    
}
