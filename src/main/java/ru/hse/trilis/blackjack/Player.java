package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards = new ArrayList<>();
    private String name;
    private boolean isActive = true;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @NotNull
    public List<Card> getCards() {
        return cards;
    }

    public void addCard(@NotNull Card card) {
        cards.add(card);
    }

    public int calculateOptimalSum(int maxPoints) {
        int sum = getMinimumSum();
        for (var card : cards) {
            if (canBeIncreased(card)) {
                int newSum = getIncreasedSum(sum, card);
                if (newSum <= maxPoints) {
                    sum = newSum;
                }
            }
        }
        return sum;
    }

    private boolean canBeIncreased(Card card) {
        return card.getValues().size() > 1;
    }

    private int getIncreasedSum(int sum, Card card) {
        return sum + card.getValues().stream().max(Integer::compareTo).orElse(0)
                            - card.getValues().stream().min(Integer::compareTo).orElse(0);
    }

    private int getMinimumSum() {
        var sum = 0;
        for (var card : cards) {
            sum += card.getValues().stream().min(Integer::compareTo).orElse(0);
        }
        return sum;
    }

    public void pass() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
