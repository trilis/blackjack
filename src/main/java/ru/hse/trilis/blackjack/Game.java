package ru.hse.trilis.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Deck deck = new Deck((new Random()).nextInt());
    private List<Player> players = new ArrayList<>();
    private List<Card> croupierCards = new ArrayList<>();
    private State state = State.CONTINUE;
    private int activePlayerId = 0;

    public Game(List<String> playerNames) {
        int n = playerNames.size();
        for (int i = 0; i < n; i++) {
            players.add(new Player(playerNames.get(i)));
            for (int j = 0; j < 2; j++) {
                players.get(i).addCard(deck.getNextCard());
                croupierCards.add(deck.getNextCard());
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

    public GameState getGameState() {
        return new GameState();
    }

    public FinalState stop() {
        while (Game.optimalSum(croupierCards) < 16) {
            croupierCards.add(deck.getNextCard());
        }
        state = State.FINISH;
        return new FinalState(optimalSum(player.getCards()), optimalSum(croupierCards));
    }

    static public int optimalSum(List<Card> cards) {
        var sum = 0;
        for (var card : cards) {
            sum += card.getValues().stream().min(Integer::compareTo).orElse(0);
        }
        for (var card : cards) {
            if (card.getValues().size() > 1) {
                var delta = card.getValues().stream().max(Integer::compareTo).orElse(0)
                        - card.getValues().stream().min(Integer::compareTo).orElse(0);
                if (sum + delta <= MAX_POINTS) {
                    sum += delta;
                }
            }
        }



        return sum;
    }

    public static int MAX_POINTS = 21;
}
