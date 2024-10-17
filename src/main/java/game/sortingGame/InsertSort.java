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

public class InsertSort implements Initializable {

    // JavaFX UI components
    public Button backButton;
    public Label userArrayLabel;
    public Label ActionLabel;
    public Button StartBtn;
    public Label ArrayLoop;

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

    // Method to start the insertion sort process
    public void start() {
        insertionSort(userArray);
    }

    // Method to switch back to the user input scene
    @FXML
    public void back() {
        StageManager.getInstance().switchScene("UserInput.fxml");
    }

    // Method to perform the Insertion Sort algorithm
    public void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0…i-1] that are greater than key to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;

            // Take a snapshot of the array's state and display it
            int[] snap = Arrays.copyOf(arr, arr.length);
            swapDisplay(snap, j + 1);
        }
    }

    // Method to display the swap and current state of the array
    public void swapDisplay(int[] arr, int key) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            ActionLabel.setText("KEY = " + Arrays.toString(new int[]{arr[key]}));
            ArrayLoop.setText(Arrays.toString(arr));
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        durationCount++;
    }
}

