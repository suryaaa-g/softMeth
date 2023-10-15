package pieces;


import board.Board;


/**
 * The Queen class is an extension of Piece and controls the movement of the Queen
 * The Queen can move in any direction and any number of spaces 
 * @author Afaq Qamar (aq101)
 * @author Suryateja Gandhesiri (sg1571)
 * @version 1.0 
 * @since 2022-03-22
 */
public class Queen extends Piece{
	/**
	 * Default constructor for the Queen it creates an Instance of the Queen 
	 * @param type The type of piece it is 
	 * @param team The team the piece belongs to (true for White, false for Black)
	 */
	
	public Queen(String type, boolean team) {
		super(type, team);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method moves the Queen and returns true if the Queen is moved, false otherwise
	 * @param  oldPos The original position of the Queen
	 * @param  newPos The end position of the Queen
	 * @return boolean true if the Queen moved false otherwise 
	 */
	
	public boolean move(String oldPos, String newPos) {
		
		didMove = true;
		
		if(isMoveValid(oldPos, newPos)) {

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
	 * This method makes sure if the path of the Queen is clear or not
	 * @param oldPos The old position of the Queen
	 * @param newPos The new position of the Queen
	 * @return boolean true if the path is clear, false otherwise
	 */
	public boolean isPathClear(String oldPos, String newPos) {
	
		return true;
	}
	
	/**
	 * This method moves the Queen and checks if the Path is clear horizontally 
	 * @param oldPos The original position of the Queen
	 * @param newPos The end position of the Queen
	 * @return boolean true if the Queen horizontal path is clear false otherwise
	 */
	public boolean isPathClearHorizontal(String oldPos, String newPos) {
		
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
		
		if(oldColumn == newColumn) {
			
			//System.out.println("Same Column");

			for(int i = leftStart + 1; i < leftEnd; i++) {
				
				//System.out.println("X = " + i + " Y = " + newPosConv[1]);
				if(Board.board[newPosConv[1]][i] instanceof Piece) {
					return false;
				}
			}
			
			
		}
		
		
		return true;
	}
	
	/**
	 * This method moves the Queen and checks if the Path is clear vertical 
	 * @param oldPos The original position of the Queen
	 * @param newPos The end position of the Queen
	 * @return boolean true if the Queen vertical path is clear false otherwise 
	 */
	public boolean isPathClearVertical(String oldPos, String newPos) {
		
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
		
		
		if(oldRow == newRow) {
			
			//System.out.println("Same Row");

			for(int i = topStart + 1; i < topEnd; i++) {
//				System.out.println("X = " + newPosConv[0] + " Y = " + i);
				
				
				if(Board.board[i][newPosConv[0]] instanceof Piece) {
					return false;
				}
			}
			
		}
		
		return true;
	}
	
	/**
	 * This method moves the Queen and checks if the Path is clear vertical 
	 * @param oldPos The original position of the Queen
	 * @param newPos The end position of the Queen
	 * @return boolean true if the Queen diagonal path is clear false otherwise 
	 */
	public boolean isPathClearDiagonals(String oldPos, String newPos) {
		
		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		
		boolean right, up;
		
		// checks whether or not we are going right or left
		if(oldPosConv[0] < newPosConv[0]) {
			right = true;
		}
		else {
			right = false;
		}
		
		// checks whether or not we are going up or down
		if(oldPosConv[1] < newPosConv[1]) {
			up = false;
		}
		else {
			up = true;
		}

		int oldRow = oldPosConv[0];
		int newRow = newPosConv[0];
		
		int oldColumn = oldPosConv[1];	
		int newColumn = newPosConv[1];
		

		//Up And Left
		
		if(up && right) {
			
			//System.out.println("Up and Right");
			
			int y = oldColumn -1;
			
			for(int x = oldRow + 1; x <= newRow -1; x++) {
				
				if(Board.board[y][x] instanceof Piece) {
					return false;
				}
				
				y--;
				
			}
		}
		
		//Up and Left
		if(up && !right) {
			
			//System.out.println("Up and Left");
			
			int y = oldColumn -1;
			
			for(int x = oldRow -1; x >= newRow + 1; x--) {
				
				if(Board.board[y][x] instanceof Piece) {
					return false;
				}
				
				y--;
				
			}
		}
		
		//Down and Left
		if(!up && !right) {
			
			//System.out.println("Down and Left");
			
			int y = oldColumn + 1;
			
			for(int x = oldRow - 1; x >= newRow + 1; x--) {
				
				if(Board.board[y][x] instanceof Piece) {
					return false;
				}
				
				y++;
				
			}
		}
		
		//Down and Right
		if(!up && right) {
			
			//System.out.println("Down and Right");
			
			int y = oldColumn + 1;
			
			
			for(int x = oldRow + 1; x <= newRow - 1; x++) {
				
				//System.out.println("X: " + x + " Y: " + y);
				if(Board.board[y][x] instanceof Piece) {
					return false;
				}
				
				y++;
				
			}
		}
		
		return true;
	}
	
	/**
	 * This method checks all the valid moves for the Queen 
	 * @param oldPos The original position of the Queen
	 * @param newPos The end position of the Queen
	 * @return boolean true if the Queen move is valid false otherwise
	 */
	
	public boolean isMoveValid(String oldPos, String newPos) {
		
		
		// ignore the code below - testing to see if this works with just rook and bishop moves (it doesnt)
		int[] oldPosConv = Board.convertCords(oldPos);
		int[] newPosConv = Board.convertCords(newPos);
		
		int originX = oldPosConv[0];
		int originY = oldPosConv[1];
		
		int destX = newPosConv[0];
		int destY = newPosConv[1];
		
		if(Board.board[newPosConv[1]][newPosConv[0]] != null && Board.board[newPosConv[1]][newPosConv[0]].getTeam() == Board.board[oldPosConv[1]][oldPosConv[0]].getTeam())
			return false;
		
		
		// Diagonals
		if(Math.abs(originX - destX) == Math.abs(originY - destY) && isPathClearDiagonals(oldPos, newPos)){
			
			return true;
			
		}
		
		// Verticals
		if(originX == destX && originY != destY && isPathClearVertical(oldPos, newPos)) {
			return true;
		}
		
		
		//Horizontal
		if(originX != destX && originY == destY && isPathClearHorizontal(oldPos, newPos)) {
			return true;
		}
		
		
		
		return false;
	}

}