package chess.resources;
/**
 * Player - an enumeration type to specify players.
 * 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public enum Player {
	
	PLAYER_NORTH("north"), // the player that plays from the north side of the board
	PLAYER_SOUTH("south"); // the player that plays from the south side of the board
	
	private String playerType;
	
	Player(String init){
		playerType = init;
	}
	
	/**
	 * this method calculates the forward position from the perspective of the player - since forward
	 * for the NORTH player is backward for the SOUTH player and vice versa. 
	 * 
	 * @param currentYCoordinate: current y coordinate to increment forward
	 * @return an integer type representing the y coordinate after moving one step forward
	 */
	int forwardDirection(int currentYCoordinate){
		if(playerType == "north") return currentYCoordinate + 1;		
		else return currentYCoordinate - 1;	
	}

}
