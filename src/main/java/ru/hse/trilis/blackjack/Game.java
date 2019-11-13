package ru.hse.trilis.blackjack;

public class Game {
    public static final int MAX_POINTS = 21;

    private Deck deck = new Deck();
    private Player player = new Player();
    private Player croupier = new Player();
    private State state = State.CONTINUE;

    public Game() {
        for (int i = 0; i < 1; i++) {
            player.addCard(deck.getNextCard());
            croupier.addCard(deck.getNextCard());
        }
    }

    public PlayState getGameState() {
        return new PlayState(player.getCards(), state);
    }

    public void makeTurn() {
        var nextCard = deck.getNextCard();
        player.addCard(nextCard);
    }

    public FinalState stop() {
        while (croupier.calculateOptimalSum(MAX_POINTS) < 16) {
            croupier.addCard(deck.getNextCard());
        }
        state = State.FINISH;
        return new FinalState(player.calculateOptimalSum(MAX_POINTS), croupier.calculateOptimalSum(MAX_POINTS));
    }
}
