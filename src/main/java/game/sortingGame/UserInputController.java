package game.sortingGame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UserInputController implements Initializable {

    // JavaFX UI components
    @FXML
    public TextField textField0;
    @FXML
    public TextField textField1;
    @FXML
    public TextField textField2;
    @FXML
    public TextField textField3;
    @FXML
    public TextField textField4;
    @FXML
    public TextField textField5;
    @FXML
    public TextField textField6;
    @FXML
    public TextField textField7;
    @FXML
    public TextField textField8;
    @FXML
    public TextField textField9;
    @FXML
    public Button mergeSortButton;
    @FXML
    public Button quickSortButton;
    @FXML
    public Button insertionSortButton;
    @FXML
    public Button selectionSortButton;
    @FXML
    public Button bubbleSortButton;
    @FXML
    public Button backButton;

    @FXML
    public Label arrayLabel;

    // Array to store user inputs
    public String[] userInputArray = new String[10];

    @FXML
    public HBox textFieldContainer;

    // Singleton instance to access user data
    DataSingleton data = DataSingleton.getInstance();

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize text fields and set key press event handlers
        TextField[] textFields = {textField0, textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8, textField9};
        for (int i = 0; i < textFields.length; i++) {
            final int index = i;
            textFields[i].setOnKeyPressed(event -> handleKeyPress(event, index, textFields));
        }
    }

    // Handle key press events for text fields
    private void handleKeyPress(KeyEvent event, int index, TextField[] textFields) {
        if (event.getCode() == KeyCode.ENTER) {
            String input = textFields[index].getText();
            if (isValidNumber(input)) {
                userInputArray[index] = input;
                if (index < textFields.length - 1) {
                    // Move to the next text field
                    textFields[index + 1].requestFocus();
                } else {
                    // Display the final array and save it
                    arrayLabel.setText(Arrays.toString(userInputArray));
                    data.setUserArray(getSavedArray());
                    System.out.println("Array Saved");
                }
            } else {
                // Show an alert for invalid input
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid input. Please enter a valid number.");
                alert.showAndWait();
            }
        }
    }

    // Method to get the saved array as integers
    public int[] getSavedArray() {
        int[] savedArray = new int[10];
        savedArray[0] = Integer.parseInt(textField0.getText());
        savedArray[1] = Integer.parseInt(textField1.getText());
        savedArray[2] = Integer.parseInt(textField2.getText());
        savedArray[3] = Integer.parseInt(textField3.getText());
        savedArray[4] = Integer.parseInt(textField4.getText());
        savedArray[5] = Integer.parseInt(textField5.getText());
        savedArray[6] = Integer.parseInt(textField6.getText());
        savedArray[7] = Integer.parseInt(textField7.getText());
        savedArray[8] = Integer.parseInt(textField8.getText());
        savedArray[9] = Integer.parseInt(textField9.getText());
        return savedArray;
    }

    // Validate if the input is a valid number
    private boolean isValidNumber(String input) {
        try {
            Double.parseDouble(input); // Try parsing as a double
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to switch back to the start scene
    public void back() {
        StageManager.getInstance().switchScene("Start.fxml");
    }

    // Methods to switch to different sorting algorithm scenes
    @FXML
    public void toMerge() {
        StageManager.getInstance().switchScene("MergeSort.fxml");
    }

    @FXML
    public void toBubble() {
        StageManager.getInstance().switchScene("BubbleSort.fxml");
    }

    @FXML
    public void toInsertion() {
        StageManager.getInstance().switchScene("InsertionSort.fxml");
    }

    @FXML
    public void toSelect() {
        StageManager.getInstance().switchScene("SelectionSort.fxml");
    }

    @FXML
    public void toQuick() {
        StageManager.getInstance().switchScene("QuickSort.fxml");
    }
}
