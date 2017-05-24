package chess.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import chess.models.Models;
import chess.resources.*;
/**
 * 
 * Check, check mate, and stale mate testing are all in here!
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class KingTests {
	
	King king;
	Rook rook;
	
	@Before
	public void setUp() throws Exception {
		
		/* set up a mock state and place a single king and 3 rooks to 
		 * SIMULATE check mates and stalemates in it **/
		
		Models.stateRepresentation = new ChessPiece[8][8];
		int[] newCoords = {0, 4}; 
		king = new King(Player.PLAYER_NORTH, newCoords);
		Models.stateRepresentation[0][4] = king;
		Models.kingNorth = king;
		
		int[] newCoords2 = {0, 1}; 
		Rook rook2 = new Rook(Player.PLAYER_SOUTH, newCoords2);
		Models.stateRepresentation[0][1] = rook2;
		
		int[] newCoords3 = {0, 5}; 
		Rook rook3 = new Rook(Player.PLAYER_SOUTH, newCoords3);
		Models.stateRepresentation[0][5] = rook3;
		
		int[] newCoords1 = {4, 4}; 
		rook = new Rook(Player.PLAYER_SOUTH, newCoords1);
		Models.stateRepresentation[4][4] = rook;
	}

	@Test
	public void isChecked1() {
		int[] moveToPosition = {3, 4}; // move directly to the diagonal.
		String result = rook.attemptMove(moveToPosition);
		assert(result == "success");
		assert(king.isChecked == true);	
	}
	
	
	@Test
	public void isChecked2() {
		int[] moveToPosition = {4, 6}; // move directly to the diagonal.
		String result = rook.attemptMove(moveToPosition);
		assert(result == "success");
		assert(king.isChecked == true);	
	}
	
	
	@Test
	public void isCheckmate1() {
		
		int[] moveToPosition = {2, 4}; // move directly to the diagonal.
		String result = rook.attemptMove(moveToPosition);
		assert(result == "success");
		assert(king.isChecked == true);	
		assert(Models.isRunning == true);	

	}
	
	@Test
	public void isCheckmate2() {
		
		int[] moveToPosition = {6, 4}; // move directly to the diagonal.
		String result = rook.attemptMove(moveToPosition);
		assert(result == "success");
		assert(king.isChecked == true);	
		assert(Models.isRunning == true);	

	}
	
	
	@Test
	public void isCheckmate3() {
		
		int[] moveToPosition = {3, 4}; // move directly to the diagonal.
		String result = rook.attemptMove(moveToPosition);
		assert(result == "success");
		assert(king.isChecked == true);	
		assert(Models.isRunning == true);	

	}
	
	@Test
	public void isCheckmate4() {
		
		int[] moveToPosition = {5, 4}; // move directly to the diagonal.
		String result = rook.attemptMove(moveToPosition);
		assert(result == "success");
		assert(king.isChecked == true);	
		assert(Models.isRunning == true);	

	}
	
	@Test
	public void isStaleMate() {
		// this is implied by the setup of the board in setup();
		assert(king.isKingInStalemate() == false);

	}

	
	@Test
	public void isStaleMate2() {
		int[] moveToPosition = {1, 4}; // move directly to the diagonal.
		String result = rook.attemptMove(moveToPosition);
		assert(result == "success");
		// this is implied by the setup of the board in setup();
		assert(king.isKingInStalemate() == false);

	}

	
}
