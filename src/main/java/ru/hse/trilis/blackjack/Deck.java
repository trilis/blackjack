package ru.hse.trilis.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private int index = 0;

    private void addCards(String name, List<Integer> values) {
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(name, values));
        }
    }

    public Deck(int seed) {
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
        Collections.shuffle(cards, new Random(seed));
    }

    public Card getNextCard() {
        return cards.get(index++);
    }
}