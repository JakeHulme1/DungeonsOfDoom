public class HumanPlayer extends Player {

    // Fields
    private boolean gameEnded = false;
    private boolean wasGold = false;

    // Accessor
    public boolean isGameEnded() {
        return this.gameEnded;
    }

    // Mutator
    public void setGameEnded(boolean status) {
        this.gameEnded = status;
    }

    // Constructor
    public HumanPlayer(int startX, int startY, Board board) {
        super(startX, startY, board);

        // Set player's tile typ to player to display as a 'P' on player's screen
        board.getTile(startX, startY).setType(TileType.PLAYER);
    }

    // Methods

    // Tells user how much gold they need to collect to win
    public void hello(Board board) {
        System.out.println("Gold to win: " + board.getGoldToWin());
    }

    // Tells user how much gold they currently own
    public void gold() {
        System.out.println("Gold owned: " + getGold());
    }

    /*
     * Pick up gold on players location if there is gold on that tile.
     * Increment gold owned
     * Change tile to empty tile
     * Return result
     */
    @Override
    public void pickUpGold() {
        // Use inheritancefrom Player to access voard
        Board board = getBoard();
        Tile currentTile = board.getTile(getXCoordinate(), getYCoordinate());

        // If tile is a GOLD tile, increment gold owned, change tile to empty, return
        // result

        if (wasGold) {
            setGold(getGold() + 1);
            currentTile.setType(TileType.EMPTY); // Set the tile to EMPTY after picking up gold
            wasGold = false; // Reset wasGold after picking up
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

                // If the player is moving off a gold tile and hasn't picked it up, revert it
                // back to gold
                if (getPreviousTileType() == TileType.GOLD && !wasGold) {
                    currentTile.setType(TileType.GOLD);
                } else {
                    currentTile.setType(getPreviousTileType());
                }

                // Update player's position
                setXCoordinate(newX);
                setYCoordinate(newY);

                // Store the original type of the target tile
                setPreviousTileType(targetTile.getType());

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
    public String quit() {

        Tile currentTile = getBoard().getTile(getXCoordinate(), getYCoordinate());
        Board board = getBoard();
        if (currentTile.getType() == TileType.EXIT && getGold() == board.getGoldToWin()) {
            System.out.println("Congratulations! You have won the game!");
            setGameEnded(true);
            return "WIN";
        } else {
            System.out.println("Our commiserations. Better luck nex time!");
            setGameEnded(true);
            return "LOSE";
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