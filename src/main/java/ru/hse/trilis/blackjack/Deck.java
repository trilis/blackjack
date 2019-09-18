package ru.hse.trilis.blackjack;

import java.util.*;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private int index = 0;

    private void addCards(String name, List<Integer> values) {
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(name, values));
        }
    }

    public Deck(int seed) {
        addCards("A", Arrays.asList(1, 11));
        addCards("2", Arrays.asList(2));
        addCards("3", Arrays.asList(3));
        addCards("4", Arrays.asList(4));
        addCards("5", Arrays.asList(5));
        addCards("6", Arrays.asList(6));
        addCards("7", Arrays.asList(7));
        addCards("8", Arrays.asList(8));
        addCards("9", Arrays.asList(9));
        addCards("10", Arrays.asList(10));
        addCards("J", Arrays.asList(10));
        addCards("Q", Arrays.asList(10));
        addCards("K", Arrays.asList(10));
        Collections.shuffle(cards, new Random(seed));
    }

    public Card getNextCard() {
        if (index >= 52) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.get(index++);
    }
}