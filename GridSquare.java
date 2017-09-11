

/**
 * This interface represents a single square on the board
 * @author Tim Gastrell
 */
public class GridSquare { 
	
	/**
	 * The type of square
	 */
	private Type id;
	
	/**
	 * If a square belongs to a particular tile, such as a sword square it is referenced here
	 */
	private Tile tile;
	
	/**
	 * All possible types of Square
	 * @author Tim Gastrell
	 */
	public enum Type{
		A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,
		a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,
		SHIELD,
		VERTICAL_SWORD,
		HORIZONTAL_SWORD,
		EMPTY,
		PLAYER_ONE_TILE,
		PLAYER_TWO_TILE,
		CREATION_TILE,
		OUT_OF_BOUNDS;
	};
	
	/**
     * Construct a new Square with a given graphic, which may or may not be
     * visible.
     * 
     * @param type of square
     */
	public GridSquare(Type id) {
		this.id = id;
	}
	
	public Type getType() {
		return id;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
}
