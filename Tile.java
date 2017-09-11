/**
 * Parent class for all tiles.
 * @author Tim Gastrell
 *
 */
public abstract class Tile {
	public Tile(){		
	}
}

/**
 * Class representing a piece for Sword and Shield game
 * @author Tim Gastrell
 *
 */
class PieceTile extends Tile{
	
	/**
	 * Unique items of each piece in order of N E S W (Swords, Shields or Empty)
	 */
	private GridSquare[] items;
	
	/**
	 * Identifying square for piece (a, b, c..)
	 */
	private GridSquare id;
	
	/**
	 * Position of piece on board
	 */
	private int x, y;
	
	/**
	 * Identifies which player this piece belongs to
	 */
	private int player;
	
	/**
	 * Initialises piece and allocates items
	 * @param id Identification square
	 * @param items unique array of items
	 */
	public PieceTile(GridSquare id, GridSquare[] items) {
		setItems(items);
		setId(id);
		id.setTile(this);
		Character c = id.getType().name().charAt(0);
		if(Character.isUpperCase(c)) {
			player = 2;
		}
		else {
			player = 1;
		}
		for(int i = 0; i < 4; i++) {
			items[i].setTile(this);
		}
	}
	
	/**
	 * Stores location of piece
	 * 
	 * @param x X position
	 * @param y Y position
	 */
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y;
	}
	 
	public void setItems(GridSquare[] items) {
		this.items = items;
	}

	public GridSquare[] getItems() {
		return items;
	}

	public void setId(GridSquare id) {
		this.id = id;
	}
	
	public int getPlayer() {
		return player;
	}

	public GridSquare getId() {
		return id;
	}
	
}

/**
 * Extension of Tile for tiles that do not move (Player, creation etc...)
 * @author Tim Gastrell
 *
 */
class FixedTile extends Tile {
	
	/**
	 * The type of tile this object is
	 */
	private GridSquare.Type fixedType;
	
	/**
	 * Initialises and sets type.
	 * @param fixedType
	 */
	public FixedTile(GridSquare.Type fixedType) {
		this.fixedType = fixedType;
	}
	
	/**
	 * Returns type of tile
	 * @return
	 */
	public GridSquare.Type getType() {
		return fixedType;
	}
}

/**
 * Extension of Fixed tile for player tiles
 * @author Tim Gastrell
 *
 */
class PlayerTile extends FixedTile {
	/**
	 * 
	 * @param playerId Player one or two
	 */
	public PlayerTile(GridSquare.Type playerId) {
		super(playerId);
	}
}

/**
 * Extension of fixed tile for creation tiles
 * @author Tim Gastrell
 *
 */
class CreationTile extends FixedTile {
	/**
	 * 
	 * @param creation creation tile
	 */
	public CreationTile(GridSquare.Type creation) {
		super(creation);
	}
}

/**
 * Extension of Fixed Tile for out of bounds tiles
 * @author Tim Gastrell
 *
 */
class OutOfBoundsTile extends FixedTile {
	/**
	 * 
	 * @param fixedType out of bounds tile
	 */
	public OutOfBoundsTile(GridSquare.Type fixedType) {
		super(fixedType);
	}
	
}



