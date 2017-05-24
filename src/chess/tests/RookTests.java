package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.models.Models;
import chess.resources.ChessPiece;
import chess.resources.Knight;
import chess.resources.Player;
import chess.resources.Rook;

public class RookTests {

	Rook rook;
	
	@Before
	public void setUp() throws Exception {
		
		/* set up a mock state and place a single rook in it **/
		
		Models.stateRepresentation = new ChessPiece[8][8];
		int[] newPosition = {0,0}; // standard location for a rook
		rook = new Rook(Player.PLAYER_NORTH, newPosition);
		Models.stateRepresentation[0][0] = rook;
	}

	@Test
	public void attemptMoveTest1() {
		int[] moveToPosition = {0, 7}; // move straight
		String result = rook.attemptMove(moveToPosition);
		assert(result == "success");
		
	}

	
	@Test
	public void attemptMoveTest2() {
		int[] moveToPosition = {4, 4}; // move un straight.
		String result = rook.attemptMove(moveToPosition);
		assert(result == "You have attempted to illegally move this rook.");	
	}
	
	
	@Test
	public void isMoveAllowedTest1() {
		int[] moveToPosition = {7, 0};
		int result = Rook.isMoveAllowed(rook.currentPosition, moveToPosition);
		assert(result == 1); // this move should be allowed
	}
	
	@Test
	public void isMoveAllowedTest2() {
		int[] moveToPosition = {5, 1};
		int result = Rook.isMoveAllowed(rook.currentPosition, moveToPosition);
		assert(result == 2); // this move should NOT be allowed
	}


}
