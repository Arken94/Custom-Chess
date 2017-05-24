package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.models.Models;
import chess.resources.ChessPiece;
import chess.resources.Player;
import chess.resources.Snail;

public class SnailTests {

	ChessPiece snail;
	
	@Before
	public void setUp() throws Exception {
		
		/* set up a mock state and place a single bishop in it **/
		
		Models.stateRepresentation = new ChessPiece[8][8];
		int[] newPosition = {0,2};
		snail = new Snail(Player.PLAYER_NORTH, newPosition);
		Models.stateRepresentation[0][2] = snail;
	}

	@Test
	public void attemptMoveTest1() {
		int[] moveToPosition = {1, 3}; // move directly to the diagonal.
		String result = snail.attemptMove(moveToPosition);
		assert(result == "success");
		
	}

	
	@Test
	public void attemptMoveTest2() {
		int[] moveToPosition = {6, 3}; // move undiagonally .
		String result = snail.attemptMove(moveToPosition);
		assert(result == "You have attempted to illegally move this snail.");	
	}
	
	
	@Test
	public void isMoveAllowedTest1() {
		int[] moveToPosition = {1, 1};
		int result = Snail.isMoveAllowed(snail.currentPosition, moveToPosition);
		assert(result == 1); // this move should be allowed
	}
	
	@Test
	public void isMoveAllowedTest2() {
		int[] moveToPosition = {5, 1};
		int result = Snail.isMoveAllowed(snail.currentPosition, moveToPosition);
		assert(result == 2); // this move should NOT be allowed
	}

}
