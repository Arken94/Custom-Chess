package chess.resources;

import chess.models.Models;

/**
 * King - class that handles all King movement logic. 
 * All check, check mate, and stale mate logic is implemented in this class.
 * 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class King extends ChessPiece {

	
	public boolean isChecked = false;
	public boolean isCheckMate = false;
	
	
	public King(Player player, int[] currentPosition) {
		super(player, currentPosition);
	}

	@Override
	public String attemptMove(int[] moveToPosition) {

		int isLegalAttemptedMove = isMoveAllowed(this.currentPosition, moveToPosition);
		
		if(isLegalAttemptedMove == 1){
			this.move(moveToPosition);
			this.isChecked = false; // Every move of the king must always be to an unchecked location
			return "success";
		} else {
			String message = "You have attempted to illegally move this king.";
			System.out.println(message);
			return(message);
		}
	}
	
	/**
	 * Checks whether this King object is allowed to move to the requested location.
	 * allows for all  moves along with a delta of 1. 
	 * 
	 * @param moveToPosition - and integer array of size two which specifies the request move position
	 * 
	 * @return returns an integer indicating whether the requested move is allowed or not. 1 means it is allowed. 
	 * 		   2 means that the requested move is not allowed. 
	 */
	public static int isMoveAllowed(int[] currentPosition, int[] moveToPosition){
		
		// prevent move to a location occupied by player that is attempting to move.
		boolean isValid = isValidMovePosition(currentPosition, moveToPosition);
		if(!isValid) return 2;
		
		int yMove = moveToPosition[0]; // y coordinate of requested move position
		int xMove = moveToPosition[1]; // x coordinate of requested move position
		int yCurrent = currentPosition[0]; // y coordinate of current position
		int xCurrent = currentPosition[1]; // x coordinate of current position
		
		int deltaY = Math.abs(yCurrent - yMove); // change in y coordinates for requested move
		int deltaX = Math.abs(xCurrent - xMove); // change in x coordinates for requested move
		
		ChessPiece king = Models.getPieceAtPosition(currentPosition);
		
		// The king is allowed to move one step at a time -- sum of delta x and y should be equal 1 in all cases.
		if( deltaY > 1 || deltaX > 1 ) return 2; 
		
		// The king can NOT move to a place that causes him to be checked!
		else{
			
			// simulate move to see if the king would be placed in check if it moves to 'moveToPosition'
			ChessPiece occupying = Models.getPieceAtPosition(moveToPosition);
			Models.changePiecePosition(currentPosition, moveToPosition);
			
			boolean isPotentiallyChecked = isChecked(king, moveToPosition);
			
			//reset the state to how it was, since we are only checking and not actually playing
			Models.changePiecePosition(moveToPosition, currentPosition);
			Models.stateRepresentation[moveToPosition[0]][moveToPosition[1]] = occupying;
			
			if(!isPotentiallyChecked) return 1;
		}
		
		return 2;
	}
	
	/**
	 * Checks whether there is at least one position that the king can move to without being placed
	 * under check! 
	 * 
	 * @param king - the king that you are checking for possible moves  
	 * 
	 * @return boolean - indicates whether this king, at currentPosition,  can make any possible moves
	 */
	public boolean isKingAbleToMove(){
		
		// simply loop through and see if all possible moves for this king would result in a check
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				
				if (i == 0 && j == 0) continue;
				
				int[] checkPosition = new int[2];
				checkPosition[0] = this.currentPosition[0] + i;
				checkPosition[1] = this.currentPosition[1] + j;
				int canMove = isMoveAllowed(this.currentPosition, checkPosition);
				System.out.println(checkPosition[0]+ " "+checkPosition[1]);
				if(canMove == 1) return true; // there is a place for the king to move to!
				
			}
		}
		return false;
	}
	
	
	
	/**
	 * Checks whether the king is under stale mate. Stale mates only occur when the king is NOT in check
	 * but he has no place to move. So we do a simple check to see if that is the case!
	 *  
	 * @return boolean - indicates whether this king, is in a stale mate
	 */
	public boolean isKingInStalemate(){
		return !this.isChecked && !this.isKingAbleToMove();
	}
	
	
	
	/**
	 * This method should be called after any piece from the opposing team is moved to check for 
	 * checks on this king. It will loop through all opposing pieces and see if they have a feasible 
	 * attack to this king. 
	 * 
	 * This method is implemented in a very clever way. Instead of rewriting the logic for checks and 
	 * check mates, I used the logic that I already had. I has already implemented isMoveAllowed() for 
	 * all types of chess pieces. This function would check to see if that piece is allowed to move to a 
	 * specific location. In order to check for Check/check mates I would loop through all the opponents
	 * pieces and check if any of their pieces are allowed to move to the location of the king in question 
	 * (using the isMoveAllowed() function I had previously written). If any single opposing piece
	 * is able to move to the kings location, than we know that the king is placed under check. 
	 * 
	 * @param currentPosition - the position of the king that you are verifying if it is under check or not. 
	 * @param king - king that we are verifying
	 * 
	 * 
	 * @return boolean - indicates whether this king, at currentPosition,  is currently under check
	 */
	public static boolean isChecked(ChessPiece king, int[] currentPosition){
		
		
		// Loop through entire state representation, and validate all opposing team's pieces
		// for check on this king piece.
		for(int i = 0; i < Models.stateRepresentation.length; i++){
			for(int j = 0; j < Models.stateRepresentation[i].length; j++){
				ChessPiece piece = Models.stateRepresentation[i][j];
				int canCheckKing = 2; // failure code returned by isMoveAllowed() call
				
				if(piece == null) continue;
				
				// if the piece belongs to the opposing team then check if the piece is "allowed to move"
				// to this kings position. If so, then the king can be checked. 
				if(piece.player != king.player){
					if (piece instanceof Rook)
						canCheckKing = Rook.isMoveAllowed(piece.currentPosition, currentPosition);
					
					if (piece instanceof Bishop)
						canCheckKing = Bishop.isMoveAllowed(piece.currentPosition, currentPosition);
					
					if (piece instanceof Queen)
						canCheckKing = Queen.isMoveAllowed(piece.currentPosition, currentPosition);
					
					if (piece instanceof Pawn) // complete diagonal kill checks
						canCheckKing = Pawn.isMoveAllowed(piece.currentPosition, currentPosition);
					
					if (piece instanceof Knight)
						canCheckKing = Knight.isMoveAllowed(piece.currentPosition, currentPosition);	
				}
				// if the opposing team's piece CAN move to this king's current position, then it is a check
				if(canCheckKing == 1){
					return true;
				}
				
			}
		}
		return false;
	}

}





