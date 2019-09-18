package ru.hse.trilis.blackjack;

import java.util.List;

public class Game {
    private Deck deck = new Deck(0);
    //player

    public void makeTurn() {

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
