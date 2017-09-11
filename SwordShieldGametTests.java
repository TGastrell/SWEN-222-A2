import static org.junit.Assert.*;

import org.junit.Test;
public class SwordShieldGametTests {
	
	
	@Test
	/*
	 * Tests the creation of a piece tile for player 1
	 */
	public void test_create_piece_01() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.a, 1);
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(game.getBoard().getSquare(Board.PLAYER_ONE_POSITION_X+4, Board.PLAYER_ONE_POSITION_Y+4).getType(), GridSquare.Type.a);	
		assertEquals(game.getBoard().getSquare(Board.PLAYER_ONE_POSITION_X+3, Board.PLAYER_ONE_POSITION_Y+4).getType(), GridSquare.Type.HORIZONTAL_SWORD);
		assertEquals(game.getBoard().getSquare(Board.PLAYER_ONE_POSITION_X+4, Board.PLAYER_ONE_POSITION_Y+3).getType(), GridSquare.Type.VERTICAL_SWORD);
		assertEquals(game.getBoard().getSquare(Board.PLAYER_ONE_POSITION_X+5, Board.PLAYER_ONE_POSITION_Y+4).getType(), GridSquare.Type.SHIELD);
		assertEquals(game.getBoard().getSquare(Board.PLAYER_ONE_POSITION_X+4, Board.PLAYER_ONE_POSITION_Y+5).getType(), GridSquare.Type.VERTICAL_SWORD);
	}
	
	@Test
	/*
	 * Tests the creation of a piece tile for player 2
	 */
	public void test_create_piece_02() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.A, 2);
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(game.getBoard().getSquare(Board.PLAYER_TWO_POSITION_X-2, Board.PLAYER_TWO_POSITION_Y-2).getType(), GridSquare.Type.A);	
	}
	
	@Test
	/*
	 * Tests the movement of an individual piece from a creation tile
	 */
	public void test_move_piece_01() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.A, 2);
			game.move(GridSquare.Type.A, SwordAndShieldGame.Dir.UP);
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.A, game.getBoard().getSquare(22, 19).getType());
		assertEquals(GridSquare.Type.CREATION_TILE, game.getBoard().getSquare(Board.PLAYER_TWO_POSITION_X-2, Board.PLAYER_TWO_POSITION_Y-2).getType());
	}
	
	@Test
	/*
	 * Tests the movement of an individual piece two places down
	 * tests the to see if vacated squares reset themselves.
	 */
	public void test_move_piece_02() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.a, 1);
			game.move(GridSquare.Type.a, SwordAndShieldGame.Dir.DOWN);
			game.move(GridSquare.Type.a, SwordAndShieldGame.Dir.DOWN);
			
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.a, game.getBoard().getSquare(7, 13).getType());
		assertEquals(GridSquare.Type.EMPTY, game.getBoard().getSquare(7, 10).getType());
	}
	
	@Test
	/*
	 * Tests that a piece moved into another piece will push that other piece in the same direction
	 */
	public void test_move_piece_03() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.a, 1);
			game.move(GridSquare.Type.a, SwordAndShieldGame.Dir.DOWN);
			game.create(GridSquare.Type.b, 1);
			game.move(GridSquare.Type.b, SwordAndShieldGame.Dir.DOWN);
			
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.a, game.getBoard().getSquare(7, 13).getType());
		assertEquals(GridSquare.Type.b, game.getBoard().getSquare(7, 10).getType());
	}
	
	@Test
	/*
	 * Tests that a piece moved into another piece will push that other piece in the same direction
	 */
	public void test_move_piece_04() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.A, 2);
			game.move(GridSquare.Type.A, SwordAndShieldGame.Dir.LEFT);
			game.create(GridSquare.Type.B, 2);
			game.move(GridSquare.Type.B, SwordAndShieldGame.Dir.LEFT);
			
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.A, game.getBoard().getSquare(16, 22).getType());
		assertEquals(GridSquare.Type.B, game.getBoard().getSquare(19, 22).getType());
	}
	
	@Test
	/*
	 * Tests that a piece moved into another piece will push that other piece in the same direction
	 */
	public void test_move_piece_05() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.A, 2);
			game.move(GridSquare.Type.A, SwordAndShieldGame.Dir.UP);
			game.create(GridSquare.Type.B, 2);
			game.move(GridSquare.Type.B, SwordAndShieldGame.Dir.UP);
			
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.A, game.getBoard().getSquare(22, 16).getType());
		assertEquals(GridSquare.Type.B, game.getBoard().getSquare(22, 19).getType());
	}
	
	@Test
	/*
	 * Tests that a piece moved into another piece will push that other piece in the same direction
	 */
	public void test_move_piece_06() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.a, 1);
			game.move(GridSquare.Type.a, SwordAndShieldGame.Dir.RIGHT);
			game.create(GridSquare.Type.b, 1);
			game.move(GridSquare.Type.b, SwordAndShieldGame.Dir.RIGHT);
			
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.a, game.getBoard().getSquare(13, 7).getType());
		assertEquals(GridSquare.Type.b, game.getBoard().getSquare(10, 7).getType());
	}
	
	@Test
	/*
	 * Tests that a piece moved into the out of bounds zone dies
	 */
	public void test_move_piece_07() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.c, 1);
			game.move(GridSquare.Type.c, SwordAndShieldGame.Dir.LEFT);
			game.move(GridSquare.Type.c, SwordAndShieldGame.Dir.UP);
			
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.EMPTY, game.getBoard().getSquare(1, 7).getType());
		assertEquals(GridSquare.Type.OUT_OF_BOUNDS, game.getBoard().getSquare(1, 4).getType());
	}
	
	@Test
	/*
	 * Tests that a piece moved off the board dies
	 */
	public void test_move_piece_08() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		putPiece(game, 6, 0);
		try {
			game.move(GridSquare.Type.a, SwordAndShieldGame.Dir.UP);
			
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.EMPTY, game.getBoard().getSquare(6, 0).getType());
	}
	
	@Test
	/*
	 * Tests that a piece moved off the board dies
	 */
	public void test_move_piece_09() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		try {
			game.create(GridSquare.Type.c, 1);
			game.move(GridSquare.Type.c, SwordAndShieldGame.Dir.UP);
			game.move(GridSquare.Type.c, SwordAndShieldGame.Dir.LEFT);
			
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.EMPTY, game.getBoard().getSquare(7, 4).getType());
	}
	
	@Test
	/*
	 * Tests creating a piece adds a new game state to stack
	 */
	public void test_undo_01() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		game.newTurn();
		try {		
			game.create(GridSquare.Type.c, 1);
				
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(1, game.getUndo().getStack().size());
	}
	
	@Test
	/*
	 * Tests undoing removes last element from stack
	 */
	public void test_undo_02() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		game.newTurn();
		try {		
			game.create(GridSquare.Type.c, 1);
			game.doUndo();
				
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(0, game.getUndo().getStack().size());
	}
	
	@Test
	/*
	 * Tests undoing undoes the last move on the board
	 */
	public void test_undo_03() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		game.newTurn();
		try {		
			game.create(GridSquare.Type.c, 1);
			game.doUndo();
				
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(GridSquare.Type.CREATION_TILE, game.getBoard().getSquare(7, 7).getType());
	}
	
	@Test
	/*
	 * Tests undoing a creation puts the piece back in its bag
	 */
	public void test_undo_04() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		game.newTurn();
		try {		
			game.create(GridSquare.Type.c, 1);
			game.doUndo();
				
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		assertEquals(24 ,game.getBag1().getBag().size());
	}
	
	@Test
	/*
	 * Tests that the correct game state (in this case an empty board) is saved when pieces are created
	 */
	public void test_undo_05() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		game.newTurn();
		try {		
			game.create(GridSquare.Type.c, 1);
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		GridSquare.Type x = game.getUndo().getStack().peek().getBoard().getSquare(7, 7).getType();
		assertEquals(GridSquare.Type.CREATION_TILE,x);
	}
	
	@Test
	/*
	 * Tests the temporary victory condition for player 1 (via player 2 suicide)
	 */
	public void test_victory_no_reactions_01() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		game.newTurn();
		try {		
			game.create(GridSquare.Type.a, 1);
			game.move(GridSquare.Type.a, SwordAndShieldGame.Dir.LEFT);
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		int winner = game.checkForWinner();
		assertEquals(2, winner);
	}
	
	@Test
	/*
	 * Tests the temporary victory condition for player 2 (via player 1 suicide)
	 */
	public void test_victory_no_reactions_02() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		game.newTurn();
		try {		
			game.create(GridSquare.Type.A, 2);
			game.move(GridSquare.Type.A, SwordAndShieldGame.Dir.RIGHT);
		} catch (InvalidMove e) {
			e.printStackTrace();
		}
		int winner = game.checkForWinner();
		assertEquals(1, winner);
	}
	
	@Test
	/*
	 * Tests that a player cannot create a piece that is not their own
	 */
	public void test_exception_handling_01() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		boolean x = false;
		try {		
			game.create(GridSquare.Type.A, 1);
		} catch (InvalidMove e) {
			x = true;
		}
		int winner = game.checkForWinner();
		assertTrue(x);
	}
	
	@Test
	/*
	 * Tests that a player cannot create a piece that is not their own
	 */
	public void test_exception_handling_02() {
		SwordAndShieldGame game = new SwordAndShieldGame(30, 30);
		boolean x = false;
		try {		
			game.create(GridSquare.Type.a, 2);
		} catch (InvalidMove e) {
			x = true;
		}
		int winner = game.checkForWinner();
		assertTrue(x);
	}
	
	
	//Puts an arbitrary piece on the board
	public void putPiece(SwordAndShieldGame game, int x, int y) {
		PieceTile piece = new PieceTile(new GridSquare(GridSquare.Type.a),
				new GridSquare[] {new GridSquare(GridSquare.Type.VERTICAL_SWORD), 
						new GridSquare(GridSquare.Type.SHIELD),
						new GridSquare(GridSquare.Type.VERTICAL_SWORD),
						new GridSquare(GridSquare.Type.HORIZONTAL_SWORD)});
		
		game.getBoard().setTile(piece, x, y);
	}
}
