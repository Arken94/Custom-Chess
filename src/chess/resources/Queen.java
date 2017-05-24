package chess.resources;


/**
 * Queen - Class that handles all queen movement logic. 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Queen extends ChessPiece {

	
	
	
	public Queen(Player player, int[] currentPosition) {
		super(player, currentPosition);
	}

	

	
	@Override
	public String attemptMove(int[] moveToPosition) {
		
		int isLegalAttemptedMove = isMoveAllowed(this.currentPosition, moveToPosition);
		
		if(isLegalAttemptedMove == 1){
			this.move(moveToPosition);
			return "success";
		} else {
			String message = "You have attempted to illegally move this queen.";
			System.out.println(message);
			return(message);
		}
		
	}
	
	
	
	
	/**
	 * Checks whether this Queen object is allowed to move to the requested location.
	 * allows for all diagonal and straight moves along free paths. Will return an error code if there is any 
	 * obstructing piece on the path to the requested position (including pieces that belong
	 * to the current player). 
	 * 
	 * @param currentPosition - current position of piece.
	 * @param moveToPosition - and integer array of size two which specifies the request move position
	 * 
	 * @return returns an integer indicating whether the requested move is allowed or not. 1 means it is allowed. 
	 * 		   2 means that the requested move is not allowed. 
	 */
	public static int isMoveAllowed(int[] currentPosition, int[] moveToPosition){
		
		boolean isValid = isValidMovePosition(currentPosition, moveToPosition);
		if(!isValid) return 2;
		
		/* We already implemented diagonal and straight moves for bishops and rooks,
		 * to be clean and concise, we will reuse that code for the queen's movements as well. 
		 */
		int isDiagonalAllowed = Bishop.isMoveAllowed(currentPosition, moveToPosition);
		int isStraightAllowed = Rook.isMoveAllowed(currentPosition, moveToPosition);
		return (isDiagonalAllowed == 1 || isStraightAllowed == 1) ? 1 : 2;

	}

}
