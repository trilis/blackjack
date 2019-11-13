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
import org.jetbrains.annotations.NotNull;

/**
 * Controller of game logic.
 */
public class BlackJackUi extends Application {
    @FXML private Button buttonMakeTurn;
    @FXML private Button buttonFinishGame;
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

        String result = getResultMessage(gameState);
        cards.setText(result);
     }

    @NotNull
    private String getResultMessage(PlayState playState) {
        var result = new StringBuilder();
        for (Player player : playState.getPlayers()) {
            for (Card card : player.getCards()) {
                result.append(card.getName()).append("\n");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @FXML
    public void finishGame() {
        FinalState finalState = game.stop();

        yourScore.setText(Integer.valueOf(finalState.getPlayerSum()).toString());
        crScore.setText(Integer.valueOf(finalState.getCroupierSum()).toString());

        disableButtons();

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
    public void initNewGame() {
        initLabels();
        enableButtons();
        game = new Game();
    }

    private void enableButtons() {
        buttonMakeTurn.setDisable(false);
        buttonFinishGame.setDisable(false);
    }

    private void disableButtons() {
        buttonMakeTurn.setDisable(true);
        buttonFinishGame.setDisable(true);
    }

    private void initLabels() {
        cards.setText("No cards");
        yourScore.setText("");
        crScore.setText("");
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