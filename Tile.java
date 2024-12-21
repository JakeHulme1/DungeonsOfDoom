/**
 * The Tile class represents asingle tileon the board.
 * 
 * Each tile has a type which is defined by the TileType enum. The reason for
 * separating the Tile class and TileType enum isto follow OOP best practises:
 * Encapsulation and single repsonsibilty (more info in README file)
 */
public class Tile {

    /**
     * The type of the tile, defined by the TileType enum.
     */
    private TileType type;

    /**
     * Constructs a new Tile with the specified type.
     *
     * @param type the type of the tile
     */
    public Tile(TileType type) {
        this.type = type;
    }

    /**
     * Gets the type of the tile.
     *
     * @return the type of the tile
     */
    public TileType getType() {
        return type;
    }

    /**
     * Sets the type of the tile.
     *
     * @param type the new type of the tile
     */
    public void setType(TileType type) {
        this.type = type;
    }
}
