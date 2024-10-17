package game.sortingGame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    // JavaFX UI component
    @FXML
    public Button startButton;

    // Method to start the game and switch to the user input scene
    @FXML
    public void StartGame() {
        StageManager.getInstance().switchScene("UserInput.fxml");
    }
}
