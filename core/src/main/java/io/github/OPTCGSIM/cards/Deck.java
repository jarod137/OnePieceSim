package io.github.OPTCGSIM.cards;

import java.util.ArrayList;
import java.util.Objects;

public class Deck {

    private LeaderCard leader;
    private ArrayList<Card> deck;

    public Deck(){
        this.leader = null;
        this.deck = new ArrayList<>();
    }

    public void setLeader(LeaderCard leader){
        this.leader = leader;
    }

    public LeaderCard getLeader(){
        return this.leader;
    }

    public ArrayList<Card> getDeck(){
        return this.deck;
    }

    public boolean hasLeader(){
        if (this.leader == null){
            return false;
        }

        return true;
    }

    public boolean rightColor(Card card){
        if (this.leader == null){
            return true;
        }

        if (Objects.equals(this.leader.getColor(), card.getColor())){
            return true;
        }

        return false;
    }

    public void addToHand(Card card){
        this.deck.add(card);
    }
}
