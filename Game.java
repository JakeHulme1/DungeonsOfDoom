import java.util.Scanner; // To get user input

public class Game {
    public static void main(String[] args) {

        // Initialise a board- all boards are 20 x 9 in the 'Maps' folder
        Board board = new Board(20, 9);

        // Input file location (directory) and map name
        String directoryPath = "./maps";
        String fileName = "map1.txt";

        // Load in the map to the 'board' object
        board.loadMap(directoryPath, fileName);

        // Initialise a human player
        // TODO: Implement random placement of player
        HumanPlayer player = new HumanPlayer(2, 2, board);

        // Display the initial board
        board.displayBoard(player);

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // Welcom message
        System.out.println("Welcome to Dungeons of Doom! Enter your commands to collect the gold and evade the bot!");

        // Infinite loop which gets user input until they type 'QUIT'
        while (!player.isGameEnded()) {
            System.out.println(">");
            input = scanner.nextLine().trim(); // Use trim to remove any leading or trailing whitespace

            // Call the method to handle the user input and store in variable to check if
            // game is over
            player.handleUserInput(input);
        }
        // Close the scanner
        scanner.close();
        System.err.println("Game Over!");
    }
}