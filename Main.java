/**
 * Main class to execute Sword and Shield game
 * 
 * @author Tim Gastrell
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		GraphicalUserInterface view = new GraphicalUserInterface();
		
		SwordAndShieldGame model = new SwordAndShieldGame(30, 30);
		
		view.setVisible(true);
		view.pack();
		
		GuiController controller = new GuiController(model, view);
		
	}
	
}
