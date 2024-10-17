package game.sortingGame;


public class DataSingleton {

    // Single instance of DataSingleton
    private static final DataSingleton instance = new DataSingleton();

    // Array to store user input
    private int [] userArray;

    // Private constructor to prevent instantiation
    private DataSingleton(){}

    // Public method to get the single instance of DataSingleton
    public static DataSingleton getInstance(){
        return instance;
    }

    // Getter method for userArray
    public int[] getUserArray() {
        return userArray;
    }

    // Setter method for userArray
    public void setUserArray(int[] userArray) {
        this.userArray = userArray;
    }
}
