package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
 
public class BlockchainServer {
	private Blockchain blockchain;
	public BlockchainServer() { 
		blockchain = new Blockchain(); 
	}
	// getters and setters
	public void setBlockchain(Blockchain blockchain) { 
		this.blockchain = blockchain; 
	} 
	
	public Blockchain getBlockchain() { 
		return blockchain; 
	}
	
	public static void main(String[] args) { 
		if (args.length != 1) {
		return; 
		}
		int portNumber = Integer.parseInt(args[0]); 
		BlockchainServer bcs = new BlockchainServer();
		try {
			ServerSocket socket = new ServerSocket(portNumber);
			while(true) {
				Socket client = socket.accept();
				OutputStream clientOutputStream = client.getOutputStream();
				InputStream clientInputStream = client.getInputStream();
				bcs.serverHandler(clientInputStream, clientOutputStream);
				clientOutputStream.close();
				clientInputStream.close();
				client.close();
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	// implement your code here.
	}
	
	public void serverHandler(InputStream clientInputStream, OutputStream clientOutputStream) {
		BufferedReader inputReader = new BufferedReader( new InputStreamReader(clientInputStream));
		PrintWriter outWriter = new PrintWriter(clientOutputStream, true);
		// implement your code here.
		try {
			String line;
			while ((line = inputReader.readLine()) != null) {
				StringBuffer reply = new StringBuffer();
				
				if (line.contains("tx")) {
					int addState = blockchain.addTransaction(line);
					if (addState == 0) {
						reply.append("Rejected\n");
					}else {
						reply.append("Accepted\n");
					}
				}else if (line.compareTo("pb") == 0) {
					reply.append(blockchain.toString());
				}else if (line.compareTo("cc") == 0) {
					return;
				}else {
					reply.append("Error\n");
				}
				outWriter.println(reply.toString());
				outWriter.println("EOF");
				outWriter.flush();
			}
		} catch (IOException e) {
			System.err.println(e);
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			return;
		}finally {
			outWriter.flush();
			outWriter.close();
		}
	}
	// implement helper functions here if you need any.
}
