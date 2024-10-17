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

public class MergeSort implements Initializable {

    // JavaFX UI components
    public Button backButton;
    public Label userArrayLabel;
    public Label sortedArray;
    public Button StartBtn;
    public Label ArrayLoop;
    public Label ActionLabel;

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

    // Method to start the merge sort process
    @FXML
    public void start() {
        mergeSort(userArray);
    }

    // Method to switch back to the user input scene
    public void back() {
        StageManager.getInstance().switchScene("UserInput.fxml");
    }

    // Method to perform the Merge Sort algorithm
    public void mergeSort(int[] userArray) {
        if (userArray.length > 1) {
            // Split the array into left and right halves
            int mid = userArray.length / 2;
            int[] left = Arrays.copyOfRange(userArray, 0, mid);
            int[] right = Arrays.copyOfRange(userArray, mid, userArray.length);

            // Display the split arrays
            splitDisplay(left, right);

            // Recursively sort both halves
            mergeSort(left);
            mergeSort(right);

            // Merge the sorted halves
            merge(userArray, left, right);
        }
    }

    // Method to merge two sorted arrays
    private void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge the left and right arrays
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }

        // Display the merged array
        mergeDisplay(arr, left, right);

        // If the array is fully merged, display the final sorted array
        if (arr.length == 10) {
            lastDisplay();
        }
    }

    // Method to display the split arrays
    public void splitDisplay(int[] leftArray, int[] rightArray) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            ActionLabel.setText("Split Array");
            ArrayLoop.setText("Left:" + Arrays.toString(leftArray) + " Right: " + Arrays.toString(rightArray));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        durationCount++;
    }

    // Method to display the merged arrays
    public void mergeDisplay(int[] array, int[] left, int[] right) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            ActionLabel.setText(" Merge Array");
            sortedArray.setText("left elements: " + Arrays.toString(left) + " right elements:" + Arrays.toString(right));
            ArrayLoop.setText(Arrays.toString(array));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        durationCount++;
    }

    // Method to display the final sorted array
    public void lastDisplay() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            ActionLabel.setText("");
            sortedArray.setText("Done:");
            ArrayLoop.setText(Arrays.toString(userArray));
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        durationCount++;
    }
}
