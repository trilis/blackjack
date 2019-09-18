package ru.hse.trilis.blackjack;

import java.util.List;

public class Card {
    private String name;
    private List<Integer> values;

    public Card(String name, List<Integer> values) {
        this.name = name;
        this.values = values;
        this.values.sort(Integer::compareTo);
    }

    public String getName() {
        return name;
    }

    public List<Integer> getValues() {
        return values;
    }
}