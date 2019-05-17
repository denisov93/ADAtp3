package datastructures;

public class InvalidKeyException extends Exception {

	static final long serialVersionUID = 0L;
	
	public InvalidKeyException() {
		super();
	}
	
	public InvalidKeyException(String error) {
		super(error);
	}
	
}
