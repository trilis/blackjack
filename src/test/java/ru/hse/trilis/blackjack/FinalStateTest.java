package ru.hse.trilis.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FinalStateTest {
    @Test
    public void testPlayerSumToBig() {
        assertEquals(Winner.CROUPIER, (new FinalState(23, 12)).getWinner());
    }

    @Test
    public void testPlayerWin() {
        assertEquals(Winner.PLAYER, (new FinalState(20, 12)).getWinner());
    }

    @Test
    public void testPlayerLose() {
        assertEquals(Winner.CROUPIER, (new FinalState(20, 21)).getWinner());
    }

    @Test
    public void testDraw() {
        assertEquals(Winner.DRAW, (new FinalState(19, 19)).getWinner());
    }
}