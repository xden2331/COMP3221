package tasks;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

class BlockchainTest {

	@Test
	void testEmptyPool() {
		Blockchain blockchain = new Blockchain();
		System.out.println(blockchain.toString());
	}
	
	@Test
	void testAddOneTx() throws NoSuchAlgorithmException, IOException {
		Blockchain blockchain = new Blockchain();
		blockchain.addTransaction("tx|unik2333|Testing add function");
		System.out.println(blockchain.toString());
	}
	
	@Test
	void testAddThreeTx() throws NoSuchAlgorithmException, IOException{
		Blockchain blockchain = new Blockchain();
		blockchain.addTransaction("tx|test0000|1");
		blockchain.addTransaction("tx|test0000|2");
		blockchain.addTransaction("tx|test0000|3");
		System.out.println(blockchain.toString());
		blockchain.addTransaction("tx|test0000|4");
		System.out.println(blockchain.toString());
	}

	@Test
	void testMessageValidation() throws NoSuchAlgorithmException, IOException{
		Blockchain blockchain = new Blockchain();
		int state = blockchain.addTransaction("tx|invalidSender|test!");
		assertEquals(0, state);
		
		state = blockchain.addTransaction("tx|test0000|1|2");
		assertEquals(0, state);
		
		String exceedStr = "";
		for(int i=0; i<100; ++i) {exceedStr += "a";}
		state = blockchain.addTransaction("tx|test0000|"+exceedStr);
		assertEquals(0, state);
		System.out.println(blockchain.toString());
		
		state = blockchain.addTransaction("tx|test0000|");
	}
	
}
