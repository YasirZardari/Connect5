/*
 * Connect 5 game class.
 */
public class Game {

	final static int width=9;
	final static int height=6;
	public static char[][] board = new char [width][height];
	
	public Game() {
		
	}

	// Initialise board with empty slots
	public static void createBoard(){
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				getBoard()[i][j]='-';
			}
		}

	}
	
	// Return plaintext version of "board"
	public static String printBoard(){
		
		String output = "";
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				output += " ";
				output += getBoard()[j][i];
			}
			output += "\n";
		}
		return output;
	}

	public static char[][] getBoard() {
		return board;
	}


	public static void setBoard(char[][] board) {
		Game.board = board;
	}
	
	// Check if every index of the board is occupied
	public static boolean fullRack() {
		int count = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j] == 'X' || board[i][j] == 'O') {
					count++;
				}
			}

		}
		if (count == 54) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean checkWinX() {

		// Sideways 5 in a row
		int count = 0;
		for (int i = 0; i < height; i++) {
			count = 0;
			for (int j = 0; j < width; j++) {
				if (board[j][i] == 'X') {
					count++;
					if (count == 5) {
						System.out.println("Player 1 wins!");
						return true;
					}
				} else {
					count = 0;
				}
			}
		}

		// Upwards 5 in a row 
		count = 0;
		for (int j = 0; j < width; j++) {
			count = 0;
			for (int i = 0; i < height; i++) {
				if (board[j][i] == 'X') {
					count++;
					if (count == 5) {
						System.out.println("Player 1 wins!");
						return true;
					}
				} else {
					count = 0;
				}
			}
		}

		// Descending diagonal
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 2; i++) {
				if (board[j][i] == 'X' && board[j][i] == board[j + 1][i + 1] && board[j][i] == board[j + 2][i + 2]
						&& board[j][i] == board[j + 3][i + 3] && board[j][i] == board[j + 4][i + 4]) {
					System.out.println("Player 1 wins!");
					return true;
				}
			}
		}

		// Ascending diagonal
		for (int j = 5; j > 3; j--) {
			for (int i = 0; i < 5; i++) {
				if (board[i][j] == 'X' && board[i][j] == board[i + 1][j - 1] && board[i][j] == board[i + 2][j - 2]
						&& board[i][j] == board[i + 3][j - 3] && board[i][j] == board[i + 4][j - 4]) {
					System.out.println("Player 1 wins!");
					return true;
				}
			}
		}

		return false;

	}

	public static boolean checkWinO() {

		int count = 0;
		for (int i = 0; i < height; i++) {
			count = 0;
			for (int j = 0; j < width; j++) {
				if (board[j][i] == 'O') {
					count++;
					if (count == 5) {
						System.out.println("Player 2 wins!");
						return true;
					}
				} else {
					count = 0;
				}
			}
		}

		count = 0;
		for (int j = 0; j < width; j++) {
			count = 0;
			for (int i = 0; i < height; i++) {
				if (board[j][i] == 'O') {
					count++;
					if (count == 5) {
						System.out.println("Player 2 wins!");
						return true;
					}
				} else {
					count = 0;
				}
			}
		}

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 2; i++) {
				if (board[j][i] == 'O' && board[j][i] == board[j + 1][i + 1] && board[j][i] == board[j + 2][i + 2]
						&& board[j][i] == board[j + 3][i + 3] && board[j][i] == board[j + 4][i + 4]) {
					System.out.println("Player 2 wins!");
					return true;
				}
			}
		}

		for (int j = 5; j > 3; j--) {
			for (int i = 0; i < 5; i++) {
				if (board[i][j] == 'O' && board[i][j] == board[i + 1][j - 1] && board[i][j] == board[i + 2][j - 2]
						&& board[i][j] == board[i + 3][j - 3] && board[i][j] == board[i + 4][j - 4]) {
					System.out.println("Player 2 wins!");
					return true;
				}
			}
		}

		return false;

	}
}
