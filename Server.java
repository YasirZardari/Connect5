import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

	public static volatile int column = 1;
	public static volatile int row;
	
	public static volatile int numberOfPlayer = 0;
	
    public static volatile ClientHandler player1;
    public static volatile ClientHandler player2;

    public static void main(String args[]) throws IOException {

    	// Create server socket allowing clients to connect as well as initialising game board
        ServerSocket server = new ServerSocket(1234);         
        row = 5;
        Game.createBoard();
        
        // Player 1 Client connects, thread is created for each client
    	Socket socket1 = server.accept();
	    System.out.println("Player 1 Connected!");
	    player1 = new ClientHandler(socket1,'X');
	    player1.start();   

        // Player 2 Client connects
	    Socket socket2 = server.accept();
	    player2 = new ClientHandler(socket2,'O');   
	    System.out.println("Player 2 Connected!");	
	    player1.setOpponent(player2);
	    player2.setOpponent(player1);
	    player2.start();

	    	 
    	}
    	


}
    
   



