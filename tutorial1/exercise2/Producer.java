package exercise2;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	private Channel<Date> channel;
	
	public Producer(Channel<Date> channel) {
		// TODO Auto-generated constructor stub
		this.channel = channel;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				
				Thread.sleep(500);
				Date date = new Date();
				channel.send(date);
				System.out.println("Sent: "+date.toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
