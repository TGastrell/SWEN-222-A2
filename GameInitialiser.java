import java.util.ArrayList;
import java.util.List;

/**
 * Class designed to initialise the game before it begins.
 * @author Tim Gastrell
 *
 */
public class GameInitialiser {
	
	/**
	 * Bags of pieces  for players one and two
	 */
	private List<PieceTile> bag1, bag2;
	
	/**
	 * Game board
	 */
	private Board board;
	
	/**
	 * 
	 * @param board game board
	 * @param bag1 player 1 bag
	 * @param bag2 player 2 bag
	 */
	public GameInitialiser(Board board, ArrayList<PieceTile> bag1, ArrayList<PieceTile> bag2) {
		this.bag1 = bag1;
		this.bag2 = bag2;
		this.board = board;
		initialiseBags();
		initialiseBoard();
	}
	
	/**
	 * Adds a new piece to player 1 bag
	 * 
	 * @param id Letter identification
	 * @param N Top item
	 * @param E Right item
	 * @param S Bottom item
	 * @param W Left item
	 */
	private void addToBag1(GridSquare.Type id, GridSquare.Type N, GridSquare.Type E, GridSquare.Type S, GridSquare.Type W) {
		bag1.add(new PieceTile(new GridSquare(id), new GridSquare[] {
				new GridSquare(N),
				new GridSquare(E),
				new GridSquare(S),
				new GridSquare(W)
		}));
	}
	
	/**
	 * Adds a new piece to player 2 bag
	 * 
	 * @param id Letter identification
	 * @param N Top item
	 * @param E Right item
	 * @param S Bottom item
	 * @param W Left item
	 */
	private void addToBag2(GridSquare.Type id, GridSquare.Type N, GridSquare.Type E, GridSquare.Type S, GridSquare.Type W) {
		bag2.add(new PieceTile(new GridSquare(id), new GridSquare[] {
				new GridSquare(N),
				new GridSquare(E),
				new GridSquare(S),
				new GridSquare(W)
		}));
	}
	
	/**
	 * Initialises bags by passing information for each piece to addtobag methods
	 */
	private void initialiseBags() {
		//Player one bag
		addToBag1(GridSquare.Type.a, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag1(GridSquare.Type.b, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag1(GridSquare.Type.c, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD);
		addToBag1(GridSquare.Type.d, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY);
		addToBag1(GridSquare.Type.e, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY);
		addToBag1(GridSquare.Type.f, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag1(GridSquare.Type.g, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.HORIZONTAL_SWORD, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag1(GridSquare.Type.h, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD);
		addToBag1(GridSquare.Type.i, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY);
		addToBag1(GridSquare.Type.j, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD);
		addToBag1(GridSquare.Type.k, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag1(GridSquare.Type.l, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY);
		addToBag1(GridSquare.Type.m, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY);
		addToBag1(GridSquare.Type.n, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY);
		addToBag1(GridSquare.Type.o, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD);
		addToBag1(GridSquare.Type.p, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag1(GridSquare.Type.q, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD);
		addToBag1(GridSquare.Type.r, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD);
		addToBag1(GridSquare.Type.s, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD);
		addToBag1(GridSquare.Type.t, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY);
		addToBag1(GridSquare.Type.u, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag1(GridSquare.Type.v, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY);
		addToBag1(GridSquare.Type.w, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD);
		addToBag1(GridSquare.Type.x, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD);
		
		//Player two bag
		addToBag2(GridSquare.Type.A, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag2(GridSquare.Type.B, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag2(GridSquare.Type.C, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD);
		addToBag2(GridSquare.Type.D, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY);
		addToBag2(GridSquare.Type.E, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY);
		addToBag2(GridSquare.Type.F, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag2(GridSquare.Type.G, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.HORIZONTAL_SWORD, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag2(GridSquare.Type.H, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD);
		addToBag2(GridSquare.Type.I, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY);
		addToBag2(GridSquare.Type.J, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD);
		addToBag2(GridSquare.Type.K, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag2(GridSquare.Type.L, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY);
		addToBag2(GridSquare.Type.M, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY);
		addToBag2(GridSquare.Type.N, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY);
		addToBag2(GridSquare.Type.O, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD);
		addToBag2(GridSquare.Type.P, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag2(GridSquare.Type.Q, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD);
		addToBag2(GridSquare.Type.R, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD);
		addToBag2(GridSquare.Type.S, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD);
		addToBag2(GridSquare.Type.T, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY);
		addToBag2(GridSquare.Type.U, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY, GridSquare.Type.HORIZONTAL_SWORD);
		addToBag2(GridSquare.Type.V, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.EMPTY, GridSquare.Type.EMPTY);
		addToBag2(GridSquare.Type.W, GridSquare.Type.VERTICAL_SWORD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD);
		addToBag2(GridSquare.Type.X, GridSquare.Type.EMPTY, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD, GridSquare.Type.SHIELD);
	}
	
	/*
	 * Initialises a new board by creating the player, out of bounds and creation tiles.
	 */
	private void initialiseBoard() {
		//Set all squares to EMPTY
		for(int x = 0; x < board.getWidth(); x++) {
			for(int y = 0; y < board.getHeight(); y++) {
				board.setSquare(new GridSquare(GridSquare.Type.EMPTY), x, y);
			}
		}
		
		//Assign player tiles
		setFixedTile(new PlayerTile(GridSquare.Type.PLAYER_ONE_TILE), Board.PLAYER_ONE_POSITION_X, Board.PLAYER_ONE_POSITION_Y);
		setFixedTile(new PlayerTile(GridSquare.Type.PLAYER_TWO_TILE), Board.PLAYER_TWO_POSITION_X, Board.PLAYER_TWO_POSITION_Y);
		
		//Assign Creation tiles
		setFixedTile(new CreationTile(GridSquare.Type.CREATION_TILE), Board.PLAYER_ONE_POSITION_X+3, Board.PLAYER_ONE_POSITION_Y+3);
		setFixedTile(new CreationTile(GridSquare.Type.CREATION_TILE), Board.PLAYER_TWO_POSITION_X-3, Board.PLAYER_TWO_POSITION_Y-3);
		
		//Assign Out of Bounds tiles
		setFixedTile(new OutOfBoundsTile(GridSquare.Type.OUT_OF_BOUNDS), Board.PLAYER_ONE_POSITION_X-3, Board.PLAYER_ONE_POSITION_Y);
		setFixedTile(new OutOfBoundsTile(GridSquare.Type.OUT_OF_BOUNDS), Board.PLAYER_ONE_POSITION_X, Board.PLAYER_ONE_POSITION_Y-3);
		setFixedTile(new OutOfBoundsTile(GridSquare.Type.OUT_OF_BOUNDS), Board.PLAYER_ONE_POSITION_X-3, Board.PLAYER_ONE_POSITION_Y-3);
		
		setFixedTile(new OutOfBoundsTile(GridSquare.Type.OUT_OF_BOUNDS), Board.PLAYER_TWO_POSITION_X+3, Board.PLAYER_TWO_POSITION_Y);
		setFixedTile(new OutOfBoundsTile(GridSquare.Type.OUT_OF_BOUNDS), Board.PLAYER_TWO_POSITION_X, Board.PLAYER_TWO_POSITION_Y+3);
		setFixedTile(new OutOfBoundsTile(GridSquare.Type.OUT_OF_BOUNDS), Board.PLAYER_TWO_POSITION_X+3, Board.PLAYER_TWO_POSITION_Y+3);
		
		
	}
	
	/**
	 * Sets fixed tiles onto the board.
	 * 
	 * @param tile tile to be set
	 * @param x X position
	 * @param y Y position
	 */
	private void setFixedTile(FixedTile tile, int x, int y) {
		GridSquare.Type type = tile.getType();
		for(int i =  x; i < x+3; i++) {
			for(int j = y; j < y+3; j++) {
				board.setSquare(new GridSquare(type) , i, j);
			}
		}
	}

	public List<PieceTile> getBag1() {
		return bag1;
	}

	public List<PieceTile> getBag2() {
		return bag2;
	}

	public Board getBoard() {
		return board;
	}
	
}
