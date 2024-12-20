public class Tile {
    
    // Fields - each tile has a tile type contained in the enum class 'TileType'
    private TileType type;

    // Constructor
    public Tile(TileType type) {
        this.type = type;
    }

    // Accessor
    public TileType getType() {
        return type;
    }

    // Mutator
    public void setType(TileType type) {
        this.type = type;
    }
}
