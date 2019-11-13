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

import java.util.List;

/**
 * Controller of game logic.
 */
public class BlackJackUi extends Application {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 900;
    private static final String TITLE = "BlackJack";

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

        String result = getTurnMessage(gameState);
        cards.setText(result);
     }

    @NotNull
    private String getTurnMessage(GameState gameState) {
        var result = new StringBuilder();
        for (Player player : gameState.getPlayers()) {
            for (Card card : player.getCards()) {
                result.append(card.getName()).append("\n");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @FXML
    public void finishGame() {
        FinalState finalState = game.getFinalState();
        setResultLabels(finalState);
        disableButtons();
        displayAlertMessage(finalState);
    }

    private void displayAlertMessage(FinalState finalState) {
        String message = getResultMessage(finalState);
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setResultLabels(FinalState finalState) {
        yourScore.setText(Integer.valueOf(finalState.getResults().get(0).getSum()).toString());
        crScore.setText(Integer.valueOf(finalState.getCroupierSum()).toString());
    }

    @NotNull
    private String getResultMessage(FinalState finalState) {
        StringBuilder message = new StringBuilder();

        for (var result : finalState.getResults()) {
            message.append(result.getName()).append(": ").append(result.getSum());
        }
        return message.toString();
    }

    @FXML
    public void initNewGame() {
        initLabels();
        enableButtons();
        game = new Game(List.of("player1"));
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
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}