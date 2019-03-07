package exercise2;

import java.util.Vector;

public class MessageQueue<E> implements Channel<E> {
	private Vector<E> queue;
	
	public MessageQueue() {
		queue = new Vector<E>();
	}
	
	// This implements a synchronized send
	public synchronized void send(E item) {
		queue.add(item);
	}
	
	// This implements a synchronized receive
	public synchronized E receive() {
		if (queue.size() == 0)
			return null;
		else return queue.remove(0);
	}
}