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


public class BubbleSort implements Initializable {

    // JavaFX UI components
    public Button backButton;
    public Label userArrayLabel;
    public Button start;
    public Label actionLabel;
    public Label sortedArrayLabel;
    public Label actionSwapLabel;
    public Label compareLabel;

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

    // Method to perform the Bubble Sort algorithm
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Display the comparison between elements
                compareDisplay(arr, j, j + 1);
                if (arr[j] > arr[j + 1]) {
                    int[] snap = Arrays.copyOf(arr, arr.length);
                    // Swap elements if they are in the wrong order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // Display the swap and current state of the array
                    swapDisplay(snap, j, j + 1);
                }
            }
        }
    }

    // Method to switch back to the user input scene
    @FXML
    public void back() {
        StageManager.getInstance().switchScene("UserInput.fxml");
    }

    // Method to display the comparison between elements
    public void compareDisplay(int[] array, int elementOne, int elementTwo) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            compareLabel.setText("Compare  " + Arrays.toString(new int[]{array[elementOne]}) + " and  " + Arrays.toString(new int[]{array[elementTwo]}));
            actionLabel.setText("");
            actionSwapLabel.setText("");
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        durationCount++;
    }

    // Method to display the swap and current state of the array
    public void swapDisplay(int[] array, int elementOne, int elementTwo) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(durationCount), _ -> {
            actionLabel.setText(Arrays.toString(new int[]{array[elementOne]}) + " is greater than " + Arrays.toString(new int[]{array[elementTwo]}));
            actionSwapLabel.setText("Swap  " + Arrays.toString(new int[]{array[elementOne]}) + " and  " + Arrays.toString(new int[]{array[elementTwo]}));
            sortedArrayLabel.setText(Arrays.toString(array));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        durationCount++;
    }

    // Method to initiate the sorting process
    @FXML
    public void StartButton() {
        bubbleSort(userArray);
    }
}




