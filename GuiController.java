import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * This is the main controller class for teh Sword and Shield game.
 * Acts as the middle man between model and view. This class 
 * implements the rules of play regarding turns and moves within turns.
 * 
 * @author Tim Gastrell
 *
 */
public class GuiController {
	
	/**
	 * The Model, Sword and Shield game
	 */
	private SwordAndShieldGame game;
	
	/**
	 * The View, Graphcical User Interface
	 */
	private GraphicalUserInterface gui;
	
	/**
	 * True if a piece is selected
	 */
	private boolean pieceSelected = false;
	
	/**
	 * The currently selected tile (if applicable)
	 */
	private PieceTile selected = null;
	
	/**
	 * Represents the player who's turn it is
	 */
	private int turn = 1;
	
	/**
	 * The phase of the game. 1 = creation 2 = movement 3 = end
	 */
	private int phase;

	/**
	 * Constructs a new Gui Controller
	 * 
	 * @param game
	 * @param gui
	 */
	public GuiController(SwordAndShieldGame game, GraphicalUserInterface gui) {
		this.game = game;
		this.gui = gui;	
		initialiseGame();
		newTurn();
	}
	
	/**
	 * Initialises mouse and button listener
	 */
	private void initialiseGame() {
		gui.initialiseBagListener(new BagListener(1), 1);
		gui.initialiseBagListener(new BagListener(2), 2);
		gui.initialiseBoardListeners(new BoardListener());
		gui.addPassListner(new PassListener());
		gui.addSurrenderListner(new SurrenderListener());
		gui.addUndoListner(new UndoListener());
	}


	/**
	 * Function to run the gameplay itself.  
	 */
	private void newTurn() {
		assessVictoryStatus();
		game.newTurn();
		gui.updateCanvas(game.getBoard(), game.getBag1().getBoard(), game.getBag2().getBoard(), game.getCemetery().getBoard());
		phase = 1;

		//Creation phase
		while(phase == 1) {
			System.out.print("");
		}

		gui.updateCanvas(game.getBoard(), game.getBag1().getBoard(), game.getBag2().getBoard(), game.getCemetery().getBoard());

		//Movement phase
		while(phase == 2) {
			System.out.print("");
		}

		turn = (turn == 1) ? 2 : 1;
		newTurn();

	}
	
	/**
	 * Checks if there is a sword touching a player tile
	 */
	private void assessVictoryStatus() {
		int input = 0;
		if(game.checkForWinner() == 1) {
			input = JOptionPane.showOptionDialog(gui, "Player 1 wins", "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );
		}
		else if(game.checkForWinner() == 2) {
			input = JOptionPane.showOptionDialog(gui, "Player 2 wins", "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );
		}
		else {
			return;
		}
		
		if(input == JOptionPane.OK_OPTION || input == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
	}
	
	/**
	 * Inner class implementation of MouseListener designed to process clicks 
	 * on the bag displays.
	 * 
	 * @author Tim Gastrell
	 *
	 */
	class BagListener implements MouseListener {
		
		/**
		 * The player to whom the bag belongs (1 or 2)
		 */
		private int player;
		
		/**
		 * 
		 * @param player The player to whom the bag belongs
		 */
		public BagListener(int player) {
			this.player = player;
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {

			PieceTile selected = null;
			
			//Ensures a player cannot create pieces form the wrong bag
			if(player == turn && phase == 1) {
				
				int x = e.getX(), y = e.getY();
				GameBag bag = (player == 1) ? game.getBag1() : game.getBag2();
				
				//find tile in board corresponding to gui click action
				for(PieceTile piece : bag.getBag()) {
					if(x/20 <= piece.getX()+3 && x/20 >= piece.getX() && y/20 <= piece.getY()+3 && y/20 >= piece.getY()) {
						selected = piece;
						break;
					}
				}
				
				if(selected == null) {
					return;
				}
				try {
					game.create(selected.getId().getType(), player);
					phase = 2;
				} catch (InvalidMove e1) {}
				
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

	}
	
	/**
	 * Inner class implementation of MouseListener designed to process clicks 
	 * on the Board displays.
	 * 
	 * @author Tim Gastrell
	 *
	 */
	class BoardListener implements MouseListener {

		public BoardListener() {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			//If a piece is yet to be chosen, select piece at click location
			if(pieceSelected == false) {
				findSelectedPiece(e);
			}
			else { // Move piece in desired direction
				listenForDirection(e);
			}
		}
		
		/**
		 * Finds the piece at the click location and stores it as selected field
		 * 
		 * @param e MouseEvent for retrieving location
		 */
		private void findSelectedPiece(MouseEvent e) {
			selected = null;
			int x = e.getX(), y = e.getY();

			for(PieceTile piece : game.getPiecesInPlay()) {
				if(x/20 <= piece.getX()+3 && x/20 >= piece.getX() 
						&& y/20 <= piece.getY()+3 && y/20 >= piece.getY()) {
					selected = piece;
					break;
				}
			}
			
			if(selected == null) {
				return;
			}
			else if(selected.getPlayer() != turn) {
				return;
			}
			else {
				gui.highlightPiece(selected.getX()*20, selected.getY()*20);
				pieceSelected = true;
			}

		}
		
		/**
		 * Identifies the direction in which the user wants the selected piece moved.
		 * 
		 * 
		 * @param e MouseEvent for retrieving click location
		 */
		private void listenForDirection(MouseEvent e) {
			
			int x = e.getX(), y = e.getY();
			SwordAndShieldGame.Dir dir = null;

			if(game.getPiecesUsed().contains(selected.getId().getType())) {
				pieceSelected = false;
				return;
			}
			
			//Check Which direction the user clicked and assign the direction enum
			if(x <= selected.getX()*20 + 20 && x >=  selected.getX()*20 && y <= selected.getY()*20 + 40 && y >=  selected.getY()*20 + 20) {
				dir = SwordAndShieldGame.Dir.LEFT;
			}
			else if(x <= selected.getX()*20 + 60 && x >=  selected.getX()*20 + 40 && y <= selected.getY()*20 + 40 && y >=  selected.getY()*20 + 20) {
				dir = SwordAndShieldGame.Dir.RIGHT;
			}
			else if(x <= selected.getX()*20 + 40 && x >=  selected.getX()*20 + 20 && y <= selected.getY()*20 + 20 && y >=  selected.getY()*20) {
				dir = SwordAndShieldGame.Dir.UP;
			}
			else if(x <= selected.getX()*20 + 40 && x >=  selected.getX()*20 + 20 && y <= selected.getY()*20 + 60 && y >=  selected.getY()*20 + 40) {
				dir = SwordAndShieldGame.Dir.DOWN;
			}
			
			//Move in desired direction
			try {
				if(dir == null) {
					System.out.println("Failed");
					pieceSelected = false;
					return;
				}
				game.move(selected.getId().getType(), dir);
				pieceSelected = false;
				gui.updateCanvas(game.getBoard(), game.getBag1().getBoard(), game.getBag2().getBoard(), game.getCemetery().getBoard());
			} catch (InvalidMove e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

	}

	/**
	 * Executes pass button logic. Increments the turn phase.
	 * 
	 * @author Tim Gastrell
	 *
	 */
	class PassListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			phase++;
		}

	}

	/**
	 * Execute surrender button logic.
	 * Displays a new JOptionPane with the winning player.
	 * 
	 * @author Tim Gastrell
	 *
	 */
	class SurrenderListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int input = 0;
			if(turn == 1) {
				input = JOptionPane.showOptionDialog(gui, "Player 2 wins", "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );
			}
			else if(turn == 2) {
				input = JOptionPane.showOptionDialog(gui, "Player 1 wins", "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );
			}
			else {
				return;
			}
			
			if(input == JOptionPane.OK_OPTION || input == JOptionPane.CANCEL_OPTION) {
				System.exit(0);
			}
		}

	}
	
	/**
	 * Executes (non functional) undo button logic.
	 * 
	 * @author Tim Gastrell
	 *
	 */
	class UndoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				game.doUndo();
				gui.updateCanvas(game.getBoard(), game.getBag1().getBoard(), game.getBag2().getBoard(), game.getCemetery().getBoard());
			} catch (InvalidMove e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
	}

}



