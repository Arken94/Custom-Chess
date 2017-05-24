package chess.resources;

/**
 * Frog - Class that handles all frog movement logic. 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Frog extends ChessPiece {

	public Frog(Player player, int[] currentPosition) {
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
			String message = "You have attempted to illegally move this frog.";
			System.out.println(message);
			return(message);
		}
	}
	

	
	/**
	 * Checks whether this frog object is allowed to move to the requested location.
	 * allows for all 2-step jumps along any path - since frogs can jump over other pieces of course :). 
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
		return ( deltaY + deltaX == 2 ) ? 1 : 2;
		
		
	}

}
