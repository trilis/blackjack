package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards = new ArrayList<>();
    private String name;
    private boolean isActive = true;

    public Player(String name) {
        this.name = name;
    }

    private Player(String name, List<Card> cards, boolean isActive) {
        this.name = name;
        this.cards = cards;
        this.isActive = isActive;
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

    public void pass() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(name);
        for (Card card : cards) {
            builder.append(':');
            builder.append(card.toString());
        }

        builder.append(':');
        builder.append(isActive);

        return builder.toString();
    }

    public static Player fromString(String player) {
        String[] tokens = player.split(":");
        String name = tokens[0];
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i < tokens.length - 1; i++) {
            cards.add(Card.fromString(tokens[i]));
        }

        boolean isActive = Boolean.parseBoolean(tokens[tokens.length - 1]);

        return new Player(name, cards, isActive);
    }
}
