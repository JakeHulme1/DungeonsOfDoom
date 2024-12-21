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
     * @param startX the starting x-coordinate
     * @param startY the starting y-coordinate
     * @param board  the board the player is playing on
     */
    public HumanPlayer(int startX, int startY, Board board) {
        super(startX, startY, board);

        // Set player's tile type to player to display as a 'P' on player's screen
        board.getTile(startX, startY).setType(TileType.PLAYER);
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
     * @param status
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
     */
    public void gold() {
        System.out.println("Gold owned: " + getGold());
    }

    /**
     * Picks up gold on the player's location if there is gold on that tile.
     * Increments the gold owned, changes the tile to an empty tile, and returns the
     * result.
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
            setOriginalTileType(TileType.EMPTY); // Update originalTileType to EMPTY
            System.out.println("Success. Gold owned: " + getGold());
        } else {
            System.out.println("Fail. Gold owned: " + getGold());
        }
    }

    // Moves the player in the specified direction unless they go into a wall
    @Override
    public void move(Direction direction) {

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

        if (isValidCoordinate(newX, newY)) {
            Tile targetTile = getBoard().getTile(newX, newY);
            if (targetTile.getType() != TileType.WALL) {
                Tile currentTile = getBoard().getTile(getXCoordinate(), getYCoordinate());

                // Restore the original tile type of the current position
                currentTile.setType(getOriginalTileType());

                // Update player's position
                setXCoordinate(newX);
                setYCoordinate(newY);

                // Store the original type of the target tile
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

    // Displays the board
    public void look(Board board) {
        board.displayBoard(this);
    }

    // Quits the game. If player wins, response is WIN, else it is LOSE
    @Override
    public void quit() {

        Board board = getBoard();
        if (getOriginalTileType() == TileType.EXIT && getGold() >= board.getGoldToWin()) {
            System.out.println("Congratulations! You have won the game!");
            setGameEnded(true);
        } else {
            System.out.println("You lost, our commiserations. Better luck nex time!");
            setGameEnded(true);
        }
    }

    // Checks if the coordinates are within range of the height and width and >= 0
    // It is private beacuse its an internal helper method
    private boolean isValidCoordinate(int x, int y) {
        Board board = getBoard();
        return x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight();
    }

    // Calls relevant functions based on user input
    public void handleUserInput(String input) {

        Board board = getBoard();

        // Handle the MOVE commands

        // Splits apart the inputted string into an array of substrings
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
}