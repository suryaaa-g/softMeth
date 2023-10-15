package pieces;




import board.Board;
/**
 * This class is an extension of the Piece class and controls the functions of the Rook 
 * The Rook can move unlimited spaces horizontally and vertically 
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0 
 * @since 2022-03-22
 */
public class Rook extends Piece{
	
	// boolean variable that lets us know if castling is possible or not
	boolean isCastling = false;
	
	
	/**
	 * Default constructor for the Rook it creates an Instance of the Rook 
	 * @param type The type of piece it is 
	 * @param team The team the piece belongs to (true for White, false for Black)
	 */
	public Rook(String type, boolean team) {
		super(type, team);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * This method moves the Queen and returns true if the Rook is moved, false otherwise
	 * @param oldPos The original position of the Rook
	 * @param newPos The end position of the Rook
	 * @return boolean true if the Rook moved false otherwise
	 */
	public boolean move(String oldPos, String newPos) {
		
		
		if(isMoveValid(oldPos, newPos)) {
			didMove = true;

			int[] oldPosConv = Board.convertCords(oldPos);		
			int[] newPosConv = Board.convertCords(newPos);

			Board.board[newPosConv[1]][newPosConv[0]] = Board.board[oldPosConv[1]][oldPosConv[0]];

			Board.board[oldPosConv[1]][oldPosConv[0]] = null;
			return true;



		}
		else {
			return false;
		}
		
		
		
		
	}
	
	/**
	 * This method moves the Rook and checks if the Path is clear
	 * @param oldPos The original position of the Rook
	 * @param newPos The end position of the Rook
	 * @return boolean true if the Rook path is clear false otherwise
	 */
	public boolean isPathClear(String oldPos, String newPos) {
		
		
		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		

		int oldRow = oldPosConv[0];
		int newRow = newPosConv[0];
		
		int oldColumn = oldPosConv[1];	
		int newColumn = newPosConv[1];
		

		
		int leftStart = oldRow;
		int leftEnd = newRow;

		
		int topStart = oldColumn;
		int topEnd = newColumn;

		
		if(newRow < oldRow) {
			
			leftStart = newRow;
			leftEnd = oldRow;

		}
	
		
		if(newColumn < oldColumn) {
			
			topStart = newColumn;
			topEnd = oldColumn;

		}
		
//		
//		System.out.println("Left Start: " + leftStart);
//
//		System.out.println("Top Start: " + topStart);

		
		if(oldRow == newRow) {
			
			//System.out.println("Same Row");

			for(int i = topStart + 1; i < topEnd; i++) {
//				System.out.println("X = " + newPosConv[0] + " Y = " + i);
				
				
				if(Board.board[i][newPosConv[0]] instanceof Piece) {
					return false;
				}
			}
			
		}
		
		if(oldColumn == newColumn) {
			
			//System.out.println("Same Column");

			for(int i = leftStart + 1; i < leftEnd; i++) {
				
				//System.out.println("X = " + i + " Y = " + newPosConv[1]);
				if(Board.board[newPosConv[1]][i] instanceof Piece) {
					return false;
				}
			}
			
			
		}
		
		//System.out.println("Got Here");
		return true;
	}
	
	/**
	 * This method checks all the valid moves for the Rook 
	 * @param oldPos The original position of the Rook
	 * @param newPos The end position of the Rook
	 * @return boolean true if the Rook move is valid false otherwise
	 */
	
	public boolean isMoveValid(String oldPos, String newPos) {
		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		
		int originX = oldPosConv[0];
		int originY = oldPosConv[1];
		
		int destX = newPosConv[0];
		int destY = newPosConv[1];
		
		if(Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[newPosConv[1]][newPosConv[0]].getTeam() == Board.board[oldPosConv[1]][oldPosConv[0]].getTeam())
			return false;
		
		
		
		if(originX == destX && originY != destY && isPathClear(oldPos, newPos)) {
			return true;
		}
		
		if(originX != destX && originY == destY && isPathClear(oldPos, newPos)) {
			return true;
		}

		
		return false;
	}
	
	
	
	

	
	
	
	
	
	

}