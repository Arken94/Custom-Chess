package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.models.Models;
import chess.resources.Bishop;
import chess.resources.ChessPiece;
import chess.resources.Knight;
import chess.resources.Player;

public class KnightTests {

	Knight knight;
	
	@Before
	public void setUp() throws Exception {
		
		/* set up a mock state and place a single knight in it **/
		
		Models.stateRepresentation = new ChessPiece[8][8];
		int[] newPosition = {0,1}; // standard location for a knight
		knight = new Knight(Player.PLAYER_NORTH, newPosition);
		Models.stateRepresentation[0][1] = knight;
	}

	@Test
	public void attemptMoveTest1() {
		int[] moveToPosition = {1, 3}; // move L-shape
		String result = knight.attemptMove(moveToPosition);
		assert(result == "success");
		
	}

	
	@Test
	public void attemptMoveTest2() {
		int[] moveToPosition = {4, 4}; // move non l-shape .
		String result = knight.attemptMove(moveToPosition);
		assert(result == "You have attempted to illegally move this knight.");	
	}
	
	
	@Test
	public void isMoveAllowedTest1() {
		int[] moveToPosition = {2, 2};
		int result = Knight.isMoveAllowed(knight.currentPosition, moveToPosition);
		assert(result == 1); // this move should be allowed
	}
	
	@Test
	public void isMoveAllowedTest2() {
		int[] moveToPosition = {5, 1};
		int result = Knight.isMoveAllowed(knight.currentPosition, moveToPosition);
		assert(result == 2); // this move should NOT be allowed
	}

}
