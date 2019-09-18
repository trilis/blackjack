package ru.hse.trilis.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Deck deck = new Deck(0);
    private Player player = new Player();
    private List<Card> croupierCards = new ArrayList<>();

    public Game() {
        for (int i = 0; i < 2; i++) {
            player.addCard(deck.getNextCard());
            croupierCards.add(deck.getNextCard());
        }
    }

    public void makeTurn() {
        var nextCard = deck.getNextCard();
        player.addCard(nextCard);
    }

    public FinalState stop() {
        while (Game.optimalSum(croupierCards) < 16) {
            croupierCards.add(deck.getNextCard());
        }
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
