package pieces;


/**
 * Abstract Class designed for all Pieces on the Chess Board
 * It has all the basic methods and variables required to make pieces function
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0 
 * @since 2022-03-22
 */
public abstract class Piece {
	
	/**
	 * The type variable declares the type of Piece being created 
	 * It will be wR..wB according to the board position
	 */
	String type; 
	
	/**
	 * The didMove variable track if a piece has moved or not
	 * true if the piece moved, false otherwise
	 */
	boolean didMove;
	
	/**
	 * The team variable tells tracks what team a piece belongs to
	 * true if the piece is White, false if Black
	 */
	boolean team; 
	
	/**
	 * The en_passantable variables tracks if the Piece can use enpassant or not 
	 * true if it can, false otherwise
	 */
	boolean en_passantable;
	
	
	/**
	 * The isCastling variables tracks whether or not castling is possible 
	 * true if castling is possible, false otherwise
	 */
	boolean isCastling;
	
	/**
	 * The enPassTurn variable tracks which player's turn it is so enpassante is done correctly 
	 * 1 - white turn, 2- black turn
	 */
	public int enPassTurn;
	
	/**
	 * Default Constructor for Piece, It creates an Instance of the piece and sets all the variables to default values
	 * @param type The type of piece it is 
	 * @param team The team the piece belongs to (true for White, false for Black)
	 */
	public Piece(String type, boolean team) {
		this.type = type;
		didMove = false;
		en_passantable = false;
		this.team = team;
		enPassTurn = 0;
	}
	
	/**
	 * This method gets the Team the piece is part of 
	 * @return boolean The piece's team true if white false otherwise
	 */
	
	public boolean getTeam() {
		return this.team;
	}
	
	/**
	 * This method checks if the Piece moved or not
	 * @return boolean Whether or not the piece moved or not : true if the piece moved false otherwise
	 */
	public boolean didMove() {
		return this.didMove;
	}
	
	/**
	 * This method sets the Piece move to true if the piece moved false otherwise
	 * @param didMove tracks whether or not the piece moved
	 * @return boolean true if the piece moved false otherwise
	 */
	public boolean setMove(boolean didMove) {
		return this.didMove = didMove;
	}
	
	/**
	 * This method gets the value of the Piece's enpassant
	 * @return boolean true if enpassant is possible false otherwise
	 */
	public boolean getEnpassant() {
		return this.en_passantable;
	}
	/**
	 * This method sets the value of the Piece's enpassant
	 * @param en_passantable true or false 
	 * @return boolean true if enpassant is possible false otherwise
	 */
	public boolean setEnpassant(boolean en_passantable) {
		return this.en_passantable = en_passantable;
	}
	
	/**
	 * This method gets the type of the Piece
	 * @return String the Piece's type i.e Rook, King, Bishop etc.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * This method gets the type of the Piece
	 * @return int The turn of the user 1 for White 2 for Black turn
	 */
	public int getEnPassTurn() {
		return this.enPassTurn;
	}
	
	
	/**
	 * Abstract method to move the Pieces depending on what it is
	 * @param oldPos The oldPos of the piece
	 * @param newPos The newPos of the piece
	 * @return boolean true if the piece moved false otherwise
	 */
	abstract public boolean move(String oldPos, String newPos);
	
	/**
	 * Abstract method to check if the Pieces path is clear 
	 * @param oldPos The oldPos of the piece
	 * @param newPos The newPos of the piece
	 * @return boolean true if the piece path is clear false otherwise
	 */
	abstract public boolean isPathClear(String oldPos, String newPos);
	
	/**
	 * Abstract method to check if the Pieces move is valid
	 * @param oldPos The oldPos of the piece
	 * @param newPos The newPos of the piece
	 * @return boolean true if the piece move is valid false otherwise
	 */
	abstract public boolean isMoveValid(String oldPos, String newPos);
	
	
}