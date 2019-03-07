package tasks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class Blockchain {

	private Block head;
	private ArrayList<Transaction> pool;
	private int length;

	private final int poolLimit = 3;
	public Blockchain() {
		pool = new ArrayList<>();
		head = null;
		length = 0;
	}

	// getters and setters
	public Block getHead() { return head; }
	public ArrayList<Transaction> getPool() { return pool; }
	public int getLength() { return length; }
	public void setHead(Block head) { this.head = head; }
	public void setPool(ArrayList<Transaction> pool) { this.pool = pool; }
	public void setLength(int length) { this.length = length; }

	// add a transaction
	public int addTransaction(String txString) throws NoSuchAlgorithmException, IOException {
		// implement you code here.
		String[] parts = txString.split("[|]");
		if(!isValid(parts)) {
			return 0;
		}
		Transaction transaction = new Transaction(parts[1], parts[2]);
		if(pool.size() < 2) {
			pool.add(transaction);
			return 1;
		}
		// pool is full
		pool.add(transaction);
		Block block = new Block(head);
		block.setTransactions(pool);
		pool = new ArrayList<>();
		++length;
		head = block;
		return 2;
	}

	public String toString() {
		String cutOffRule = new String(new char[81]).replace("\0", "-") + "\n";
		String poolString = "";
		for (Transaction tx : pool) {
			poolString += tx.toString(); 
			}
		String blockString = ""; Block bl = head;
		while (bl != null) {
		blockString += bl.toString();
		bl = bl.getPreviousBlock(); }
		return "Pool:\n" + cutOffRule
		+ poolString
		+ cutOffRule
		+ blockString; 
	}
		// implement helper functions here if you need any.
	
	private boolean isValid(String[] parts) {
		if(parts.length != 3 
				|| parts[0].compareTo("tx") != 0 
				|| !parts[1].matches("[a-z]{4}[0-9]{4}")
				|| parts[2].length() >70) {
			return false;
		}
		return true;
	}
	
}