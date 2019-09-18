package ru.hse.trilis.blackjack;


public class FinalState {
    private Winner winner;
    private int playerSum;
    private int croupierSum;

    public FinalState(int playerSum, int croupierSum) {
        this.playerSum = playerSum;
        this.croupierSum = croupierSum;

        if (playerSum > Game.MAX_POINTS) {
            winner = Winner.CROUPIER;
        } else if (playerSum > croupierSum) {
            winner = Winner.PLAYER;
        } else if (playerSum == croupierSum) {
            winner = Winner.DRAW;
        } else {
            winner = Winner.PLAYER;
        }
    }

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