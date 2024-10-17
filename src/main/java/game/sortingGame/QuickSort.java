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

public class QuickSort implements Initializable {

    // JavaFX UI components
    public Button backButton;
    public Label userArrayLabel;
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
        userArrayLabel.setText("Players Array : " + Arrays.toString(userArray));
    }

    // Method to start the quicksort process
    @FXML
    public void start() {
        quicksort(userArray);
    }

    // Method to switch back to the user input scene
    public void back() {
        StageManager.getInstance().switchScene("UserInput.fxml");
    }

    // Wrapper method to initiate quicksort
    public void quicksort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    // Recursive method to perform Quick Sort
    private void quicksort(int[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            lastDisplay();
            return;
        }
        int pivot = array[highIndex];
        int leftPointer = partition(array, lowIndex, highIndex, pivot);
        quicksort(array, lowIndex, leftPointer - 1);
        quicksort(array, leftPointer + 1, highIndex);
    }

    // Method to partition the array and find the correct position for the pivot
    private int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        // Loop until the left and right pointers cross
        while (leftPointer < rightPointer) {
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }

        // Swap the pivot to its correct position
        if (array[leftPointer] > array[highIndex]) {
            swap(array, leftPointer, highIndex);
        } else {
            leftPointer = highIndex;
        }

        // Display the partitioned arrays
        int[] leftArray = Arrays.copyOfRange(array, lowIndex, leftPointer);
        int[] rightArray = Arrays.copyOfRange(array, leftPointer + 1, highIndex + 1);
        pivotDisplay(leftArray, rightArray, Arrays.toString(new int[]{array[highIndex]}));

        return leftPointer;
    }

    // Method to swap two elements in the array
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    // Method to display the final sorted array
    public void lastDisplay() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            ActionLabel.setText("Done");
            ArrayLoop.setText(Arrays.toString(userArray));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    // Method to display the pivot selection and partitions
    public void pivotDisplay(int[] left, int[] right, String text) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            ActionLabel.setText("pivot selected :" + text);
            ArrayLoop.setText("Left Partition : " + Arrays.toString(left) + " Right Partition: " + Arrays.toString(right));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        durationCount++;
    }
}

