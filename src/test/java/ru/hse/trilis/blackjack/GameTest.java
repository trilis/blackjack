package ru.hse.trilis.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void init() {
        game = new Game();
    }

    @Test
    void makeTurn() {
    }

    @Test
    void stop() {
    }

    @Test
    void optimalSumEmptyList() {
        assertEquals(0, Game.optimalSum(Collections.emptyList()));
    }

    @Test
    void optimalSumNumbers() {
        for (int i = 2; i <= 10; i++) {
            assertEquals(i, Game.optimalSum(
                    Collections.singletonList(new Card("", Collections.singletonList(i)))));
        }
    }

    @Test
    void optimalSumQueen() {
        assertEquals(10, Game.optimalSum(Collections.singletonList(new Card("", Collections.singletonList(10)))));
    }

    @Test
    void optimalSumAcc() {
        assertEquals(11, Game.optimalSum(Collections.singletonList(new Card("", List.of(11, 1)))));
    }

    @Test
    void optimalSumTwoAcc() {
        var acc = new Card("", List.of(11, 1));
        assertEquals(12, Game.optimalSum(List.of(acc, acc)));
    }
}