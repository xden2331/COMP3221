package src;

import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BlockchainClient {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        if (args.length != 2) {
            return;
        }
        String serverName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        BlockchainClient bcc = new BlockchainClient();
        
        try {
			Socket socket = new Socket(serverName, portNumber);
			socket.setSoTimeout(1000);
			InputStream serverInputStream = socket.getInputStream();
			OutputStream serverOutputStream = socket.getOutputStream();
			bcc.clientHandler(serverInputStream, serverOutputStream);
			
			serverInputStream.close();
			serverOutputStream.close();
			socket.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}

        // TODO: implement your code here.
    }

    public void clientHandler(InputStream serverInputStream, OutputStream serverOutputStream) throws NoSuchAlgorithmException {
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(serverInputStream));
        PrintWriter outWriter = new PrintWriter(serverOutputStream, true);

        Scanner sc = new Scanner(System.in);
        try {
	        	while (sc.hasNextLine()) {
	                // TODO: implement your code here
	            		String command = sc.nextLine();
	            		outWriter.print(command+'\n');
	            		outWriter.flush();
	            		if (command.compareTo("cc") == 0) {
							return;
						}
	            		String line;
	            		while ((line = inputReader.readLine()) != null) {
	            			if(line.compareTo("EOF") != 0) {
	            				System.out.println(line);
	            			}else {
								break;
							}
	            		}
	            }
		} catch (IOException e) {
			// TODO: handle exception
		}
    }

    // implement helper functions here if you need any.
}