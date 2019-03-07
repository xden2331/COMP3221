package exercise2;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

	private Channel<Date> channel;
	
	public Consumer(Channel<Date> channel) {
		// TODO Auto-generated constructor stub
		this.channel = channel;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				Thread.sleep(500);
				Date date = channel.receive();
				if(date == null) {
					continue;
				}else {
					System.out.println("Received: " + date.toString());
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
