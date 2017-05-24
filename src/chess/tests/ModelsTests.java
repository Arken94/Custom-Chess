package chess.tests;

import chess.models.*;
import chess.resources.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Before;
import org.junit.Test;
/**
 * Tests all methods in the State class. Assumes initializeState() is working. 
 * This method is tested in StateInitializerTest()
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class ModelsTests {

	
	Board board;
	
	@Before
	public void setUp() throws Exception {
		Models.initializeState(); // we will test this in Initializer state	
	}

	@Test
	public void changePiecePositionTest1() {
		
		//changing the state of any piece should work.
		int[] position1 = {0, 1}; 
		int[] position2 = {2, 3};  
		String result = Models.changePiecePosition(position1, position2);
		assert(result == "success");
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void changePiecePositionTest2() {
		int[] position1 = {0, 1}; // this should be knight
		int[] position2 = {20, 20}; // this move should throw and error, out of bounds
		String result = Models.changePiecePosition(position1, position2);	
	}	
	
	
	@Test
	public void isPositionOccupiedTest1() {
		//should be occupied.
		int[] position1 = {0, 7};  
		boolean result = Models.isPositionOccupied(position1);
		assert(result == true);	
	}
	
	@Test
	public void isPositionOccupiedTest2() {
		//should NOT occupied.
		int[] position1 = {4,0};  
		boolean result = Models.isPositionOccupied(position1);
		assert(result == false);
		
	}
	
	
	@Test
	public void getPieceAtPosition() {
		//should return a ChessPiece object.
		int[] position1 = {1,0};  
		ChessPiece result = Models.getPieceAtPosition(position1);
		assertTrue(result instanceof ChessPiece);
		
	}

}
