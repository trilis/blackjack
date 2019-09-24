package ru.hse.trilis.blackjack;

import java.util.List;

public class GameState {
    private List<Card> cards;
    private State state;

    public GameState(List<Card> cards, State state) {
        this.cards = cards;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public List<Card> getCards() {
        return cards;
    }
}