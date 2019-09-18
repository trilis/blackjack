package ru.hse.trilis.blackjack;

import java.util.List;

public class Deck {
    private List<Card> cards;
    private int index;

    public Deck() {

    }

    public Card getNextCard() {
        return new Card("", new int[] {});
    }
}