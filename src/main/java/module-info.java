module SortingGame {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jdk.compiler;

    opens game.sortingGame to javafx.fxml;
    exports game.sortingGame;

}