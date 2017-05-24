package chess.views;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import chess.controller.Controller;
import chess.models.Models;
import chess.resources.*;
import chess.resources.Player;

/**
 * 
 * Views - this class in completely in charge of displaying all the visuals to the user. 
 * Any change that happens to the GUI must go through this class. No matter what. User interactions
 * are first caught by the ActionListeners in this class, than directly delegated off to the 
 * CONTROLLERS, since that is the proper MVC implementation.
 * 
 * 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Views {
	
	private Controller controller; 
	public JFrame frame;
	private JPanel statsBoard;
	public JPanel chessBoard;
    private JButton[][] tiles;
    private int height;
    private int width;
    private boolean buttonClicked = false;
    private JLabel player1;
    private JLabel player2;
  
    

    
    
    /**
     * 
     * Constructor  - sets up the view for the end user to visibly see. It adds all the chess pieces and
     * styles everything in a visually pleasing manner. Many of the tasks are delegated to different functions
     * 
     * @param Controller - the controller that will be taking care of this view
     * @param boolean - whether the view should ask for the players names
     */
        
	public Views(Controller controller, boolean askForNames) {
		
		this.controller = controller; // set controller
		this.controller.views = this; // Controller needs access to this view as well
		
		this.height = Models.board.getHeight();
		this.width = Models.board.getWidth();
		
	    this.frame = new JFrame();  
	   
	    // only ask for names if it is the first time building a view.Completley new games
	    if(askForNames)
	    	this.askForPlayerNames();
	    
	    BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS); // top to bottom
	    frame.setLayout(boxLayout);
	    
	    //create side panel
	    statsBoard = new JPanel();
	    statsBoard.setPreferredSize(new Dimension(300, 650));
	    statsBoard.setBackground(Color.decode("#FFF7F0"));
	     
	    // Add player names to the side bar
	    setUpPlayerView();
	    
	    // Add the reset, forfeit, and end game buttons
	    setUpStatButtons();

	    // Build the chess board from scratch
	    setUpChessBoard();
	  
	    frame.add(statsBoard);
	    
	    //setup frame
	   
	    frame.setResizable(true);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	   
		
	}
	
	
	
	/**
	 * 
	 * Adds all the chess pieces to the visual screen, and places them in the proper manner. 
	 * It sets the height, width, and other stylistic beauties :)
	 * 
	 * @param None
	 * @return None
	 */
	public void setUpChessBoard(){
		
		// create chess board portion
	    chessBoard = new JPanel();
	    chessBoard.setLayout(new GridLayout(8, 8));
	    chessBoard.setSize(650, 650);  
		this.tiles = new JButton[height][width];

	
	    // generate chess board
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	        	
	        	// set the image and create a new JButton
	        	String imageUrl = getImageForLocation(i, j);
	        	

        		ImageIcon ic = new ImageIcon(getImageForLocation(i, j)); // get specific image for each location
	            tiles[i][j] = new JButton(ic);
	            tiles[i][j].setPreferredSize(new Dimension(70, 70)); // set the buttons size
	        	
	       
	            addJButtonActionListener(tiles[i][j]);
	            
	            // Remove background opaqueness
	            tiles[i][j].setOpaque(false);
	            tiles[i][j].setContentAreaFilled(false);
	            tiles[i][j].setBorderPainted(false);
	           
	            // Add the buttons to the board in a stylistic manner!
	            JPanel jPanel = new JPanel();
	            jPanel.add(tiles[i][j]);
	            
	            if( (i + j) % 2 == 1){ // set alternating color tiles
	            	jPanel.setBackground(Color.decode("#D4A67F"));
	            } else{
	            	jPanel.setBackground(Color.decode("#FFF7F0"));
	            }
	            this.chessBoard.add(jPanel);
	        
	        }
	    }
	    // add elements to frame
	    frame.add(chessBoard);

	    
	}
	
	
	
	/**
	 * 
	 * Asks for the unique names of the players, than stores them in the Models reserved
	 * variables. 
	 * 
	 * @param None
	 * @return None
	 */
	public void askForPlayerNames(){
		
		Models.player1Name = JOptionPane.showInputDialog("Enter name for the first player: ");
        Models.player2Name = JOptionPane.showInputDialog("Enter name for the second player: ");	
	}
	

	
	
	
	
	/**
	 * 
	 * This function is in charge of displaying and styling all the buttons in the side panel. 
	 * It delegates the click handlers to the Controllers, and doesnt worry about anything here. 
	 * 
	 * 
	 * @param None
	 * @return None
	 * 
	 */
	public void setUpStatButtons(){
		
		JButton reset = new JButton("<html>Restart<br></html>");
		JButton forfeit = new JButton("<html>Forfeit<br></html>");
		JButton undo = new JButton("<html>Undo<br></html>");
				
		
		Font font = new Font("century gothic", Font.BOLD, 28);
		
		reset.setFont(font);
		forfeit.setFont(font);
		undo.setFont(font);
		
		// Delegate all controls to the controller
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.restart();	
			}		
		});
		
		//delegate all controls to the controller
		forfeit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.forfeit();	
			}		
		});	
		
		
		//delegate all controls to the controller
		undo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo();	
			}		
		});	
		
		
		this.statsBoard.add(reset);
		this.statsBoard.add(forfeit);
		this.statsBoard.add(undo);
		
	}
	
	
	
	
	
	/**
	 * 
	 * Adds the players names and scores to the side panel. Styles them in an asthetically pleasing
	 * manner
	 * 
	 * 
	 * @param None
	 * @return None
	 */
	public void setUpPlayerView(){
		
		player1 = new JLabel(Models.player1Name + ": "+ Models.playerNorthScore +" wins");
		player2 = new JLabel(Models.player2Name + ": "+ Models.playerSouthScore +" wins");
		
		Font font = new Font("century gothic", Font.BOLD, 26);
		
		player1.setFont(font);
		player2.setFont(font);
		player1.setForeground(Color.blue);
		player2.setForeground(Color.lightGray);
		
		this.statsBoard.add(player1);
		this.statsBoard.add(player2);
		
	}
	
	
	
	
	/**
	 * 
	 * Simply switches the color of each player's color label, in order to signify whos turn it is.
	 * 
	 * 
	 * @param None
	 * @return None
	 */
	public void switchPlayerColor(){
		if(player1.getForeground() == Color.lightGray){
			player1.setForeground(Color.blue);
			player2.setForeground(Color.lightGray);	
		} else{
			player2.setForeground(Color.blue);
			player1.setForeground(Color.lightGray);	
		}
		 
	}
	
	
	
	/**
	 *
	 * Gets the proper chess piece image for each location on the board
	 * 
	 * @param row - row of location
	 * @param col - column of location
	 * @return a string representing the image that should appear at that location
	 */
	public String getImageForLocation(int row, int col){
		
		// return white pieces
		if(row == 1 && (col > 0 && col < 7)) return "images/Chess_plt60.png";
		if(row == 1 && col == 0 ) return "images/frog.png";
		if(row == 1 && col == 7 ) return "images/snail.png";
		if(row == 0 && (col == 7 || col == 0)) return "images/Chess_rlt60.png";
		if(row == 0 && (col == 6 || col == 1)) return "images/Chess_nlt60.png";
		if(row == 0 && (col == 5 || col == 2)) return "images/Chess_blt60.png";
		if(row == 0 && col == 4) return "images/Chess_klt60.png";
		if(row == 0 && col == 3) return "images/Chess_qlt60.png";
		
		
		// return black pieces
		if(row == 6 && (col > 0 && col < 7)) return "images/Chess_pdt60.png";
		if(row == 6 && col == 0 ) return "images/frog.png";
		if(row == 6 && col == 7 ) return "images/snail.png";
		if(row == 7 && (col == 7 || col == 0)) return "images/Chess_rdt60.png";
		if(row == 7 && (col == 6 || col == 1)) return "images/Chess_ndt60.png";
		if(row == 7 && (col == 5 || col == 2)) return "images/Chess_bdt60.png";
		if(row == 7 && col == 4) return "images/Chess_kdt60.png";
		if(row == 7 && col == 3) return "images/Chess_qdt60.png";
		
		return "NONE";
				
		
	}
	
	
	
	/**
	 * 
	 * Gets the proper chess piece image for each type of chess piece. 
	 * 
	 * @param row - row of location
	 * @param col - column of location
	 * @return a string representing the image that should appear at that location
	 */
	public String getImageForType(ChessPiece piece){
		
		// return white pieces
		if(piece.player == Player.PLAYER_NORTH){
			
			if(piece instanceof Frog ) return "images/frog.png";
			if(piece instanceof Snail ) return "images/snail.png";
			if(piece instanceof Pawn ) return "images/Chess_plt60.png";
			if(piece instanceof Rook ) return "images/Chess_rlt60.png";
			if(piece instanceof Knight ) return "images/Chess_nlt60.png";
			if(piece instanceof Bishop ) return "images/Chess_blt60.png";
			if(piece instanceof King ) return "images/Chess_klt60.png";
			if(piece instanceof Queen ) return "images/Chess_qlt60.png";
		}
		
		
		// return black pieces
		else{
			if(piece instanceof Frog ) return "images/frog.png";
			if(piece instanceof Snail ) return "images/snail.png";
			if(piece instanceof Pawn )  return "images/Chess_pdt60.png";
			if(piece instanceof Rook ) return "images/Chess_rdt60.png";
			if(piece instanceof Knight ) return "images/Chess_ndt60.png";
			if(piece instanceof Bishop ) return "images/Chess_bdt60.png";
			if(piece instanceof King ) return "images/Chess_kdt60.png";
			if(piece instanceof Queen ) return "images/Chess_qdt60.png";
		}
		return "NONE";
		
				
		
	}
	
	
	
	
	
	/**
	 * This just adds the standard JButton ActionListener to the JButtons on the screen. These
	 * ActionListeners will delegate the necessary actions to the CONTROLLERS code in order to 
	 * maintain the integrity of this perfect MVC system. 
	 * 
	 * 
	 * @param jbutton - jButton that we want to add the standard jButton Action Listener to.
	 */
	public void addJButtonActionListener(JButton jbutton){
		
		jbutton.addActionListener(new ActionListener() { 
			
			  @Override
			  public void actionPerformed(ActionEvent e) { 
				  
				 
				  
				  // get button who requested the click
				  JButton source = (JButton) e.getSource();
				  int[] coordinates = getCoordinatesFromButton(source);
				    
				  
				  /* Delegate all logical controller work to the controller subsystem */
				  controller.handlePieceClick(coordinates);
				  
				  
			  }
			});
		
	}
	
	
	
	/**
	 * retrieves the coordinates of a specific JButton in the 2d array of 
	 * JButtons that represent the visual chessboard
	 * 
	 * @param getButton - button to retrieve coordinate of 
	 * @return int[2] the coordinates of the button we are searching for. 
	 */
	public int[] getCoordinatesFromButton(JButton getButton){
		
			for(int i = 0; i < tiles.length; i++){
				for(int j = 0; j < tiles[0].length; j++){
					if (tiles[i][j] == getButton) {
						int[] coordinate = {i , j};
						return coordinate;
					}
				}
			}
		
			return null;
	}
	
	
	
	/**
	 * 
	 * Change a tile to the "selected color"
	 * 
	 * @param row
	 * @param col
	 */
	public void selectColorOfTile(int row, int col){
		 JPanel panelHolder = (JPanel) tiles[row][col].getParent();
		 panelHolder.setBackground(Color.decode("#90D4AC"));	
	}
	
	
	/**
	 * 
	 * Remove the selected color from the tile
	 * 
	 * @param row
	 * @param col
	 */
	public void removeSelectColorOfTile(int row, int col){
		// Set the color of the tile back to its original hue :)
		JPanel panelHolder = (JPanel) tiles[row][col].getParent();
		 if( (row + col) % 2 == 1){ // set alternating color tiles
	    	panelHolder.setBackground(Color.decode("#D4A67F"));
	    } else{
	    	panelHolder.setBackground(Color.decode("#FFF7F0"));
	    }	
	}
	
	
	
	/**
	 * 
	 * Visually Move a chess piece. This is only called after the chess piece has been
	 * successfully moved in the Models state representation. 
	 * 
	 * @param from - moving from
	 * @param to - moving to
	 */
	
	public void movePiece(int[] from, int[] to){
		
		int fromX = from[0];
		int fromY = from[1];
		int toX = to[0];
		int toY = to[1];
			
		// change the piece's image to the users - reflect what is going on in the models to the users, through the controller.
		this.tiles[toX][toY].setIcon(tiles[fromX][fromY].getIcon()); 
		this.tiles[fromX][fromY].setIcon(new ImageIcon("NONE"));
		
		// Set the color of the tile back to its original hue :)
		JPanel panelHolder = (JPanel) tiles[fromX][fromY].getParent();
		 if( (fromX + fromY) % 2 == 1){ // set alternating color tiles
        	panelHolder.setBackground(Color.decode("#D4A67F"));
        } else{
        	panelHolder.setBackground(Color.decode("#FFF7F0"));
        }
		
		
	}
	
	/**
	 * 
	 * 
	 * Adds a ChessPiece to the board - simply sets the visual image to that chess piece type. 
	 * 
	 * @param piece
	 * @return None
	 */
	public void addPiece(ChessPiece piece){
		int[] location = piece.currentPosition;
		int locX = location[0];
		int locY = location[1];
		
		// change the piece's image to the users - reflect what is going on in the models to the users, through the controller.
		this.tiles[locX][locY].setIcon(new ImageIcon(getImageForType(piece))); 
		
	}
	
	
	
	/**
	 * 
	 * 
	 * These are a set of utility functions that are handy to call whenever I need to show a dialog to the user
	 * It makes the code more compact and easier to read!
	 * 
	 * 
	 */
	
	
	public void showCheckDialog(){	
		 JOptionPane.showMessageDialog(null, "You have been checked", "Check!", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	public void showCheckMateDialog(Player winner){
		String name = (winner == Player.PLAYER_NORTH) ? Models.player1Name : Models.player2Name;
		JOptionPane.showMessageDialog(null, "You have been checked mated. The game is over. " + name + " has won this round" , "CheckMate!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	public void showIllegalMoveDialog(){
		JOptionPane.showMessageDialog(null, "Warning! Illegal Move. Try Again. " , "Warning", JOptionPane.INFORMATION_MESSAGE);
	}
	
	

	


}
