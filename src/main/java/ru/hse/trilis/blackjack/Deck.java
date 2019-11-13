package ru.hse.trilis.blackjack;

import java.util.*;

public class Deck {
    private static final int SUIT_NUMBER = 4;

    private Stack<Card> cards = new Stack<>();

    private void addCards(String name, List<Integer> values) {
        for (int i = 0; i < SUIT_NUMBER; i++) {
            cards.push(new Card(name, values));
        }
    }

    public Deck() {
        addCards("A", List.of(1, 11));
        addCards("2", List.of(2));
        addCards("3", List.of(3));
        addCards("4", List.of(4));
        addCards("5", List.of(5));
        addCards("6", List.of(6));
        addCards("7", List.of(7));
        addCards("8", List.of(8));
        addCards("9", List.of(9));
        addCards("10", List.of(10));
        addCards("J", List.of(10));
        addCards("Q", List.of(10));
        addCards("K", List.of(10));
        Collections.shuffle(cards, new Random());
    }

    public Card getNextCard() {
        if (cards.empty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.pop();
    }
}