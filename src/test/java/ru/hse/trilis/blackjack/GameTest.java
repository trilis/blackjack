package ru.hse.trilis.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static final int MAX_POINTS = 21;

    private Game game;
    private Player player;

    @BeforeEach
    void init() {
        game = new Game();
        player = new Player();
    }

    @Test
    void makeTurn() {
    }

    @Test
    void stop() {
    }

    @Test
    void optimalSumEmptyList() {
        assertEquals(0, player.calculateOptimalSum(MAX_POINTS));
    }

    @Test
    void optimalSumNumbers() {
        for (int i = 2; i <= 10; i++) {
            player = new Player();
            player.addCard(new Card("", Collections.singletonList(i)));
            assertEquals(i, player.calculateOptimalSum(MAX_POINTS));
        }
    }

    @Test
    void optimalSumQueen() {
        player.addCard(new Card("", Collections.singletonList(10)));
        assertEquals(10, player.calculateOptimalSum(MAX_POINTS));
    }

    @Test
    void optimalSumAcc() {
        player.addCard(new Card("", List.of(11, 1)));
        assertEquals(11, player.calculateOptimalSum(MAX_POINTS));
    }

    @Test
    void optimalSumTwoAcc() {
        player.addCard(new Card("", List.of(11, 1)));
        player.addCard(new Card("", List.of(11, 1)));
        assertEquals(12, player.calculateOptimalSum(MAX_POINTS));
    }

    @Test
    void testInitialState() {
        PlayState state = game.getGameState();
        assertEquals(1, state.getCards().size());
        assertEquals(State.CONTINUE, state.getState());
    }

    @Test
    void testStopState() {
        PlayState state = game.getGameState();
        FinalState finalState = game.stop();
        for (Card card : state.getCards()) {
            player.addCard(card);
        }
        assertEquals(player.calculateOptimalSum(MAX_POINTS), finalState.getPlayerSum());
    }

}