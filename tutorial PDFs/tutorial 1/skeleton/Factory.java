import java.util.Date;

public class Factory {
	public static void main(String args[]) {
		// create the message queue
		Channel<Date> queue = new MessageQueue<Date>();
		
		// create the producer and consumer threads and pass
		// each thread a reference to the MessageQueue object.
		Thread producer = new Thread(new Producer(queue));
		Thread consumer = new Thread(new Consumer(queue));
		
		producer.start();
		consumer.start();
	}
}