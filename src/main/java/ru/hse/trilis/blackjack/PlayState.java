package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameState {
    @NotNull private List<Card> cards;
    @NotNull private State state;

    public GameState(@NotNull List<Card> cards, @NotNull State state) {
        this.cards = cards;
        this.state = state;
    }

    @NotNull public State getState() {
        return state;
    }

    @NotNull public List<Card> getCards() {
        return cards;
    }
}