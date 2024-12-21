import java.io.*;

/**
 * This class represents the game board.
 * It manages the individual tiles, dimensions of the board, loading in the
 * board,
 * displaying the board, and the amount of gold required to win.
 */
public class Board {

    /**
     * The 2D array of tiles representing the board
     */
    private Tile[][] tiles;

    /**
     * The width of the board - user needs to input dimensions for printing board
     * and checking moves are within bounds (maps aren't always surrounded by walls)
     */
    private int width;

    /**
     * The height of the board - user needs to input dimensions for printing board
     * and checking moves are within bounds (maps aren't always surrounded by walls)
     */
    private int height;

    /**
     * The amount of gold required to win the game
     */
    private int goldToWin;

    /**
     * Constructs a new board with the specified width and height
     * 
     * @param width
     * @param height
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    /**
     * Sets the amount of gold required to win the game.
     *
     * @param goldToWin the amount of gold required to win
     */
    public void setGoldToWin(int goldToWin) {
        this.goldToWin = goldToWin;
    }

    /**
     * Gets the amount of gold required to win the game.
     *
     * @return the amount of gold required to win
     */
    public int getGoldToWin() {
        return this.goldToWin;
    }

    /**
     * Gets the width of the board.
     *
     * @return the width of the board
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the board.
     *
     * @return the height of the board
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the tile at the specified coordinates.
     *
     * @param xCoordinate the x-coordinate of the tile
     * @param yCoordinate the y-coordinate of the tile
     * @return the tile at the specified coordinates
     */
    public Tile getTile(int xCoordinate, int yCoordinate) {
        return tiles[xCoordinate][yCoordinate];
    }

    /**
     * Reads the map from a given directory path and filename and stores it in the
     * tiles 2D array
     * 
     * @param directoryPath
     * @param fileName
     */
    public void loadMap(String directoryPath, String fileName) {

        File file = new File(directoryPath, fileName);

        // Use try-with-resources when reading files in Java so file is auto closed at
        // end of try block
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            // Read the first line (don't need to do anything with it, its just the map
            // title)
            String line = br.readLine();

            // Read the second line to get amount of gold required to win
            line = br.readLine();
            if (line != null && line.startsWith("win")) {
                // Following the space is the number of gold required to win, use mutator to set
                // goldToWin
                setGoldToWin(Integer.parseInt(line.split(" ")[1]));
            }

            // Read the rest of the map
            // y counts the number of rows
            int y = 0;

            // While the line isn't empty and we haven't exceeded 'height', populate the
            // tiles array
            while ((line = br.readLine()) != null && y < height) {
                for (int x = 0; x < line.length() && x < width; x++) {
                    char tileChar = line.charAt(x);
                    tiles[x][y] = createTileFromChar(tileChar);
                }
                y++; // Move to next row
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when trying to load the file :/");
        }
    }

    /**
     * Assigns each tile in tiles array a TileType depending on its character (see
     * TileType class tosee different types).
     * Used by loadMap() method to assign each character a type (each type has
     * different functionality)
     * Type is private as this is an internal helper function,no other classes need
     * access to this
     * 
     * @param tileChar The inputted character which is assigned a tile type
     * @return the tile type for each character
     */
    private Tile createTileFromChar(char tileChar) {

        // Used switch statements as this is much more concise than if statements and
        // there is a
        // finite number of tile types
        switch (tileChar) {
            case '#':
                return new Tile(TileType.WALL);
            case '.':
                return new Tile(TileType.EMPTY);
            case 'G':
                return new Tile(TileType.GOLD);
            case 'E':
                return new Tile(TileType.EXIT);
            case 'P':
                return new Tile(TileType.PLAYER);
            case 'B':
                return new Tile(TileType.BOT);
            default:
                return new Tile(TileType.EMPTY);
        }
    }

    /**
     * Returns the corresponding character for a tile when passed its TileType.
     *
     * @param type the tile type
     * @return the character representing the tile type (used for displaying the
     *         map)
     */
    private char getCharFromTileType(TileType type) {
        switch (type) {
            case WALL:
                return '#';
            case EMPTY:
                return '.';
            case GOLD:
                return 'G';
            case EXIT:
                return 'E';
            case PLAYER:
                return 'P';
            case BOT:
                return 'B';
            default:
                return ' ';
        }
    }

    /**
     * Prints the board to the terminal, used by the LOOK command
     * 
     * @param player need to pass the player so that their position can be printed
     */
    public void displayBoard(Player player) {

        // Get coordinates of player's current position
        int playerX = player.getXCoordinate();
        int playerY = player.getYCoordinate();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // If on player's current poisition, display 'P'. The player's position should
                // always overwrite the
                // the tile it is on
                if (x == playerX && y == playerY) {
                    System.out.print('P' + " ");
                }
                // Else, display the character represented by the tile type
                else {
                    System.out.print(getCharFromTileType(tiles[x][y].getType()) + " ");
                }
            }
            System.out.println();
        }
    }
}