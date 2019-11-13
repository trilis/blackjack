package ru.hse.trilis.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {
    private String name;
    private List<Integer> values;

    public Card(String name, List<Integer> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getValues() {
        return values;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(name);
        for (int value : values) {
            builder.append(' ');
            builder.append(value);
        }

        return builder.toString();
    }

    public static Card fromString(String card) {
        String[] tokens = card.split(" ");
        String name = tokens[0];
        List<Integer> values = new ArrayList<>();

        for (int i = 1; i < tokens.length; i++) {
            values.add(Integer.parseInt(tokens[i]));
        }

        return new Card(name, values);
    }
}