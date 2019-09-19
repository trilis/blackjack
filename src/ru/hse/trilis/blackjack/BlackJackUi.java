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
        GameState gameState = game.getGameState();

        if (gameState.getState() == State.FINISH) {
            finishGame();
            return;
        }

        String result = "";
        for (Card card : gameState.getCards()) {
            result += card.getName() + "\n";
        }

        result += "\n";
        cards.setText(result);
     }

    @FXML
    public void finishGame() {
        FinalState finalState = game.stop();

        yourScore.setText(Integer.valueOf(finalState.getPlayerSum()).toString());
        crScore.setText(Integer.valueOf(finalState.getCroupierSum()).toString());

        buttonMakeTurn.setDisable(true);
        buttonFinishGame.setDisable(true);

        String message = "";

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

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
        Parent root = FXMLLoader.load(getClass().getResource("BlackJackUi.fxml"));
        primaryStage.setTitle("BlackJack");
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}