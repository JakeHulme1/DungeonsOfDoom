import java.util.Scanner; // To get user input

/**
 * The Game class is the executable class containing a main method to run the
 * game
 */
public class Game {

    /**
     * This is the main method which can be run to play the game in the terminal
     * 
     * @param args
     */
    public static void main(String[] args) {

        /*
         * Initialise a board- all boards are 20 x 9 in the 'Maps' folder. If new maps
         * are added, when loading them in, the correct dimensions must be input here
         */
        Board board = new Board(20, 9);

        // Input file path (directoryPath) and map name (fileName)
        String directoryPath = "./maps";
        String fileName = "map2.txt";

        // Load in the map to the 'board' object
        board.loadMap(directoryPath, fileName);

        // Initialise a human player and a bot player, both at random positions
        HumanPlayer player = new HumanPlayer(board);
        BotPlayer bot = new BotPlayer(board);

        // Display the initial board so player can see the starting position of bot and
        // themselves
        board.displayBoard(player);

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // Welcome message
        System.out.println("Welcome to Dungeons of Doom! Enter your commands to collect the gold and evade the bot!");

        // Infinite loop which gets user input until they type 'quit'
        while (!player.isGameEnded()) {
            System.out.print(">");
            input = scanner.nextLine().trim(); // Use trim to remove any leading or trailing whitespace

            // Take user input and execute their move
            player.handleUserInput(input);

            // Determine the bot's next move and move the bot
            Direction botDirection = bot.determineNextMove(player);
            if (botDirection != null) {
                bot.move(botDirection);
            }

            // If bot and player are on the same position, game over
            if (bot.getXCoordinate() == player.getXCoordinate() && bot.getYCoordinate() == player.getYCoordinate()) {
                System.out.println("The bot caught you!");
                player.setGameEnded(true);
            }
        }
        // Close the scanner
        scanner.close();
        System.out.println("Game Over!");
    }
}