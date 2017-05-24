package chess.models;

import chess.resources.*;


/**
 * 
 * Command - this class represents a single command. It stores all the information necessary 
 * in order to reverse it. It is used in conjuction with the command stack in order to 
 * reverse commands that have been initiation by the user. 
 * 
 * @author Arken Ibrahim: amibrah2@illinois.edu
 *
 */
public class Command {

	
	public Player player;
	public ChessPiece piece;
	public int[] backToCoordinates;
	 
	public ChessPiece killedPiece = null; // stores the piece that this command killed, if any. 
	
	public Command(Player player,ChessPiece piece, int[] backToCoordinates ) {
		this.player = player;
		this.piece = piece;
		this.backToCoordinates = backToCoordinates;
	}

}
