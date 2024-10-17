package game.sortingGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SelectionSort implements Initializable {

    // JavaFX UI components
    public Button backButton;
    public Label userArrayLabel;
    public Button startBtn;
    public Label actionLabel;
    public Label arrayLoop;

    // Singleton instance to access user data
    DataSingleton data = DataSingleton.getInstance();

    // Array to store user input
    int[] userArray = data.getUserArray();

    // Counter for timing the display updates
    int durationCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Display the initial user array
        userArrayLabel.setText(Arrays.toString(userArray));
    }

    // Method to start the selection sort process
    @FXML
    public void StartButton() {
        selectionSort(userArray);
    }

    // Method to perform the Selection Sort algorithm
    public void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // Find the minimum element in the unsorted part of the array
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element of the unsorted part
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

            // Take a snapshot of the array's state and display it
            int[] snap = Arrays.copyOf(arr, arr.length);
            swapDisplay(snap, Arrays.toString(new int[]{snap[minIndex]}));
        }
    }

    // Method to display the swap and current state of the array
    public void swapDisplay(int[] arr, String min) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            actionLabel.setText("Minimum index is :" + min);
            arrayLoop.setText(Arrays.toString(arr));
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        durationCount++;
    }

    // Method to switch back to the user input scene
    public void back() {
        StageManager.getInstance().switchScene("UserInput.fxml");
    }
}

