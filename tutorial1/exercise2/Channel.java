package exercise2;

interface Channel<E> {
	public void send(E item);
	public E receive();
}
