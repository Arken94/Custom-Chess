package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.models.Models;
import chess.resources.Bishop;
import chess.resources.ChessPiece;
import chess.resources.Pawn;
import chess.resources.Player;

public class PawnTests {

	Pawn pawn;
	
	@Before
	public void setUp() throws Exception {
		
		/* set up a mock state and place a single pawn in it **/
		
		Models.stateRepresentation = new ChessPiece[8][8];
		int[] newPosition = {1,1}; // a pawn's standard location
		pawn = new Pawn(Player.PLAYER_NORTH, newPosition);
		Models.stateRepresentation[1][1] = pawn;
	}

	@Test
	public void attemptMoveTest1() {
		int[] moveToPosition = {2, 1}; // move forward one step .
		String result = pawn.attemptMove(moveToPosition);
		assert(result == "success");
		
	}

	
	@Test
	public void attemptMoveTest2() {
		int[] moveToPosition = {1, 2}; // move sideways .
		String result = pawn.attemptMove(moveToPosition);
		assert(result == "You have attempted to illegally move this Pawn.");	
	}
	
	
	@Test
	public void isMoveAllowedTest1() {
		int[] moveToPosition = {2, 1};
		int result = Pawn.isMoveAllowed( pawn.currentPosition, moveToPosition);
		assert(result == 1); // this move should be allowed
	}
	
	@Test
	public void isMoveAllowedTest2() {
		int[] moveToPosition = {5, 1};
		int result = Pawn.isMoveAllowed(pawn.currentPosition, moveToPosition);
		assert(result == 2); // this move should NOT be allowed
	}

}
