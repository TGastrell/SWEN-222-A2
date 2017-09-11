import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Implementation of JPanel designed to display Sword and Shield game boards.
 * 
 * @author Tim Gastrell
 *
 */
public class GamePanel extends JPanel {
	
	private Board board;
	
	public GamePanel() {
		this.repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Paint board
		paintObject(g);

	}
	
	/**
	 * Paints the specified part of the game
	 * 
	 * @param g Graphics pane
	 */
	private void paintObject(Graphics g) {
		if(board == null) {
			System.out.println("ERROR - Object to paint does not exist");
			return;
		}
		int x = 0;
		int y = 0;
		for(int i = 0; i < board.getWidth(); i++) {
			for(int j = 0; j < board.getHeight(); j++) {
				
				g.setColor(getTypeColor(board.getSquare(i, j)));
				g.drawRect(x, y, 20, 20);
				g.fillRect(x, y, 20, 20);

				y += 20;
			}
			x += 20;
			y = 0;
		}
		
	}
	
	/**
	 * Draws a rectangle around the piece at the specifed location
	 * 
	 * @param x xPos of Piece
	 * @param y yPos of Piece
	 */
	public void highlight(int x, int y) {
		this.getGraphics().setColor(Color.RED);
		this.getGraphics().drawRect(x, y, 60, 60);
	}
	
	/**
	 * Returns the appropriate color for the passed Grid Square
	 * 
	 * @param Grid Square
	 * @return Corresponding Color
	 */
	private Color getTypeColor(GridSquare g) {
		
		GridSquare.Type type = g.getType();
		
		Color color = Color.WHITE;

		switch(type) {
		case EMPTY :
			color = Color.LIGHT_GRAY;
			break;
		case VERTICAL_SWORD :
			color = Color.RED;
			break;
		case HORIZONTAL_SWORD :
			color = Color.RED;
			break;
		case SHIELD :
			color = Color.BLUE;
			break;
		case CREATION_TILE :
			color = Color.GRAY;
			break;
		case OUT_OF_BOUNDS :
			color = Color.DARK_GRAY;
			break;
		case PLAYER_ONE_TILE :
			color = Color.GREEN;
			break;
		case PLAYER_TWO_TILE :
			color = Color.YELLOW;
			break;
		default:
			if(Character.isUpperCase(type.toString().charAt(0))){
				color = Color.YELLOW;
			}
			else {
				color = Color.GREEN;
			}
			break;	
		}
		
		return color;
	}
	
	/**
	 * Method for updating the board.
	 * 
	 * @param board New Board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
}
