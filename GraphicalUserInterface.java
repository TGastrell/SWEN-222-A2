import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 * Main part of View component for Sword and Shield game
 * 
 * @author Tim Gastrell
 *
 */
public class GraphicalUserInterface extends JFrame {	
    
    /**
     * Main Jpanel Container
     */
	private JPanel mainPanel;
	
	/**
	 * Board Panel
	 */
	private GamePanel board;
	
	/**
	 * Bag 1 Panel
	 */
	private GamePanel bag1;
	
	/**
	 * Bag 2 Panel
	 */
	private GamePanel bag2;
	
	/**
	 * Cemetery Panel
	 */
	private GamePanel cemetery;
	
	/*
	 * Button to undo the most recent action
	 */
	private JButton undoButton;
	
	/**
	 * Button to pass the current stage of the turn
	 */
	private JButton passButton;
	
	/**
	 * Button to surrender victory to the opposing player
	 */
	private JButton surrenderButton;

	/**
	 * Constructs a new Graphical User Interface
	 */
	public GraphicalUserInterface() {
				
		mainPanel = new JPanel();	
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setMaximumSize(new Dimension(1200, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200,675);
		
		//Initialise toolbar
		addToolBar(mainPanel);
		
		//Initialise panels
		initialiseGamePanels();
		
		displayStartMenu();
	}
	
	/**
	 * Displays JOptionPane for start menu
	 */
	public void displayStartMenu() {
		this.add(mainPanel);
		Object[] options = {"Start", "Info", "Quit"};
		Object message = "Sword and Shield Game";
		int n  = JOptionPane.showOptionDialog(this, message, "New Game",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[2]);
		if(n == 1) {
			JOptionPane.showMessageDialog(this, "Sword and Shield game"
					+ " created by Tim Gastrell");
			displayStartMenu();
		}
		else if(n == 2) {
			System.exit(0);
		}
	}
	
	/**
	 * Initialises mouse listener for board panel
	 * 
	 * @param boardListener Mouse Listener for board 
	 */
	public void initialiseBoardListeners(MouseListener boardListener) {
		board.addMouseListener(boardListener);
	}
	
	/**
	 * Initialises mouse listeners for bag panels
	 * 
	 * @param bagListener Mouse listener for bags
	 * @param player identification for bags
	 */
	public void initialiseBagListener(MouseListener bagListener, int player) {
		if(player == 1) {
			bag1.addMouseListener(bagListener);
		}
		else {
			bag2.addMouseListener(bagListener);
		}
	}
	
	/**
	 * Initialises the pass buttons functionality
	 * 
	 * @param listenForPassButton Actionlistener implementing pass functionality
	 */
	public void addPassListner(ActionListener listenForPassButton) {
		passButton.addActionListener(listenForPassButton);
	}
	
	/**
	 * Initialises the undo buttons functionality
	 * 
	 * @param listenForUndoButton Actionlistener implementing undo functionality
	 */
	public void addUndoListner(ActionListener listenForUndoButton) {
		undoButton.addActionListener(listenForUndoButton);
	}
	
	/**
	 * Initialises the surrender buttons functionality
	 * 
	 * @param listenForSurrenderButton Actionlistener implementing surrender functionality
	 */
	public void addSurrenderListner(ActionListener listenForSurrenderButton) {
		surrenderButton.addActionListener(listenForSurrenderButton);
	}
	
	/**
	 * Initialises the Board, Bags and cemetery JPanels
	 */
	private void initialiseGamePanels() {
		
		JPanel gamePanel = new JPanel(new BorderLayout(4, 4));
		JPanel bagPanel = new JPanel(new GridLayout(2, 1, 4, 4));
		
		bag1 = new GamePanel();
		bag1.setPreferredSize(new Dimension(300, 300));
		bag1.setMaximumSize(new Dimension(300, 300));
		bagPanel.add(bag1, 0);
		
		bag2 = new GamePanel();
		bag2.setPreferredSize(new Dimension(300, 300));
		bag2.setMaximumSize(new Dimension(300, 300));
		bagPanel.add(bag2, 1);
		
		gamePanel.add(bagPanel, BorderLayout.WEST);
		
		board = new GamePanel();
		board.setPreferredSize(new Dimension(600, 600));
		board.setMaximumSize(new Dimension(600, 600));
		gamePanel.add(board, BorderLayout.CENTER);
		
		cemetery = new GamePanel();
		cemetery.setPreferredSize(new Dimension(300, 600));
		cemetery.setMaximumSize(new Dimension(300, 600));
		gamePanel.add(cemetery, BorderLayout.EAST);
		
		mainPanel.add(gamePanel, BorderLayout.CENTER);
	}
	
	/**
	 * Calls the highlight method in the Board panel
	 * 
	 * @param x xPos of piece to highlight
	 * @param y yPos of piece to highlight
	 */
	public void highlightPiece(int x, int y) {
		board.highlight(x, y);
	}
	
	/**
	 * Initalises tool bar with buttons and adds to main JPanel
	 * 
	 * @param mainPanel main JPanel
	 */
	private void addToolBar(JPanel mainPanel) {
		
		JToolBar toolBar = new JToolBar();
		
		addButtons(toolBar);
		
		toolBar.setFloatable(false);

		mainPanel.add(toolBar, BorderLayout.SOUTH);
	}
	
	/**
	 * Adds buttons and layout to JToolBar
	 * 
	 * @param toolBar 
	 */
	private void addButtons(JToolBar toolBar) {
		
		undoButton = new JButton("Undo");		
		toolBar.add(undoButton);
		toolBar.addSeparator();
		
		passButton = new JButton("Pass");		
		toolBar.add(passButton);
		toolBar.addSeparator();

		
		surrenderButton = new JButton("Surrender");		
		toolBar.add(surrenderButton);
		toolBar.addSeparator();
	}
	
	/**
	 * Updates the canvas
	 * 
	 * @param board new board
	 * @param bag1 new bag1
	 * @param bag2new bag2
	 * @param cemetery new cemeterys
	 */
	public void updateCanvas(Board newBoard, Board newBag1, Board newBag2, Board newCemetery){
		this.board.setBoard(newBoard);
		this.bag1.setBoard(newBag1);
		this.bag2.setBoard(newBag2);
		this.cemetery.setBoard(newCemetery);
		repaintAll();
	}
	
	private void repaintAll() {
		board.repaint();
		bag1.repaint();
		bag2.repaint();
		cemetery.repaint();
	}

	
}

