import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Client {

private Socket socket = null;
private DataInputStream input = null;
private DataOutputStream output = null;
private PrintWriter writer = null;
private Cipher cipher = null;
private DataInputStream sockinput = null;

public Client(String url, int port) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	




	
	
try { 
		
	socket = new Socket(url, port);
	
	System.out.println("Accepting The Server");
	
	input = new DataInputStream(System.in);
	
	sockinput = new DataInputStream(socket.getInputStream());
	
	output = new DataOutputStream(socket.getOutputStream());

	
	writer = new PrintWriter(socket.getOutputStream(), true);
	
	
	}catch (IOException e) {
	System.out.println(e);	
	}



String line = " ";

while(!line.equals("over")) {
	try {
		
	String plaintext = input.readLine();
 
	//plaintext = "hello";
	
	
	//Scanner fetch = Scanner(input);
	
	SecretKey i = Client.generateKey(128); 
	
	String ciphertext = Client.Encrypt(i, plaintext);
	
	//System.out.println(ciphertext);
	
	writer.println(ciphertext);
	
	String in = sockinput.readLine();
	
	System.out.println("The Server returned the encrypted string:" + in + "\n");
	System.out.println("");
	String decryptedstring = Client.Decrypt(i, ciphertext);
	System.out.println("");
	System.out.println("The decrypted string is:" + decryptedstring);
	
		
	}catch(IOException e) {
		System.out.println(e);
	}
}
}

//public Client(String aes) {
	
public static String Encrypt(SecretKey i, String plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	
	Cipher c = Cipher.getInstance("AES");
	
	c.init(Cipher.ENCRYPT_MODE, i);
	
	byte[] ciphertext = c.doFinal(plaintext.getBytes());
	
	return Base64.getEncoder().encodeToString(ciphertext);
	
}

public static String Decrypt(Key secretkey, String ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	
	Cipher c = Cipher.getInstance("AES");
	
	c.init(Cipher.DECRYPT_MODE, secretkey);
	
	byte[] plaintext = c.doFinal(Base64.getDecoder().decode(ciphertext));
	
	return new String(plaintext);
	
}
	
public static SecretKey generateKey(int i) throws NoSuchAlgorithmException {
	
	KeyGenerator keygen = KeyGenerator.getInstance("AES");
	
	SecureRandom secure = new SecureRandom();
	
	keygen.init(secure);
	
	SecretKey key = keygen.generateKey();
	
	return key;
	
	
}

public static void main(String args[]) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			
	
Client client = new Client("127.0.0.1", 5000);
	
}	
}

