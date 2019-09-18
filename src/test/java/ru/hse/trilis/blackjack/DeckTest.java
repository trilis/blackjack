package ru.hse.trilis.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void init() {
        deck = new Deck(42);
    }

    @Test
    void takeAllCardsTest() {
        HashMap<String, Integer> types = new HashMap<String, Integer>();
        for (int i = 0; i < 52; i++) {
            Card card = deck.getNextCard();
            Integer count = types.get(card.getName());
            types.put(card.getName(), count == null ? 1 : count + 1);
        }


        assertThrows(IllegalStateException.class, () -> deck.getNextCard());
        assertEquals(13, types.size());
        for (var count : types.values()) {
           assertEquals(4, (int)count);
        }
    }
}