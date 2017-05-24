package chess.resources;

import chess.models.Models;



/**
 * Rook - Class that handles all rook movement logic. 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Rook extends ChessPiece {

	
	
	public Rook(Player player, int[] currentPosition) {
		super(player, currentPosition);
	}

	
	
	@Override
	public String attemptMove(int[] moveToPosition) {
		
		int isLegalAttemptedMove = isMoveAllowed(this.currentPosition, moveToPosition);
		if(isLegalAttemptedMove ==1){
			this.move(moveToPosition);
			return "success";
			
		} else if(isLegalAttemptedMove == 3){
			String message = "You have attempted to illegally move this rook through an obstructed path";
			System.out.println(message);
			return(message);
			
		}else{
			String message = "You have attempted to illegally move this rook.";
			System.out.println(message);
			return(message);
		}
		
		
	}
	
	
	/**
	 * Checks whether a Rook object is allowed to move to the requested location.
	 * allows for all straight moves along free paths. Will return error codes if there is any 
	 * obstructing piece on the path to the requested position (including pieces that belong
	 * to the current player).
	 * 
	 * @param moveToPosition - and integer array of size two which specifies the request move position.
	 * 		  currentPosition - currentPosition of the bishop piece requesting to move.
	 * 
	 * @return returns an integer indicating whether the requested move is allowed or not. 1 means it is allowed. 
	 * 		   2 means that the requested move is not straight. and 3 means that the requested move was obstructed.
	 */
	
	public static int isMoveAllowed(int[] currentPosition, int[] moveToPosition){
		int yMove = moveToPosition[0]; // y coordinate of requested move position
		int xMove = moveToPosition[1]; // x coordinate of requested move position
		int yCurrent = currentPosition[0]; // y coordinate of current position
		int xCurrent = currentPosition[1]; // x coordinate of current position
		
		int deltaY = Math.abs(yCurrent - yMove); // change in y coordinates for requested move
		int deltaX = Math.abs(xCurrent - xMove); // change in x coordinates for requested move
		
		boolean isValid = isValidMovePosition(currentPosition, moveToPosition);
		if(!isValid) return 2;
		
		
		if(deltaY != 0 && deltaX != 0) return 2;	// This is not a STRAIGHT path. 
		
		// If we are moving straight north check for any obstructions in its path
		if (yMove < yCurrent && xMove == xCurrent){ 
			for(int pathCount = 0; pathCount < deltaY -1; pathCount++){
				yCurrent-=1;  /* Moving north  */
				int[] checkPosition = {yCurrent, xCurrent};
				if( Models.isPositionOccupied(checkPosition)) return 3;
			}
			return 1;	
		}
		// If we are moving straight south  check for any obstructions in its path
		if (yMove > yCurrent && xMove == xCurrent){ 	
			for(int pathCount = 0; pathCount < deltaY -1; pathCount++){
				yCurrent+=1;  /* Moving south  */
				int[] checkPosition = {yCurrent, xCurrent};
				if( Models.isPositionOccupied(checkPosition)) return 3;
			}
			return 1;		
		}
		// If we are moving straight  west check for any obstructions in its path
		if (yMove == yCurrent && xMove < xCurrent){ 	
			for(int pathCount = 0; pathCount < deltaX -1; pathCount++){
				xCurrent-=1; /* Moving  west */
				int[] checkPosition = {yCurrent, xCurrent};
				if( Models.isPositionOccupied(checkPosition)) return 3;
			}
			return 1;		
		}
		// If we are moving straight south east check for any obstructions in its path
		if (yMove == yCurrent && xMove > xCurrent){ 	
			for(int pathCount = 0; pathCount < deltaX - 1; pathCount++){
				xCurrent+=1; /* Moving south east */
				int[] checkPosition = {yCurrent, xCurrent};
				if( Models.isPositionOccupied(checkPosition)) return 3;
			}
			return 1;			
		}
		return 2;
	}

}
