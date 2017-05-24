package chess.resources;

import chess.models.*;

/**
 * ChessPiece - an abstract class that all chess pieces inherit from. This class implements the basic move functionality
 * for all subclasses, however requires each subclass to implement their own "attemptMove()" method which 
 * enforces piece specific movement rules.
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public abstract class ChessPiece {
	
	
	public Player player;  // The player that owns this chess piece. 
	public int[] currentPosition;
	public boolean addCommand = true;
	
	
	
	public ChessPiece(Player player, int[] currentPosition){
		this.player = player;
		this.currentPosition = currentPosition;
	}
	
	
	/**
	 * 
	 * This method is responsible for modifying the "State" of the game. This method should only
	 * ever be called after "attemptMove()" is called, since it first checks to see if the requested state
	 * change is allowed by the rules governing that piece. This method is technically a wrapper for
	 * State.changePiecePosition(), however it is important to create this wrapper and make it a method
	 * in the "ChessPiece" class in order to decouple chess pieces from the State of the game.  
	 * 
	 * This method also checks if the opposing king has been placed under check or check mate after each
	 * move made. If it has, we mark the isChecked attribute of the king to true. 
	 * 
	 * this method is also responsible for adding the "move" to the command stack, in order 
	 * to later allow for undos!
	 * 
	 * 
	 * @param moveToPosition - the position to which we are moving the piece
	 */
	
	public void move(int[] moveToPosition, boolean addToCommand){
		
		if(!addToCommand){
			this.addCommand = false;
		}
		move(moveToPosition);
		
	
	}

	
	public void move(int[] moveToPosition){
		
		ChessPiece occupyingPiece = Models.getPieceAtPosition(moveToPosition);
		
		//Modify the state of the game with the new move.
		String result = Models.changePiecePosition(currentPosition, moveToPosition);  
		if(result == "success" || result == "killed"){	
			
			if(this.addCommand){
				// add this move to the command stack
				Command newMove = new Command(this.player, this, this.currentPosition );
				
				// re place on board the piece that was killed by this piece
				if(result == "killed"){
					newMove.killedPiece = occupyingPiece;
				}
				
				Models.commands.push(newMove);
			} this.addCommand = true;

			// change the current position of this piece
			this.currentPosition = moveToPosition;
		
			
			//we check for check / check mate (no pun intended)
			King king = (this.player == Player.PLAYER_NORTH) ? Models.kingSouth : Models.kingNorth;
			
			if(king == null) return;
			
			boolean isKingChecked = King.isChecked(king, king.currentPosition);
			
			if(isKingChecked){
				// mark the checked flag
				king.isChecked = true;
				System.out.println("The king has been checked, You must move the king!");
				// Check for check mate 
				if( !king.isKingAbleToMove() ){
					System.out.println("The king has been checkmated, the game is over!");
					king.isCheckMate = true;
					Models.isRunning = false; // set game state to non running the game has been ended
					
				}	
			} else{
				king.isChecked = false;
				System.out.println("The king has not been placed under check!");
			}
			
		}	
	
	}
	

	
	
	/**
	 * 
	 * Checks whether a position on the board is occupied by a piece of the same player
	 * that is trying to move. If this is the case, then we mark this position as an
	 * invalid position for that player.   
	 * 
	 * 
	 * @param moveToPosition - the position to which we are moving the piece
	 * 		  currentPosition - the current position of the piece
	 *
	 *@return boolean - indicated validity of the move
	 */
	public static boolean isValidMovePosition(int[] currentPosition, int[] moveToPosition){
		

		// Make sure that you cannot move beyond the bounds of the board!
		if( moveToPosition[0] >= 8 || moveToPosition[0] < 0 ){
			return false;
		}
		
		
		// Make sure that you cannot move beyond the bounds of the board!
		if( moveToPosition[1] >= 8 || moveToPosition[1] < 0 ){
			return false;
		}
		
		//make sure you are not moving to the same location
		if(currentPosition[0] == moveToPosition[0] && currentPosition[1] == moveToPosition[1]){
			return false;
		}
		
		
		ChessPiece movePiece = Models.getPieceAtPosition(currentPosition); // the piece we are moving
		boolean isMovePositionOccupied = Models.isPositionOccupied(moveToPosition);
		
		// make sure the move to location is not occupied by own player
		if(isMovePositionOccupied){
			ChessPiece occupyingPiece = Models.getPieceAtPosition(moveToPosition);
			// Attempting to move to a position that is occupied by one of YOUR own pieces.
			if(occupyingPiece.player == movePiece.player) return false;	
		}
		
		
		
		return true;
		
	}
	
	
	/**
	 * 
	 * This is the first method called when a piece is requested to be moved. It checks if the requested 
	 * move is allowed. If it is we actually move the piece by calling "move()" - which changes the state of the game. 
	 * This method should be overridden by every subclass of "ChessPiece".
	 * 
	 * @param moveToPosition - the position to which we are REQUESTING to move the piece
	 * @return String specifying the result of this call, "success" or "error message"
	 */
	public abstract String attemptMove(int[] moveToPosition);
		
		
	

}
