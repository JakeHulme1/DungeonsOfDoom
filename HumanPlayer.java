import java.util.Random; // To generate random numbers for starting position

/**
 * This class contains all the functionality of a Human Player which the user
 * controls through the
 * command-line
 */
public class HumanPlayer extends Player {

    /**
     * Boolean value which stores if the game has ended or not
     */
    private boolean gameEnded = false;

    /**
     * Constructs a new HumanPlayer with the specified starting coordinates and
     * board.
     *
     * @param board the board the player is playing on
     */
    public HumanPlayer(Board board) {
        super(board);
        randomStartingPosition(board, this);
    }

    /**
     * Returns the boolean value of whether the game has ended or not
     */
    public boolean isGameEnded() {
        return this.gameEnded;
    }

    /**
     * Sets the status of if the game is over or not
     * 
     * @param status boolean value which represents whether the game has ended or
     *               not
     */
    public void setGameEnded(boolean status) {
        this.gameEnded = status;
    }

    /**
     * Tells the user how much gold they need to collect to win on a certain baord.
     * Called via 'hello' command
     *
     * @param board the game board
     */
    public void hello(Board board) {
        System.out.println("Gold to win: " + board.getGoldToWin());
    }

    /**
     * Tells the user how much gold they currently own
     * Called via 'gold' command
     */
    public void gold() {
        System.out.println("Gold owned: " + getGold());
    }

    /**
     * Picks up gold on the player's location if there is gold on that tile.
     * Increments the gold owned, changes the tile to an empty tile, and returns the
     * result.
     * Called via 'pickup' command
     */
    @Override
    public void pickUpGold() {

        // Access the tile the player is currently on
        Tile currentTile = getBoard().getTile(getXCoordinate(), getYCoordinate());

        // If the tile was GOLD before the player moved onto it, increment gold and set
        // it to empty
        if (getOriginalTileType() == TileType.GOLD) {
            setGold(getGold() + 1);
            currentTile.setType(TileType.EMPTY); // Set the tile to EMPTY after picking up gold
            setOriginalTileType(TileType.EMPTY); // Update originalTileType to EMPTY (in case player moves over it
                                                 // again)
            System.out.println("Success. Gold owned: " + getGold());
        } else {
            System.out.println("Fail. Gold owned: " + getGold());
        }
    }

    /**
     * Moves the player in the specified direction unless they go into a wall or out
     * of bounds
     * Called via 'move [N,E,S or W]' command
     *
     * @param direction the direction in which to move the player
     */
    @Override
    public void move(Direction direction) {

        // Current coordinates of player, to be changed in the switch statements,
        // depending on the direction
        int newX = getXCoordinate();
        int newY = getYCoordinate();

        // Calculate new X or Y coordinate based on user input
        switch (direction) {
            case N:
                newY -= 1;
                break;
            case E:
                newX += 1;
                break;
            case S:
                newY += 1;
                break;
            case W:
                newX -= 1;
                break;
        }

        // Check the new position is not out of bounds
        if (isValidCoordinate(newX, newY)) {

            // Store info about the newtile using new coordinates
            Tile targetTile = getBoard().getTile(newX, newY);

            // Check the new tile is not a wall
            if (targetTile.getType() != TileType.WALL) {

                // Restore the original tile type of the current position
                Tile currentTile = getBoard().getTile(getXCoordinate(), getYCoordinate());
                currentTile.setType(getOriginalTileType());

                // Update player's position
                setXCoordinate(newX);
                setYCoordinate(newY);

                // Store the original type of the target tile (to keep track if it was
                // a gold or exit tile)
                setOriginalTileType(targetTile.getType());

                // Set the new position to PLAYER
                targetTile.setType(TileType.PLAYER);

                System.out.println("Success");
            } else {
                System.out.println("Fail. There's a wall in the way!");
            }
        } else {
            System.out.println("Fail. Out of bounds!");
        }
    }

    /**
     * Displays the board.
     *
     * @param board the game board
     */
    public void look(Board board) {
        board.displayBoard(this);
    }

    /**
     * Quits the game. If the player wins, the response is a win message, otherwise
     * its a lose message
     */
    @Override
    public void quit() {

        // If the player is on an exit tile and has enough gold to win, display win
        // message and set 'gameEnded' field to true
        Board board = getBoard();
        if (getOriginalTileType() == TileType.EXIT && getGold() >= board.getGoldToWin()) {
            System.out.println("Congratulations! You have won the game!");
            setGameEnded(true);
        }
        // If not, display lose message
        else {
            System.out.println("You lost. Better luck next time!");
            setGameEnded(true);
        }
    }

    /**
     * Checks if the passed coordinates are within range of the height and width and
     * >= 0.
     * It is private because it's an internal helper method.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the above coordinates are valid, false otherwise
     */
    private boolean isValidCoordinate(int x, int y) {
        Board board = getBoard();
        return x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight();
    }

    /**
     * Calls relevant functions based on user input in the command-line
     *
     * @param input the user input
     */
    public void handleUserInput(String input) {

        Board board = getBoard();

        /*
         * Handle the MOVE commands
         * 
         * // Splits apart the inputted string (where there is empty space " ") into an
         * array of substrings
         */
        String[] sections = input.split(" ");

        // If the input is 2 substrings with the first being MOVE, we can process the
        // move() method
        if (sections.length == 2 && sections[0].equalsIgnoreCase("MOVE")) {

            // Need to pass in a 'Direction' object to move() method to calc new coordinates
            Direction direction;
            switch (sections[1].toUpperCase()) {
                case "N":
                    direction = Direction.N;
                    break;
                case "S":
                    direction = Direction.S;
                    break;
                case "E":
                    direction = Direction.E;
                    break;
                case "W":
                    direction = Direction.W;
                    break;
                default:
                    System.out.println("Invalid direction.");
                    return;
            }
            // Call the move method and input instance of 'Direction' class
            move(direction);
        }

        // Handle the HELLO command
        else if (input.equalsIgnoreCase("HELLO")) {
            hello(board);
        }

        // Handle the GOLD command
        else if (input.equalsIgnoreCase("GOLD")) {
            gold();
        }

        // Handle the PICKUP command
        else if (input.equalsIgnoreCase("PICKUP")) {
            pickUpGold();
        }

        // Handle the LOOK command
        else if (input.equalsIgnoreCase("LOOK")) {
            look(board);
        }

        // Handle the QUIT command
        else if (input.equalsIgnoreCase("QUIT")) {
            quit();
        }

        else {
            System.out.println("Invalid command!");
        }
    }

    /**
     * Randomly places a player on an empty tile on the board.
     *
     * @param board  the game board
     * @param player the player to be placed
     */
    public void randomStartingPosition(Board board, Player player) {

        Random random = new Random();
        boolean validCoordinates = false;

        // Constantly generate random coordinates until its an empty space
        do {
            int randomX = random.nextInt((board.getWidth()));
            int randomY = random.nextInt(board.getHeight());

            // Check if the random coordinates are on an empty tile
            Tile tile = board.getTile(randomX, randomY);
            if (tile.getType() == TileType.EMPTY) {
                // Place player on the empty tile
                player.setXCoordinate(randomX);
                player.setYCoordinate(randomY);
                board.getTile(randomX, randomY).setType(TileType.PLAYER);

                // Exit loop
                validCoordinates = true;
            }
        } while (!validCoordinates);
    }
}