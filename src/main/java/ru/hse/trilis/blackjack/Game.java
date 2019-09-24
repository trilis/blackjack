package ru.hse.trilis.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Deck deck = new Deck((new Random()).nextInt());
    private Player player = new Player();
    private List<Card> croupierCards = new ArrayList<>();
    private State state = State.CONTINUE;

    public Game() {
        for (int i = 0; i < 1; i++) {
            player.addCard(deck.getNextCard());
            croupierCards.add(deck.getNextCard());
        }
    }

    public GameState getGameState() {
        return new GameState(player.getCards(), state);
    }

    public void makeTurn() {
        var nextCard = deck.getNextCard();
        player.addCard(nextCard);
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
