package ru.hse.trilis.blackjack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    @NotNull private List<Player> players;
    private Player activePlayer;

    public GameState(@NotNull List<Player> players,
                     Player activePlayer) {
        this.players = players;
        this.activePlayer = activePlayer;
    }

    @NotNull public List<Player> getPlayers() {
        return players;
    }

    @NotNull public Player getActivePlayer() {
        return activePlayer;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Player player : players) {
            builder.append(player);
            builder.append('$');
        }

        builder.append(activePlayer.getName());

        return builder.toString();
    }

    public static GameState fromString(String gameState) {
        String[] tokens = gameState.split("$");
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < tokens.length - 1; i++) {
            players.add(Player.fromString(tokens[i]));
        }

        String activePlayerName = tokens[tokens.length - 1];

        Player activePlayer = null;
        for (Player player : players) {
            if (player.getName().equals(activePlayerName)) {
                activePlayer = player;
            }
        }

        return new GameState(players, activePlayer);
    }
}