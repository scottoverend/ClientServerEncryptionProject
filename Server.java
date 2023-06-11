import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Server {
private Socket socket = null;
private ServerSocket serversocket = null;
private Server server = null;
private DataInputStream input = null;
private DataOutputStream output = null;
private PrintWriter writer = null;
	
	
public Server(int port) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
	
	try {
	
	serversocket = new ServerSocket(port);
	
	System.out.println("Server Ready, Awaiting Client");
	
	socket = serversocket.accept();
	
	input = new DataInputStream(socket.getInputStream());
	
	output = new DataOutputStream(socket.getOutputStream());
	
	writer = new PrintWriter(socket.getOutputStream(),true);
		
	}catch(IOException e) {
	System.out.println(e);	
	}

String lin = "";
	
while(!lin.equals("over")) {
	try {
	String in = input.readLine();
	
	System.out.println(in + "\n");
	
	String outer = in;
	
	System.out.println(outer);
	
	writer.println(outer);
		
		
	}catch(IOException e) {
		System.out.println(e);
	}
}
}

public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

Server server = new Server(5000);

}	
}
