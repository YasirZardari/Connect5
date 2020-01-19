import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author Yasir Zardari
 *
 */
class GameTest {
	
	/*
	 * In the below tests, you can enter System.out.println(gametest.printBoard()) before assertions to view connect 5 board
	 */
	  
	@Test
	void testVerticalWin() {
		
		Game gametest = new Game();
		gametest.createBoard();
		gametest.board[0][1] = 'X';
		gametest.board[0][2] = 'X';
		gametest.board[0][3] = 'X';
		gametest.board[0][4] = 'X';
		gametest.board[0][5] = 'X';

		assertTrue(Game.checkWinX());
		
		gametest.createBoard();
		gametest.board[0][1] = 'X';
		gametest.board[0][2] = 'X';
		gametest.board[0][3] = 'X';
		gametest.board[0][4] = 'X';

		assertFalse(Game.checkWinX());
	}
	
	@Test
	void testHorizontalWin() {
		
		Game gametest = new Game();
		gametest.createBoard();
		gametest.board[0][5] = 'X';
		gametest.board[1][5] = 'X';
		gametest.board[2][5] = 'X';
		gametest.board[3][5] = 'X';
		gametest.board[4][5] = 'X';
		
		assertTrue(Game.checkWinX());
	}
	
	@Test
	void testDiagonalAscendingWin() {
		
		Game gametest = new Game();
		gametest.createBoard();
		gametest.board[0][5] = 'X';
		gametest.board[1][4] = 'X';
		gametest.board[2][3] = 'X';
		gametest.board[3][2] = 'X';
		gametest.board[4][1] = 'X';
		
		assertTrue(Game.checkWinX());
	}
	
	@Test
	void testDiagonalDescendingWin() {
		
		Game gametest = new Game();
		gametest.createBoard();
		gametest.board[0][0] = 'X';
		gametest.board[1][1] = 'X';
		gametest.board[2][2] = 'X';
		gametest.board[3][3] = 'X';
		gametest.board[4][4] = 'X';
		
		assertTrue(Game.checkWinX());
	}

}
