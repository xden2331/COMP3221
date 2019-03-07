package exercise1;

public class Exercise1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread mThread = new Thread(new MyThread());
		mThread.start();
	}

}
