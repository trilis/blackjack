package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FinalState {
    public enum ResultKind {
        WIN, LOSE, DRAW
    }

    public class Result {
        private String name;
        private int sum;
        private ResultKind kind;

        public String getName() {
            return name;
        }

        public int getSum() {
            return sum;
        }

        public ResultKind getKind() {
            return kind;
        }

        public Result(String name, int sum, ResultKind kind) {
            this.name = name;
            this.sum = sum;
            this.kind = kind;
        }
    }

    @NotNull
    private List<Result> results;
    private List<Player> players;
    private int croupierSum;

    public FinalState(List<Player> players, int croupierSum) {
        this.players = players;
        this.croupierSum = croupierSum;
        initWinner(croupierSum);
    }

    private void initWinner(int croupierSum) {
        var winnerNames = new ArrayList<String>();
        var drawNames = new ArrayList<String>();

        for (var player : players) {
            var playerSum = player.calculateOptimalSum(Game.MAX_POINTS);
            ResultKind resultKind = getResultKind(croupierSum, playerSum);
            results.add(new Result(player.getName(), playerSum, resultKind));
        }
    }

    @NotNull
    private FinalState.ResultKind getResultKind(int croupierSum, int playerSum) {
        ResultKind resultKind = ResultKind.LOSE;
        if (playerSum <= Game.MAX_POINTS && playerSum >= croupierSum) {
            if (playerSum == croupierSum) {
                resultKind = ResultKind.DRAW;
            } else {
                resultKind = ResultKind.WIN;
            }
        }
        return resultKind;
    }

    @NotNull
    public List<Result> getResults() {
        return results;
    }

    public int getCroupierSum() {
        return croupierSum;
    }
}