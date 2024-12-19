public class HumanPlayer extends Player {

    // Fields
    private boolean gameEnded = false;

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
    public String pickUpGold() {
        // Use inheritancefrom Player to access voard
        Board board = getBoard();
        Tile currentTile = board.getTile(getXCoordinate(), getYCoordinate());

        // If tile is a GOLD tile, increment gold owned, change tile to empty, return
        // result
        if (currentTile.getType() == TileType.GOLD) {
            setGold(getGold() + 1);
            currentTile.setType(TileType.EMPTY);
            return "Success. Gold owned: " + getGold();
        } else {
            return "Fail. Gold owned: " + getGold();
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

        // Checks if the tile player wants to move into is in bounds and not a wall and
        // moves them if so. ELse, displays error message.
        if (isValidCoordinate(newX, newY)) {
            Tile targetTile = getBoard().getTile(newX, newY);
            if (targetTile.getType() != TileType.WALL) {
                setXCoordinate(newX);
                setYCoordinate(newY);
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
        board.displayBoard();
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
        else if (input.toUpperCase() == "HELLO") {
            hello(board);
        }

        // Handle the GOLD command
        else if (input.toUpperCase() == "GOLD") {
            gold();
        }

        // HAndle the PICKUP command
        else if (input.toUpperCase() == "PICKUP") {
            pickUpGold();
        }

        // Handle the look command
        else if (input.toUpperCase() == "LOOK") {
            look(board);
        }

        // Handle the QUIT command
        else if (input.toUpperCase() == "QUIT") {
            quit();
        }

        else {
            System.out.println("Invalid command!");
        }
    }

}