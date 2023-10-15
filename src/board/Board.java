package board;

import pieces.*;

/**
 * The board class initializes the board and pieces. The board is a 2-d 8X8 array.
 * The pieces are initialized stored in standard starting positions on the board.
 * There are several helper methods which are used to properly print the board.
 * 
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0
 * @since 2022-03-22
 */




public class Board {
	
    /**
	 * board is a 2-d array that will store all pieces. 
	 */
	public static Piece[][] board = new Piece[8][8];
	
	
    /**
     *This method is used to initialize the board 2-d array
     *by setting all spots to null by default. Then each piece
     *object is created and stored in correct order and spot
     *
     *Board is read like this
     * 0
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7 
     *   0 1 2 3 4 5 6 7
     */
	public static void init() {
	
	for(int i =0; i < 8; i++) {
	
	for(int j = 0; j < 8; j++) {
	
	board[i][j] = null;
	
	}
	}
	
	// Black Pieces
	board[1][0] = new Pawn("bp", false);
	board[1][1] = new Pawn("bp", false);
	board[1][2] = new Pawn("bp", false);
	board[1][3] = new Pawn("bp", false);
	board[1][4] = new Pawn("bp", false);
	board[1][5] = new Pawn("bp", false);
	board[1][6] = new Pawn("bp", false);
	board[1][7] = new Pawn("bp", false);

	
	board[0][0] = new Rook("bR", false);
	board[0][1] = new Knight("bN", false);
	board[0][2] = new Bishop("bB", false);
	board[0][3] = new Queen("bQ", false);
	board[0][4] = new King("bK", false);
	board[0][5] = new Bishop("bB", false);
	board[0][6] = new Knight("bN", false);
	board[0][7] = new Rook("bR", false);
	
	// White Pieces
	board[6][0] = new Pawn("wp", true);
	board[6][1] = new Pawn("wp", true);
	board[6][2] = new Pawn("wp", true);
	board[6][3] = new Pawn("wp", true);
	board[6][4] = new Pawn("wp", true);
	board[6][5] = new Pawn("wp",true);
	board[6][6] = new Pawn("wp", true);
	board[6][7] = new Pawn("wp", true);
	
	board[7][0] = new Rook("wR", true);
	board[7][1] = new Knight("wN", true);
	board[7][2] = new Bishop("wB", true);
	board[7][3] = new Queen("wQ", true);
	board[7][4] = new King("wK", true);
	board[7][5] = new Bishop("wB", true);
	board[7][6] = new Knight("wN", true);
	board[7][7] = new Rook("wR", true);
	
	}
	
	/**
	 * This method prints the pattern used in the printBoard() method
	 * to match the required board structure
	 * @param x This is the x-coordinate 
	 * @param y This is the y-coordinate
	 */
	public static void printPattern(int x, int y ) {
	if (x % 2 == 0) {
	if (y % 2 == 0) {
	System.out.print("   ");
	}
	else {
	System.out.print("## ");
	}
	} else if (x % 2 == 1) {
	if (y % 2 == 0) {
	System.out.print("## ");
	} 
	else {
	System.out.print("   ");
	}
	}
	}
	
	/**
	 * This method prints the board according to the pattern specified
	 * by using the 2-d board array, printPatter() method, and the
	 * piece objects within the array
	 * 
	 */
	public static void printBoard() {
	
	int n = 8;
	
	for (int x = 0; x < 8; x++) {
	
	for (int y = 0; y < 8; y++) {
	
	if (board[x][y] instanceof Pawn) {
	System.out.print(board[x][y].getType() + " ");
	} 
	else if (board[x][y] instanceof Rook) {
	System.out.print(board[x][y].getType() + " ");
	}
	else if (board[x][y] instanceof Knight) {
	System.out.print(board[x][y].getType() + " ");
	}
	else if (board[x][y] instanceof Bishop) {
	System.out.print(board[x][y].getType() + " ");
	}
	else if (board[x][y] instanceof Queen) {
	System.out.print(board[x][y].getType() + " ");
	}
	else if (board[x][y] instanceof King) {
	System.out.print(board[x][y].getType() + " ");
	}
	else {
	printPattern(x,y);
	}
	
	// BORDER: RIGHT (Numbers)
	if (y == 7) {
	System.out.print(n);
	n--;
	}
	
	}
	
	System.out.println();
	
	// BORDER: BOTTOM (Letters)
	if (x == 7) {
	char letter = 'a';
	for (int bord = 0; bord < 8; bord++) {
	
	System.out.print(" " + letter + " ");
	letter = (char) (letter + 1);
	
	}
	System.out.println();
	}
	
	}
	
	System.out.println();
	}
	
	/**
	 * This method converts String to integers the board can understand i.e e2 will be converted to 4 2 
	 * @param input This is the only input and represents the String version of the Piece's coordinates
	 * @return int[] This method returns an integer array of the String cord converted to numbers
	 */
	public static int[] convertCords(String input) {
	int[] cords = new int[2];
	
	if(input.charAt(0) == ('a')) {
	cords[0] = 0;
	}
	if(input.charAt(0) == ('b')) {
	cords[0] = 1;
	}
	if(input.charAt(0) == ('c')) {
	cords[0] = 2;
	}
	if(input.charAt(0) == ('d')) {
	cords[0] = 3;
	}
	if(input.charAt(0) == ('e')) {
	cords[0] = 4;
	}
	if(input.charAt(0) == ('f')) {
	cords[0] = 5;
	}
	if(input.charAt(0) == ('g')) {
	cords[0] = 6;
	}
	if(input.charAt(0) == ('h')) {
	cords[0] = 7;
	}
	
	cords[1] = 8 - Character.getNumericValue(input.charAt(1));
	
	return cords;
	
	}
	
}

