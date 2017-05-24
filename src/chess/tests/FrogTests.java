package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.models.Models;
import chess.resources.*;
import chess.resources.ChessPiece;
import chess.resources.Player;

public class FrogTests {

	ChessPiece frog;
	
	@Before
	public void setUp() throws Exception {
		
		/* set up a mock state and place a single bishop in it **/
		
		Models.stateRepresentation = new ChessPiece[8][8];
		int[] newPosition = {0,2};
		frog = new Frog(Player.PLAYER_NORTH, newPosition);
		Models.stateRepresentation[0][2] = frog;
	}

	@Test
	public void attemptMoveTest1() {
		int[] moveToPosition = {0, 4}; // move directly to the diagonal.
		String result = frog.attemptMove(moveToPosition);
		assert(result == "success");
		
	}

	
	@Test
	public void attemptMoveTest2() {
		int[] moveToPosition = {6, 3}; // move undiagonally .
		String result = frog.attemptMove(moveToPosition);
		assert(result == "You have attempted to illegally move this frog.");	
	}
	
	
	@Test
	public void isMoveAllowedTest1() {
		int[] moveToPosition = {2, 2};
		int result = Frog.isMoveAllowed(frog.currentPosition, moveToPosition);
		assert(result == 1); // this move should be allowed
	}
	
	@Test
	public void isMoveAllowedTest2() {
		int[] moveToPosition = {5, 1};
		int result = Frog.isMoveAllowed(frog.currentPosition, moveToPosition);
		assert(result == 2); // this move should NOT be allowed
	}

}
