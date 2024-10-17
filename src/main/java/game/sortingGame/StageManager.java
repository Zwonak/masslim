package game.sortingGame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class StageManager {
    private static StageManager instance;
    private Stage primaryStage;

    // Private constructor to prevent direct instantiation
    private StageManager() {
    }

    // Method to get the single instance of StageManager
    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    // Method to set the primary stage
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Method to switch scenes
    public void switchScene(String fxmlPath) {
        try {
            // Load the new FXML file and set it as the root
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // Handle any exceptions (e.g., FXML file not found)
            System.out.println("FXML file not found");
        }
    }
}

