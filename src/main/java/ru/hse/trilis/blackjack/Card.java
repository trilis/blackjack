package ru.hse.trilis.blackjack;

public class Card {
    private String name;
    private int[] values;

    public Card(String name, int[] values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public int[] getValues() {
        return values;
    }
}