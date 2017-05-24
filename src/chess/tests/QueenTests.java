package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.models.Models;
import chess.resources.ChessPiece;
import chess.resources.Knight;
import chess.resources.Player;
import chess.resources.Queen;

public class QueenTests {

	Queen queen;
	
	@Before
	public void setUp() throws Exception {
		
		/* set up a mock state and place a single knight in it **/
		
		Models.stateRepresentation = new ChessPiece[8][8];
		int[] newPosition = {0,3}; // standard location for a knight
		queen = new Queen(Player.PLAYER_NORTH, newPosition);
		Models.stateRepresentation[0][3] = queen;
	}

	@Test
	public void attemptMoveTest1() {
		int[] moveToPosition = {1, 4}; // diagonal
		String result = queen.attemptMove(moveToPosition);
		assert(result == "success");
		
	}

	
	@Test
	public void attemptMoveTest2() {
		int[] moveToPosition = {4, 4}; // move not allowed by queen .
		String result = queen.attemptMove(moveToPosition);
		assert(result == "You have attempted to illegally move this queen.");	
	}
	
	
	@Test
	public void isMoveAllowedTest1() {
		int[] moveToPosition = {7, 3};
		int result = Queen.isMoveAllowed(queen.currentPosition, moveToPosition);
		assert(result == 1); // this move should be allowed
	}
	
	@Test
	public void isMoveAllowedTest2() {
		int[] moveToPosition = {5, 1};
		int result = Queen.isMoveAllowed(queen.currentPosition, moveToPosition);
		assert(result == 2); // this move should NOT be allowed
	}

}
