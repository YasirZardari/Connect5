import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private static String playerID;
	static boolean gameInSession = false;

	public static void main(String[] args) throws UnknownHostException, IOException {

        Socket socket = new Socket("localhost", 1234); 
        Scanner input = new Scanner(System.in);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        // Read which player the client is connected as
        playerID = in.readLine();
        System.out.println("Connected as token " + playerID);

        
        System.out.println("Please enter your name:");
        String name = input.next();
        out.println(name);
                
        /*
         * Threads to continuously read or write any messages involving this client connection
         */
        
     // sendMessage thread 
        Thread sendMessage = new Thread(new Runnable()  
        { 
            @Override
            public void run() { 
                while (true) { 
  
                    // read the message to deliver. 
                    String msg = input.nextLine(); 
                                       
                    // write on the output stream 
                    out.println(msg); 
                   
                } 
            } 
        }); 
          
        // readMessage thread 
        Thread readMessage = new Thread(new Runnable()  
        { 
            @Override
            public void run() { 
  
                while (true) { 
                    try { 
                        // read the message sent to this client 
                        String msg = in.readLine(); 
                        System.out.println(msg); 
                        
                    } catch (IOException e) { 
  
                        e.printStackTrace(); 
                    } 
                } 
            } 
        }); 
  
        sendMessage.start(); 
        readMessage.start();
        
	}

}
