package ru.hse.trilis.blackjack;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller of game logic.
 */
public class BlackJackUi extends Application {
    @FXML private Button buttonMakeTurn;
    @FXML private Button buttonFinishGame;
    @FXML private Button buttonNewGame;
    @FXML private Label cards;
    @FXML private Label yourScore;
    @FXML private Label crScore;
    private Game game;

    @FXML
    public void makeTurnCall() {
        game.makeTurn();
        var gameState = game.getGameState();

        if (gameState.getState() == State.FINISH) {
            finishGame();
            return;
        }

        var result = new StringBuilder();
        for (Card card : gameState.getCards()) {
            result.append(card.getName()).append("\n");
        }

        result.append("\n");
        cards.setText(result.toString());
     }

    @FXML
    public void finishGame() {
        FinalState finalState = game.stop();

        yourScore.setText(Integer.valueOf(finalState.getPlayerSum()).toString());
        crScore.setText(Integer.valueOf(finalState.getCroupierSum()).toString());

        buttonMakeTurn.setDisable(true);
        buttonFinishGame.setDisable(true);

        var message = "";

        switch (finalState.getWinner()) {
            case PLAYER:
                message = "PLAYER";
                break;
            case DRAW:
                message = "DRAW";
                break;
            case CROUPIER:
                message = "CROUPIER";
                break;
        }

        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @FXML
    public void newGame() {
        cards.setText("No cards");
        yourScore.setText("");
        crScore.setText("");

        buttonMakeTurn.setDisable(false);
        buttonFinishGame.setDisable(false);

        game = new Game();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var resource = getClass().getClassLoader().getResource("BlackJackUi.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);

        primaryStage.setTitle("BlackJack");
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}