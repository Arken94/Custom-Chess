package chess.resources;

import chess.models.*;

/**
 * Pawn - Class that handles all pawn movement logic. 
 * TODO: handle 2 steps on first move
 * 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Pawn extends ChessPiece {


	boolean isFirstMove = true; 

	
	public Pawn(Player player, int[] currentPosition) {
		super(player, currentPosition);
	}
	
	
	@Override
	public String attemptMove(int[] moveToPosition) { // check if pawn can move to requested position
		
		int isLegalAttemptedMove = this.isMoveAllowed(this.currentPosition, moveToPosition);
		if(isLegalAttemptedMove == 1){
			this.move(moveToPosition);
			return "success";
		}
		else{
			String message = "You have attempted to illegally move this Pawn.";
			System.out.println(message);
			return(message);
			
		}
	
	}
	
	/**
	 * Checks whether this Pawn object is allowed to move to the requested location.
	 * allows for all straight moves and no backwards motions. Allows for one step diagonals when killing.
	 * Will return an error code if there is any obstructing piece on the path to the
	 * requested position (including pieces that belong to the current player). 
	 * 
	 * @param currentPosition - current position of piece we are moving
	 * @param moveToPosition - and integer array of size two which specifies the request move position
	 * 
	 * @return returns an integer indicating whether the requested move is allowed or not. 1 means it is allowed. 
	 * 		   2 means that the requested move is not allowed. 
	 */
	public static int isMoveAllowed(int[] currentPosition, int[] moveToPosition){
		
		int yMove = moveToPosition[0]; // y coordinate of requested move position
		int xMove = moveToPosition[1]; // x coordinate of requested move position
		int yCurrent = currentPosition[0]; // y coordinate of current position
		int xCurrent = currentPosition[1]; // x coordinate of current position
		
		// Get the current player
		ChessPiece currentPawn = Models.getPieceAtPosition(currentPosition);
		Player player = currentPawn.player;
		
		boolean isMovePositionOccupied = Models.isPositionOccupied(moveToPosition);
		int isLegalAttemptedMove = 2;
		
		
		boolean isValid = isValidMovePosition(currentPosition, moveToPosition);
		if(!isValid) return 2;
		
		/* Since Pawns can only move forward and not backward we must check to see which player is 
		attempting to move this piece (since forward for one player means backward for another) */
		
		// can move 1 step Directly South bound if position is empty
		if(yMove == player.forwardDirection(yCurrent) && xMove == xCurrent && !isMovePositionOccupied ) 
			isLegalAttemptedMove = 1;
		// can move 1 step south east in order to kill opponent's piece
		if(yMove == player.forwardDirection(yCurrent) && xMove == xCurrent + 1 && isMovePositionOccupied)
			isLegalAttemptedMove = 1;
		// can move 1 step south west in order to kill opponents piece
		if(yMove == player.forwardDirection(yCurrent) && xMove == xCurrent - 1 && isMovePositionOccupied)
			isLegalAttemptedMove = 1;	
			
		
		return isLegalAttemptedMove;
		
	} 

}
