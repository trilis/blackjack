package ru.hse.trilis.blackjack;

import java.util.*;

public class Deck {
    private static final int SUIT_NUMBER = 4;

    private Stack<Card> cards = new Stack<>();

    private void addCards(String name, Integer... values) {
        for (int i = 0; i < SUIT_NUMBER; i++) {
            cards.push(new Card(name, List.of(values)));
        }
    }

    public Deck() {
        addCards("A", 1, 11);
        addCards("2", 2);
        addCards("3", 3);
        addCards("4", 4);
        addCards("5", 5);
        addCards("6", 6);
        addCards("7", 7);
        addCards("8", 8);
        addCards("9", 9);
        addCards("10",10);
        addCards("J", 10);
        addCards("Q", 10);
        addCards("K", 10);
        Collections.shuffle(cards, new Random());
    }

    public Card getNextCard() {
        if (cards.empty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.pop();
    }
}