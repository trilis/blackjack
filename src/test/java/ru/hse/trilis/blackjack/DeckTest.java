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
        var types = new HashMap<String, Integer>();
        for (int i = 0; i < 52; i++) {
            var card = deck.getNextCard();
            var count = types.get(card.getName());
            types.put(card.getName(), count + 1);
        }


        assertThrows(IllegalStateException.class, () -> deck.getNextCard());
        assertEquals(13, types.size());
        for (var count : types.values()) {
           assertEquals(4, (int)count);
        }
    }
}