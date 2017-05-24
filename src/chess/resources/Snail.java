package chess.resources;
/**
 * Snail - Class that handles all snail movement logic. 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Snail extends ChessPiece {

	public Snail(Player player, int[] currentPosition) {
		super(player, currentPosition);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public String attemptMove(int[] moveToPosition) {
		int isLegalAttemptedMove = isMoveAllowed(this.currentPosition, moveToPosition);
		if(isLegalAttemptedMove == 1){
			this.move(moveToPosition);
			return "success";
		} else {
			String message = "You have attempted to illegally move this snail.";
			System.out.println(message);
			return(message);
		}
	}
	

	
	/**
	 * Checks whether this snail object is allowed to move to the requested location.
	 * allows for all 1-step diagonal moves along any path. 
	 * 
	 * @param moveToPosition - and integer array of size two which specifies the request move position
	 * 		  currentPosition - currentPosition of the bishop piece requesting to move.
	 * 
	 * @return returns an integer indicating whether the requested move is allowed or not. 1 means it is allowed. 
	 * 		   2 means that the requested move failed.
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
		
		// the move was not a 3-step L-shaped move if deltaY + deltaX != 3
		return ( deltaY == 1 && deltaX == 1 ) ? 1 : 2;
		
		
	}

}
