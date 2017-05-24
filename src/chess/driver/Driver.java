package chess.driver;



import chess.controller.Controller;
import chess.models.*;
import chess.resources.*;
import chess.views.Views;



/**
 * GUI -- Class this is used to host the main method, that will be in charge 
 * of starting the main loop of the chess game. For now, this class will remain 
 * unimplemented until next week's assignment requires us to complete this portion. 
 * 
 * SOURCES: JavaFX Tutorial (I'm pretty new to it so I need to loop up how to make a table, etc..)
 * 
 * http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/table-view.htm#CJAGAAEE
 * https://docs.oracle.com/javafx/2/api/javafx/scene/image/Image.html
 * http://stackoverflow.com/questions/35709762/javafx-resizable
 * http://stackoverflow.com/questions/11934045/how-to-add-empty-row-in-gridpane-in-javafx
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */

public class Driver  {

	/**
	 * This method is in charge of the view aspect of the chess game. 
	 * It will handle all the things that will be visible to the end user.
	 * 
	 * 
	 * I have completely and perfectly separated each aspect of this application into 
	 * 
	 * MODELS, VIEWS, and CONTROLLER
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
 
    	
    	/* MVC  - perfected */
    	
    	Models.initializeState(); // think of this like a DB - we can query it or ask to modify its data :)
    	
    	Controller controller = new Controller();
    	
    	new Views(controller, true);
    	
    		
    }
    
    
    
    
    
}
