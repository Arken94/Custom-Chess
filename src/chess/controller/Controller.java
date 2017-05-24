package chess.controller;

import java.awt.event.WindowEvent;
import javax.swing.*;
import chess.models.Command;
import chess.models.Models;
import chess.resources.*;
import chess.views.Views;


/**
 * Controller - This class is in charge of all user interaction with the GUI. It handles changes to the Models
 * including the state of the game, the score, etc.. It is also in charge of relaying back the changes that 
 * were made in the models back to the users through the views. In other words, this class changes the models based
 * on a user's request then reflects those changes back to the user through the views. 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Controller {

	
	public Views views;
	private boolean hasClickedOnce = false;
	int[] coordinates; 
	
		
	
	/**
	 * Handle all click events by User. Completely and perfectly abstract away all
	 * controller logic from the views. The views has no idea what is going on 
	 * behind the scenes. All it knows to do is call this function whenever a button in 
	 * its JFrame is clicked! 
	 * 
	 * MVC - perfected
	 *
	 * @param coordinates - coordinates that was clicked by user
	 * @return None
	 */
	public void handlePieceClick(int[] coordinates){
		
		// If this is the first time the user is clicking it means that 
		// he is clicking the piece he wants to move, so we must store that
		if (!hasClickedOnce){
			
			// You are NOT allowed to select a tile that is empty
			// and DO NOT allow moving of opposite teams player
			ChessPiece pieceAtPosition = Models.getPieceAtPosition(coordinates);
			if(pieceAtPosition == null) return;
			if(pieceAtPosition.player != Models.turn){ 
				return; 
			}
			
			// process successfull selection
			this.coordinates = coordinates;
			hasClickedOnce = true;
			this.views.selectColorOfTile(coordinates[0], coordinates[1]); // change view to show selection
			
		} 
		
		// The user is now choosing where he wants to move his piece to. So We
		// Will attempt to move this piece to the requested location on the second click
		else {
			
			hasClickedOnce = false; // set back to false
			ChessPiece piece = Models.getPieceAtPosition(this.coordinates);
			String result = piece.attemptMove(coordinates);
			
			if(result.equals("success")){
				
				//get current player
				Player currentPlayer = Models.turn;
				
				// if attempted move was successful, than move piece on chess board
				// and switch the turn to the next player
				this.views.movePiece(this.coordinates, coordinates);
				Models.switchTurn(); // models switch
				views.switchPlayerColor(); // views switch

				// check for check and checkmate, and do proper visuals for the user
				King opposingKing = (currentPlayer == Player.PLAYER_NORTH) ? Models.kingSouth : Models.kingNorth;
				if(opposingKing.isChecked){
					
					if(opposingKing.isCheckMate){
						this.views.showCheckMateDialog(currentPlayer);
						Models.incrementScore(currentPlayer);
						newGame();
						
					} else{
						this.views.showCheckDialog();
					}
				}
				
				
				
			} else {
				
				// if attempted move failed than give the same user another chance
				hasClickedOnce = false;
				this.views.removeSelectColorOfTile(this.coordinates[0], this.coordinates[1]);
				this.views.showIllegalMoveDialog();
				
			}
			
			
			
			
		}
		
	}
	
	
	
	/**
	 * 
	 * This is the handler that is called upon clicking the Undo Button. I Chose to implement an 
	 * advanced Command retrieval technique where I created "command" objects and pushed them on to 
	 * the command stack. Whenever I need to reverse a command, I simply execute the command and it reverses
	 * whatever operation was initially done. 
	 * 
	 * My undo implementation allows for UNLIMITED undos in a very efficient manner. 
	 * 
	 * 
	 * @param None
	 * @return None
	 */
	public void undo(){
		
	   if(Models.commands.isEmpty()) return;
		
		// Change the models to reflect the state of the game
       Command executeMove = Models.commands.pop();
       
       // Change the views
       this.views.movePiece(executeMove.piece.currentPosition, executeMove.backToCoordinates);
       
       // change the models
       executeMove.piece.move(executeMove.backToCoordinates, false);
       
       // place back killed piece if it was there!
       if(executeMove.killedPiece != null){
    	   ChessPiece addBack = executeMove.killedPiece;
    	   Models.stateRepresentation[addBack.currentPosition[0]][addBack.currentPosition[1]] = addBack;
    	   this.views.addPiece(addBack);
    	   
       }
       
       // switch players
       Models.switchTurn();
       this.views.switchPlayerColor();
	
	}
	
	
	
	/**
	 * 
	 * The handler that is called when the "restart" button is clicked. It simply tries to get 
	 * Confirmation from both users, than calls a new game. 
	 * 
	 * @param None
	 * @return None
	 */
	public void restart(){
		
		// make sure both players said yes
		String ans1 = JOptionPane.showInputDialog(Models.player1Name + " do you agree to restart the game?");
        String ans2 = JOptionPane.showInputDialog(Models.player2Name + " do you agree to restart the game?");
    
        if(ans1.equals("yes") && ans2.equals("yes")) newGame();

		
	}
	
	
	/**
	 * The handler that is called when the "forfeit" button is clicked. It simply tries to get 
	 * Confirmation the user forfeiting, increments the score for the opposing team,  than calls a new game. 
	 * 
	 * @param None
	 * @return None
	 */
	public void forfeit(){
		String name = (Models.turn == Player.PLAYER_NORTH) ? Models.player1Name : Models.player2Name;
		// make sure both players said yes
		String ans1 = JOptionPane.showInputDialog(name + " do you agree to forfeit the game?");
        if(ans1.equals("yes")){ 
        	Models.incrementScore((Models.turn == Player.PLAYER_NORTH) ? Player.PLAYER_SOUTH : Player.PLAYER_NORTH);
        	newGame();
        }

		
	}
	
	
	
	
	/**
	 * 
	 * Create a new game, but maintain all meta data such as score, etc..
	 * Simply initializes a new Model, throws away the current view, and creates a new one.
	 * The MVC structure made it this easy!s
	 * 
	 * @param None
	 * @return None
	 * 
	 */
	public void newGame(){
		
		// Create a new state representation for a new game!
		Models.initializeState();
		Models.turn = Player.PLAYER_NORTH;
		
		// generate a new frame and create a new view as a whole!
		this.views.frame.dispatchEvent(new WindowEvent(this.views.frame, WindowEvent.WINDOW_CLOSING));
		this.views = new Views(this, false);
		
	}
	
	
	
	
	

}
