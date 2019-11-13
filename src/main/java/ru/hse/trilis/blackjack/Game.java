package ru.hse.trilis.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int MAX_POINTS = 21;

    private Deck deck = new Deck();
    private List<Player> players = new ArrayList<>();
    private Player croupier = new Player();
    private State state = State.CONTINUE;
    private int activePlayerId = 0;

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

    public void nextPlayer() {
        activePlayerId++;
    }

    public void askForNextMove() {
        var activePlayer = players.get(activePlayerId);
        if (activePlayer.pass()) {
            nextPlayer();
        } else {
            activePlayer.addCard(deck.getNextCard());
        }
    }

    public PlayState getGameState() {
        return new PlayState();
    }

    public FinalState stop() {
        while (croupier.calculateOptimalSum(MAX_POINTS) < 16) {
            croupier.addCard(deck.getNextCard());
        }
        state = State.FINISH;
        return new FinalState(player.calculateOptimalSum(MAX_POINTS), croupier.calculateOptimalSum(MAX_POINTS));
    }
}
