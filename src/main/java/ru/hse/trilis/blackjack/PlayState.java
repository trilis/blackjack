package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class PlayState {
    @NotNull private List<Player> players;
    @NotNull private Player activePlayer;
    @NotNull private State state;

    public PlayState(@NotNull List<Player> players ,
                     @NotNull State state,
                     @NotNull Player activePlayer) {
        this.players = players;
        this.state = state;
        this.activePlayer = activePlayer;
    }

    @NotNull public State getState() {
        return state;
    }

    @NotNull public List<Player> getPlayers() {
        return players;
    }

    @NotNull public Player getActivePlayer() {
        return activePlayer;
    }
}