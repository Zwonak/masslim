package game.sortingGame;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Start.fxml")));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SortingGame");
        primaryStage.show();

        StageManager.getInstance().setPrimaryStage(primaryStage);


    }
}
