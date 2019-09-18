package ru.hse.trilis.blackjack;

import java.util.List;

public class Game {
    private Deck deck = new Deck(0);
    //private Player player = new Player();
    private Croupier croupier = new Croupier();

    public Game() {
        for (int i = 0; i < 2; i++) {
            //player.addCard(deck.getNextCard());
            croupier.addCard(deck.getNextCard());
        }
    }

    public void makeTurn() {
        var nextCard = deck.getNextCard();
        //player.addCard(nextCard);
    }

    public void stop() {

    }

    static public int optimalSum(List<Card> cards) {
        var sum = 0;
        for (var card : cards) {
            sum += card.getValues().get(0);
        }
        for (var card : cards) {
            if (card.getValues().size() > 1) {
                var delta = card.getValues().get(1) - card.getValues().get(0);
                if (sum + delta <= MAX_POINTS) {
                    sum += delta;
                }
            }
        }
        return sum;
    }

    public static int MAX_POINTS = 21;
}
