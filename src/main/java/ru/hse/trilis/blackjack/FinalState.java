package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

public class FinalState {
    @NotNull
    private Winner winner;
    private int playerSum;
    private int croupierSum;

    public FinalState(int playerSum, int croupierSum) {
        this.playerSum = playerSum;
        this.croupierSum = croupierSum;
        initWinner(playerSum, croupierSum);
    }

    private void initWinner(int playerSum, int croupierSum) {
        if (playerSum > Game.MAX_POINTS) {
            winner = Winner.CROUPIER;
        } else if (playerSum > croupierSum) {
            winner = Winner.PLAYER;
        } else if (playerSum == croupierSum) {
            winner = Winner.DRAW;
        } else if (croupierSum > Game.MAX_POINTS) {
            winner = Winner.PLAYER;
        } else {
            winner = Winner.CROUPIER;
        }
    }

    @NotNull
    public Winner getWinner() {
        return winner;
    }

    public int getPlayerSum() {
        return playerSum;
    }

    public int getCroupierSum() {
        return croupierSum;
    }
}