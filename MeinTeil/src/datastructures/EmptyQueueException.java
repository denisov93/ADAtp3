package datastructures;

public class EmptyQueueException extends RuntimeException {

	static final long serialVersionUID = 0L;
	
	public EmptyQueueException() {
		super();
	}
	
	public EmptyQueueException(String error) {
		super(error);
	}
}
