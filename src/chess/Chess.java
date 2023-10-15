package chess;

import java.util.Scanner;
import java.util.ArrayList;

import board.*;
import pieces.Pawn;
import pieces.Piece;
import pieces.*;

/**
 * The Chess class contains the main method, and handles the logic for the 
 * chess game such as reading user input, keeping track of and printing turns,
 * printing board, displaying if move is invalid, draw, resign, check, CheckMate
 * along with displaying who the winner of the game is.
 * 
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0
 * @since 2022-03-22
 */


public class Chess {
	
	/**
	 * The turn variable tracks which player's move it is.
	 * 1 is white's move, 2 is blacks move. This variable is changed to 0
	 * to break out of the while loop to end the game;
	 */
	static int turn = 1;
	
	/**
	 * The team variable tracks which player's turn it is in boolean form.
	 * true is white's turn, 2 is blacks move.
	 */
	static boolean team = true;
	
	/**
	 * The turnCount variable tracks the move number the game is currently on.
	 * Each time player turn switches, turnCount increments.
	 * true is white's turn, 2 is blacks move.
	 */
	public static int turnCount = 0;
	
	/**
	 * The testMove variable tracks whether the sequence of code is testing 
	 * for hypothetical scenarios on the board. This ensures that during test moves,
	 * flags such as castling or enPassant aren't wrongly changed.
	 */
	public static boolean testMove = false;

	/**
	 * The valid_move variable tracks whether the move the user entered is valid
	 * or invalid. If invalid, the console will display so, and the inner while loop
	 * runs again, prompting the player to try entering a valid move. If move is valid,
	 * the inner while loop ends, and turn is switched.
	 */
	static boolean valid_move = true;
	
	
	/**
	 * The draw variable tracks whether a player has motioned to end the game as a draw.
	 * If so, during the next turn when the other player also motions to end game as draw,
	 * the game ends. 
	 */
	static boolean draw = false;
	
	/**
	 * The whiteInCheck variable is a flag for white king being in check if true
	 */
	static boolean whiteInCheck = false;
	
	/**
	 * The blackInCheck variable is a flag for black king being in check if true
	 */
	static boolean blackInCheck = false;
	
	/**
	 * The validMoves array list stores any valid moves that can take the current
	 * team out of check. If empty when in check, there are no valid moves that
	 * would take the current team out of check, meaning it is check mate and games
	 * ends declaring the other team as winner.
	 */
	static ArrayList<String> validMoves = new ArrayList<String>();

	
	// ArrayList that stores any valid moves for selected piece. USED ONLY DURING TESTING.
	// static ArrayList<String> valid = new ArrayList<String>();
	
	
	/**
	 * The main method. Initializes board, prints board, takes user input,
	 * moves if valid input, displays if invalid input, handles draw, handles
	 * resign, handles check and check mate.
	 * @param args The command line arguments.
	 **/
	public static void main(String[] args) {
	
	// Initialize board and pieces
	Board.init();
	
	// Print out Board
	Board.printBoard();

	// Create scanner to read user input
	Scanner scanner = new Scanner(System.in);
	
	// While white or black turn, game in process. When changed to 0, game is ended.
	while(turn == 1 || turn == 2) {
	
	// Updated each turn to track iteration of turn count. 
	turnCount++;
	
	// Update team boolean according to turn integer.
	if(turn == 1) {
	team = true;
	}
	
	else {
	team = false;
	}
	
	// While valid_move, inner while loop will run. If user enters valid move, set to false and breaks out of inner while loop.
	// If invalid move, valid_move remains true, and inner while loop repeats until valid move is entered.
	
	valid_move = true;
	
	while (valid_move) {
	
	// Start of sequence in which hypothetical moves are tested. EsPassant and Castling flags should not be affected.
	testMove = true;
	
	// Check if white king is or isn't in check
	if(WhiteInCheck()) {
	whiteInCheck = true;
	}
	else {
	whiteInCheck = false;
	}
	
	// Check if black king is or isn't in check
	if(BlackInCheck()) {
	
	blackInCheck = true;
	}
	else {
	blackInCheck = false;
	}
	
	// Clear validMoves arrayList
	validMoves.clear();
	
	// Check if there are any valid moves left, and populate validMoves arrayList with those moves
	anyMovesLeft(team);

	// End of sequence in which hypothetical moves are tested. EsPassant and Castling flags can now be affected.
	testMove = false;

	
	
	if(whiteInCheck) {
	
	if(validMoves.size() == 0) {
	System.out.println("Checkmate\nBlack Wins");
	turn = 0;
	valid_move = false;
	continue;
	}
	
	else {
	System.out.println("Check");
	}
	
	}
	
	if(blackInCheck) {
	
	if(validMoves.size() == 0) {
	System.out.println("Checkmate\nWhite Wins");
	turn = 0;
	valid_move = false;
	continue;	
	}
	
	else {
	System.out.println("Check");
	}
	}
	
	
	// Print which team's turn it is
	System.out.print(printTurn());
	
	// Read user input
	String userInput = scanner.nextLine();
	
	// Move to new line after user move
	System.out.print("");
	
	// If input is resign, declare other as winner, and get out of while loop.
	if(userInput.equals("resign")) {
	
	System.out.print(resign());
	turn = 0;
	valid_move = false;
	continue;
	
	}
	
	// If draw flag is true (other players queued for draw), and current user enters draw, end game as draw
	if(userInput.equals("draw") && draw) {
	
	turn = 0;
	valid_move = false;
	continue;
	
	}
	
	// Split user input and store in an array
	String[] splitted = userInput.split(" ");
	
	String oldPos = splitted[0];
	
	String newPos = splitted[1];
	
	String additionalInput = "";
	

	if(whiteInCheck || blackInCheck) {
	
	String testIfValidMove = oldPos + " " + newPos;
	
	if(!validMoves.contains(testIfValidMove) && selfCheck(oldPos, newPos)) {
	System.out.println("Illegal move, try again");
	}
	
	}
	
	if(splitted.length == 3) {
	
	additionalInput = splitted[2];
	
	}	
	
	if(additionalInput.equals("draw?")) {
	
	draw = true;
	
	}	
	
	int[] oldPosConv = Board.convertCords(oldPos);
	
	int[] newPosConv = Board.convertCords(newPos);
	

	/////// TEST METHOD FOR RETURNING VALID MOVES. USED ONLY IN TESTING.
	
//	valid.clear();
//	if(oldPos.equals("valid")) {
//	valid(team,newPos);
//	continue;
//	}
	
	//////////////////////////////////////////////////////////////
	
	if(!validCoords(oldPosConv, newPosConv)) {
	
	System.out.println("Illegal move, try again");
	
	}	
	
	else if(!selfCheck(oldPos, newPos)){
	
	if(Board.board[oldPosConv[1]][oldPosConv[0]] != null && additionalInput.length() == 1 
	&& ((Pawn) Board.board[oldPosConv[1]][oldPosConv[0]]).Promotion(oldPos, newPos, additionalInput)){
	
	valid_move = false;
	
	}
	
	else if(Board.board[oldPosConv[1]][oldPosConv[0]] != null && 
	Board.board[oldPosConv[1]][oldPosConv[0]].move(oldPos, newPos)) {
	
	valid_move = false;
	
	}
	
	else {
	
	System.out.println("Illegal move, try again");
	
	}
	
	}	


	}
	
	if(turn == 1 || turn == 2) {
	
	System.out.println("");
	Board.printBoard();
	
	changeTurn();
	}
	}
	}
	
	/**
	 * This method prints which team's turn it is.  
	 * @return String The method returns "White's move: " or "Black's Move: " based on the turn
	 * 
	 */	
	public static String printTurn() {	
	
	if(turn == 1) {
	
	return "White's move: ";
	
	}
	
	else {
	
	return "Black's move: ";
	
	}
	
	}
	
	/**
	 * Helper method to assist with changing the turns
	 * If turn is one, change to two and wise versa
	 */
	public static void changeTurn() {	
	
	if(turn == 1) {
	
	turn = 2;
	
	}
	
	else if(turn == 2) {
	
	turn = 1;
	
	}
	
	}
	
	/**
	 * The resign method declares other team as winner
	 * @return String It will display "Black Wins" or "White Wins"
	 */
	public static String resign() {
	
	if(turn == 1) {
	
	return "Black Wins";
	}
	
	else{
	
	return "White Wins";
	
	}
	
	}
	
	/**
	 * This method checks if the user enters valid coordinates to move the piece
	 * The coordinates must be on the board, and the starting spot must contain a piece
	 * of the current team.
	 * @param oldPosConv First param will take in the old position coordinates as an array
	 * @param newPosConv First param will take in the new position coordinates as an array
	 * @return boolean returns true if the coordinates are valid, false if they are not
	 */
	public static boolean validCoords(int[] oldPosConv, int[] newPosConv) {
	
	//Trying to select an initial coordinate where there isn't any piece present
	if(Board.board[oldPosConv[1]][oldPosConv[0]] == null) {
	return false;
	}
	
	// Trying to select an initial or end piece off of the board
	if(oldPosConv[1] > 7 || oldPosConv[1] < 0 || oldPosConv[0] > 7 || oldPosConv[0] < 0 ||
	newPosConv[1] > 7 || newPosConv[1] < 0 || newPosConv[0] > 7 || newPosConv[0] < 0) {
	return false;
	}
	
	//Trying to move a piece of the opposite team
	if((turn == 1 && Board.board[oldPosConv[1]][oldPosConv[0]].getTeam() == false)
	|| (turn == 1 && Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[newPosConv[1]][newPosConv[0]].getTeam() == true)) {
	return false;
	}
	if((turn == 2 && Board.board[oldPosConv[1]][oldPosConv[0]].getTeam() == true)
	|| (turn == 2 && Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[newPosConv[1]][newPosConv[0]].getTeam() == false)) {
	return false;
	}
	
	return true;
	}
	
	/**
	 * This method find the coordinates of the King piece of specified team on specified board
	 * It takes in a boolean of the team type 
	 *     true = white
	 *     false = black
	 *     
	 * @param board First param is the board itself. It can either be main board, or a test board
	 * @param team Second param takes in what team King you want: true = white false = black 
	 * @return int[] returns the coordinates of King
	 */
	public static int[] findKing(Piece[][] board, boolean team) {
	
	for(int i = 0; i < 8; i++) {
	
	for(int j = 0; j < 8; j++) {
	
	if(board[j][i] != null && board[j][i] instanceof King && team && board[j][i].getTeam() == true) {
	
	int [] myArr = {i,j};
	return myArr;
	}
	
	else if(board[j][i] != null && board[j][i] instanceof King && !team && board[j][i].getTeam() == false) {
	
	int [] myArr = {i,j};
	return myArr;
	
	}
	}
	}
	
	int[] arr = {};
	return arr;
	}
	
	
	/**
	 * ConvertBack takes in the int[] coords and converts it back into a string so the isCheck method can pass it into isValidMove
	 * @param coords First param that takes in the int array cords 
	 * @return String Returns the string version of integer cords
	 */
	public static String convertBack(int[] coords) {
	String input = "";
	
	if(coords[0] == 0) {
	input += "a";
	}
	if(coords[0] == 1) {
	input += "b";
	}
	if(coords[0] == 2) {
	input += "c";
	}
	if(coords[0] == 3) {
	input += "d";
	}
	if(coords[0] == 4) {
	input += "e";
	}
	if(coords[0] == 5) {
	input += "f";
	}
	if(coords[0] == 6) {
	input += "g";
	}
	if(coords[0] == 7) {
	input += "h";
	}

	if(coords[1] == 7) {
	input += "1";
	}
	if(coords[1] == 6) {
	input += "2";
	}
	if(coords[1] == 5) {
	input += "3";
	}
	if(coords[1] == 4) {
	input += "4";
	}
	if(coords[1] == 3) {
	input += "5";
	}
	if(coords[1] == 2) {
	input += "6";
	}
	if(coords[1] == 1) {
	input += "7";
	}
	if(coords[1] == 0) {
	input += "8";
	}

	return input;
	}
	
	
	/**
	 * WhiteInCheck method checks if the white king is in check by any black pieces.
	 * It gets the kings location through findKing method and tests if any black
	 * piece has a valid move to the king.
	 * @return boolean Returns true if king is in check, and false otherwise.
	 */
	public static boolean WhiteInCheck() {
	
	int[] kingCoords = findKing(Board.board, true);
	
	String kingCoordsArr = convertBack(kingCoords);
	
	for(int x = 0; x < 8; x++) {
	
	for(int y = 0; y < 8; y++) {
	
	//Check if any black piece have valid move to King
	if(Board.board[y][x] != null && Board.board[y][x] instanceof Piece && Board.board[y][x].getTeam() == false) {
	
	int [] myArr = {x,y};
	String pieceCoords = convertBack(myArr);

	if(Board.board[y][x].isMoveValid(pieceCoords, kingCoordsArr)) {
	
	return true;
	}

	}
	}
	
	}
	
	return false;
	
	}
	
	/**
	 * This method checks if a White King is in check in the hypothetical copy board
	 * @param board The copied chess board
	 * @return boolean True if the white king is in check, False otherwise
	 */
	public static boolean WhiteInCheck(Piece[][] board) {
	
	int[] kingCoords = findKing(board, true);
	
	if(kingCoords.length == 0) {
		return true;
	}
	
	String kingCoordsArr = convertBack(kingCoords);
	
	for(int x = 0; x < 8; x++) {
	
	for(int y = 0; y < 8; y++) {
	
	//Check if any black piece have valid move to King
	if(board[y][x] != null && board[y][x] instanceof Piece && board[y][x].getTeam() == false) {
	
	int [] myArr = {x,y};	
	String pieceCoords = convertBack(myArr);

	if(board[y][x].isMoveValid(pieceCoords, kingCoordsArr)) {

	return true;
	}
	}
	}
	}	
	
	return false;
	
	}

	/**
	 * BlackInCheck method checks if the black king is in check by any white pieces.
	 * It gets the kings location through findKing method and tests if any white
	 * piece has a valid move to the king.
	 * @return boolean Returns true if king is in check, and false otherwise.
	 **/
	public static boolean BlackInCheck() {
	
	int[] kingCoords = findKing(Board.board, false);
	
	String kingCoordsArr = convertBack(kingCoords);
	
	for(int x = 0; x < 8; x++) {
	
	for(int y = 0; y < 8; y++) {

	if(Board.board[y][x] != null && Board.board[y][x].getTeam() == true) {
	
	int [] myArr = {x,y};
	String pieceCoords = convertBack(myArr);

	if(Board.board[y][x].isMoveValid(pieceCoords, kingCoordsArr)) {
	
	return true;
	}

	}
	}
	}
	
	return false;
	
	}
	
	/**
	 * This method checks if a Black King is in check in the hypothetical copy board
	 * @param board The copied chess board
	 * @return boolean True if the black king is in check, False otherwise
	 */
	public static boolean BlackInCheck(Piece[][] board) {
	
	int[] kingCoords = findKing(board, false);
	
	if(kingCoords.length == 0) {
		return true;
	}
	
	String kingCoordsArr = convertBack(kingCoords);
	
	for(int x = 0; x < 8; x++) {
	
	for(int y = 0; y < 8; y++) {

	if(board[y][x] != null && board[y][x].getTeam() == true) {
	
	int [] myArr = {x,y};
	String pieceCoords = convertBack(myArr);

	if(board[y][x].isMoveValid(pieceCoords, kingCoordsArr)) {

	return true;
	}

	}
	}
	}
	
	return false;
	
	}

	/**
	 * This method checks if the current team has any moves left when in check.
	 * It stores all valid moves any piece on the team can do that take the king out
	 * of check, and stores them in the validMoves arrayList
	 * @param team True if White, False otherwise
	 * @return Returns an ArrayList filled with all the possible moves that take team out of check 
	 */
	public static ArrayList<String> anyMovesLeft(boolean team) {
	
	for(int i = 0; i < 8; i++) {
	
	for(int j = 0; j < 8; j++) {
	
	if(Board.board[j][i] != null && Board.board[j][i].getTeam() == team) {
	
	Piece piece = Board.board[j][i];
	Piece[][] copyArray = copyArray();
	
	for(int x = 0; x < 8; x++) {
	
	for(int y = 0; y < 8; y++) {
	
	int[] start = {i,j};
	int[] end = {x,y};
	
	String startSpot = convertBack(start);
	String endSpot = convertBack(end);

	if(copyArray[j][i].isMoveValid(startSpot, endSpot)  && !selfCheck(startSpot,endSpot)) {
	
	copyArray[j][i] = piece;
	
	if(team) {
	validMoves.add(startSpot + " " + endSpot);
	}
	
	if(!team) {
	validMoves.add(startSpot + " " + endSpot);
	
	}
	}	
	}
	}
	}
	}
	}
	
	return validMoves;
	
	}
	
	/**
	 * This method checks if a move the team is attempting will put it's own King in check
	 * @param start The starting position of the piece
	 * @param end The ending position of the piece
	 * @return boolean True if the move puts it's own king in check, False otherwise
	 */
	public static boolean selfCheck(String start, String end) {
	
	int[] oldPosConv = Board.convertCords(start);
	int[] newPosConv = Board.convertCords(end);
	
	Piece[][] copyArray = copyArray();
	
	if(copyArray[oldPosConv[1]][oldPosConv[0]] != null && copyArray[oldPosConv[1]][oldPosConv[0]].isMoveValid(start, end)) {
	
	copyArray[newPosConv[1]][newPosConv[0]] = copyArray[oldPosConv[1]][oldPosConv[0]];
	copyArray[oldPosConv[1]][oldPosConv[0]]= null;
	
	if(team && WhiteInCheck(copyArray)) {
	return true;
	}
	
	if(!team && BlackInCheck(copyArray)) {
	return true;
	}
	}
	return false;
	}
	
	/**
	 * This method makes a copy of the 2D board array to be used for hypothetical tests
	 * @return Piece[][] Returns a 2D array of type Piece (copy of the Board)
	 */
	public static Piece[][] copyArray() {
	
	Piece[][] copyArray = new Piece[Board.board.length][];
	
	for(int i = 0; i < Board.board.length; i++){
	
	copyArray[i] = new Piece[Board.board[i].length];
	
	for(int j = 0; j < Board.board[i].length; j++){
	
	copyArray[i][j] = Board.board[i][j];
	
	}
	}
	
	return copyArray;
	}

	
//	/**
//	 * This method prints valid moves for any piece. ONLY USED IN TESTING
//	 * @param boolean team True if White, False otherwise
//	 * @param String start The starting cords of a piece
//	 * @return ArrayList<String> Returns an ArrayList filled with all the possible valid moves for selected piece
//	 * 
//	 **/
//	public static ArrayList<String> valid(boolean team, String start) {
//	
//	int[] oldPosConv = Board.convertCords(start);
//	
//	for(int i = 0; i < 8; i++) {
//	
//	for(int j = 0; j < 8; j++) {
//	
//	//System.out.println(start + " " );
//
//	int[] end = {i,j};
//	String endSpot = convertBack(end);
//	
//	//System.out.println(start + " " + endSpot);
//	
//	if(Board.board[oldPosConv[1]][oldPosConv[0]].isMoveValid(start, endSpot) && !selfCheck(start, endSpot)) {
//	
//	System.out.println(start + " " + endSpot);
//	
//	
//	}
//	}
//	}
//	
//	
//	return valid;
//	
//	}
	
}
