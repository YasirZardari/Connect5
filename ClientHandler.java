import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
	
    Socket socket;
    PrintWriter out;
    BufferedReader in;    
    char token;
    String name;
    ClientHandler opponent = null;
    boolean gameStart = false;
    
    int playerID;
    char opponentID;
    

	public ClientHandler(Socket socket, char token) {
		this.socket = socket;
		this.token = token;
	}
	
	public void setOpponent(ClientHandler opponent) {
		this.opponent = opponent;
	}
	
	public void run() {
		try {
			
		    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));   
		    out = new PrintWriter(this.socket.getOutputStream(), true);
			
		    // Read name of client
		    out.println(String.valueOf(this.token));
		    name = in.readLine();
		    Server.numberOfPlayer++;
		    
		    // Wait for other player to connect
		    if(Server.numberOfPlayer < 2) {
		    	out.println("Waiting for other player");
			    while(Server.numberOfPlayer < 2) {
			    	
			    }
		    }
		    out.println("All players connected");
		    
		    // Send message indicating first move or not
		    if(this.token == 'X') {
		    	out.println(Game.printBoard());
		    	out.println("Your move " + name + ", please enter a column number (1-9)");
		    	this.opponentID = 'O';
		    }else {
		    	out.println(Game.printBoard());
		    	out.println("Waiting for opponent");
		    	this.opponentID = 'X';
		    }
		    
		    /*
		     * Continuously read input and alter connect 5 board before sending it back to the
		     * client. Make checks for a game winning move as well if the rack is full and send messages
		     * to client indicating these.
		     */
		    while(true) {
		    	
		    	if(this.token == 'X') {
		    		
		    		String input = in.readLine();		
		    		try {
		    			int column = Integer.parseInt(input) - 1;
		    			while (Game.getBoard()[column][Server.row] == 'X' || Game.getBoard()[column][Server.row] == 'O') {
		    				Server.row--;
		    			}	
		    			Game.board[column][Server.row] = 'X';
		    			
		    			Server.row = 5;
			    		out.println(Game.printBoard());
			    		
			    		// Full board check
			    		if(Game.fullRack()) {
		    				out.println("The game is over, it's a draw");
			    			Server.player2.sendMessage("The game is over, it's a draw");
			    			break;
			    		}
			    		
			    		if(Game.checkWinX()) {
			    			out.println("The game is over, " + name + " is the winner!");
			    			Server.player2.sendMessage(Game.printBoard());
			    			Server.player2.sendMessage("The game is over, the opponent " + name + " is the winner!");
			    			break;
			    		}
			    		else if(Game.checkWinO()) {
			    			out.println("The game is over, the opponent " + Server.player2.name + " is the winner!");
			    			Server.player2.sendMessage(Game.printBoard());
			    			Server.player2.sendMessage("The game is over, " + Server.player2.name + " is the winner!");
			    			break;
			    		}
			    		
			    		out.println("Waiting for opponent");
			    		if(Server.player2 != null) {
			    			Server.player2.sendMessage(Game.printBoard());
			    			Server.player2.sendMessage("Your move " + Server.player2.name + ", please enter a column number (1-9)");
			    		}
		    		} catch(NumberFormatException nfe) {
		    		}		
		    	}
		    	else {
		    		
		    		String input = in.readLine();
		    		try {
				    	int column = Integer.parseInt(input) - 1;
				    	while (Game.getBoard()[column][Server.row] == 'X' || Game.getBoard()[column][Server.row] == 'O') {
		    				Server.row--;
		    			}	
			    		Game.board[column][Server.row] = 'O';
			    		
			    		Server.row = 5;
				    	out.println(Game.printBoard());
				    	
				    	if(Game.fullRack()) {
		    				out.println("The game is over, it's a draw");
			    			Server.player1.sendMessage("The game is over, it's a draw");
			    			break;
				    	}
				    	
				    	if(Game.checkWinX()) {
			    			out.println("The game is over, the opponent" + Server.player1.name + " is the winner!");
			    			Server.player1.sendMessage(Game.printBoard());
			    			Server.player1.sendMessage("The game is over, " + Server.player1.name + " is the winner!");
			    			break;
			    		}
			    		else if(Game.checkWinO()) {
			    			out.println("The game is over, " + name + " is the winner!");
			    			Server.player1.sendMessage(Game.printBoard());
			    			Server.player1.sendMessage("The game is over, the opponent " + name + " is the winner!");
			    			break;
			    		}
				    	
				   		out.println("Waiting for opponent");
				   		if(Server.player1 != null) {
				   			Server.player1.sendMessage(Game.printBoard());
				   			Server.player1.sendMessage("Your move " + Server.player1.name + ", please enter a column number (1-9)");
			    		}
		    		} catch(NumberFormatException nfe) {
		    	    }			    		
		    		
		    	}
		    		
		    }
		    
		} catch (IOException e) {
			if(this.token == 'X') {
    			Server.player2.sendMessage("The other player has disconnected, the game is over");
			}
			else {
				Server.player1.sendMessage("The other player has disconnected, the game is over");
			}
			
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
    }
	
	// Send message to client
	public void sendMessage(String s) {
		out.println(s);
	}

	
}
