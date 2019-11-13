package ru.hse.trilis.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int MAX_POINTS = 21;

    private Deck deck = new Deck();
    private List<Player> players = new ArrayList<>();
    private Player croupier = new Player("croupier");
    private int activePlayerId = 0;
    private FinalState finalState = null;

    //FIXME
    private Player activePlayer;

    public Game(List<String> playerNames) {
        int n = playerNames.size();
        for (int i = 0; i < n; i++) {
            players.add(new Player(playerNames.get(i)));
            for (int j = 0; j < 2; j++) {
                players.get(i).addCard(deck.getNextCard());
                croupier.addCard(deck.getNextCard());
            }
        }
    }

    private void nextPlayer() {
        activePlayerId++;
        if (activePlayerId == players.size()) {
            stop();
        }
    }

    public void askForNextMove() {
        var activePlayer = players.get(activePlayerId);
        if (activePlayer.isActive()) {
            nextPlayer();
        } else {
            activePlayer.addCard(deck.getNextCard());
        }
    }

    public GameState getGameState() {
        if (finalState != null) {
            return new GameState(players, null);
        } else {
            return new GameState(players, players.get(activePlayerId));
        }
    }

    public void stop() {
        while (croupier.calculateOptimalSum(MAX_POINTS) < 16) {
            croupier.addCard(deck.getNextCard());
        }
        finalState = new FinalState(players, croupier.calculateOptimalSum(MAX_POINTS));
    }

    public FinalState getFinalState() {
        return finalState;
    }
}
