package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards = new ArrayList<>();

    @NotNull
    public List<Card> getCards() {
        return cards;
    }

    public void addCard(@NotNull Card card) {
        cards.add(card);
    }
}
