package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.models.Models;
import chess.models.ModelsInitializer;
import chess.resources.*;

public class ModelsInitializerTests {

	Board board;
	
	@Before
	public void setUp() throws Exception {
		this.board = new Board(8,8);
	}

	@Test
	public void StateInitializerConstructorTest() {
		Board board = new Board(20,20);
		ModelsInitializer init = new ModelsInitializer(board);
		assert(Models.stateRepresentation.length == 20);
		assert(Models.stateRepresentation[0].length == 20);
	}
	
	
	/* For all these build tests I test to see that number of piece in the state
	 * are the correct number after calling build<Piece>()
	 */
	
	
	@Test
	public void buildQueensTest() {
		Models.stateRepresentation = new ChessPiece[board.getHeight()][board.getWidth()];
		Models.board = board;
		ModelsInitializer.buildQueens();
		
		int numberOfQueensInState = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (Models.stateRepresentation[i][j] instanceof Queen)
					numberOfQueensInState++;
			}
		}
		assert(numberOfQueensInState == 2);	
	}
	
	@Test
	public void buildKnightsTest() {
		Models.stateRepresentation = new ChessPiece[board.getHeight()][board.getWidth()];
		Models.board = board;
		ModelsInitializer.buildKnights();
		
		int numberOfKnightsInState = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (Models.stateRepresentation[i][j] instanceof Knight)
					numberOfKnightsInState++;
			}
		}
		assert(numberOfKnightsInState == 4);	
	}
	
	
	@Test
	public void buildBishopsTest() {
		Models.stateRepresentation = new ChessPiece[board.getHeight()][board.getWidth()];
		Models.board = board;
		ModelsInitializer.buildBishops();
		
		int numberOfBishopsInState = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (Models.stateRepresentation[i][j] instanceof Bishop)
					numberOfBishopsInState++;
			}
		}
		assert(numberOfBishopsInState == 4);	
	}
	
	@Test
	public void buildPawnsTest() {
		Models.stateRepresentation = new ChessPiece[board.getHeight()][board.getWidth()];
		Models.board = board;
		ModelsInitializer.buildPawns();
		
		int numberOfPawnsInState = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (Models.stateRepresentation[i][j] instanceof Pawn)
					numberOfPawnsInState++;
			}
		}
		assert(numberOfPawnsInState == 16);	
	}
	
	@Test
	public void buildRookTest() {
		Models.stateRepresentation = new ChessPiece[board.getHeight()][board.getWidth()];
		Models.board = board;
		ModelsInitializer.buildRooks();
		
		int numberOfRooksInState = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (Models.stateRepresentation[i][j] instanceof Rook)
					numberOfRooksInState++;
			}
		}
		assert(numberOfRooksInState == 4);	
	}
	
	

}
