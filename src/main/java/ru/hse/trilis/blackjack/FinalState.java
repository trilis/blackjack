package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FinalState {
    @NotNull
    private Winner winner;
    private List<Player> players;
    private int croupierSum;

    public FinalState(List<Player> players, int croupierSum) {
        this.players = players;
        this.croupierSum = croupierSum;
        var winnerNames = new ArrayList<String>();
        var drawNames = new ArrayList<String>();

        for (var player : players) {
            var playerSum = player.calculateOptimalSum(Game.MAX_POINTS);
            if (playerSum <= Game.MAX_POINTS && playerSum >= croupierSum) {
                if (playerSum == croupierSum) {
                    drawNames.add(player.getName());
                } else {
                    winnerNames.add(player.getName());
                }
            }
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