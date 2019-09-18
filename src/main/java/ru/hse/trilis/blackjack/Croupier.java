package ru.hse.trilis.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Croupier {
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> play(Deck deck) {
        while (Game.optimalSum(cards) < 16) {
            cards.add(deck.getNextCard());
        }
        return cards;
    }
}
