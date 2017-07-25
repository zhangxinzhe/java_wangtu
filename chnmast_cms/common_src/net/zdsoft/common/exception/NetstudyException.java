package net.zdsoft.common.exception;

@SuppressWarnings("serial")
public class NetstudyException extends Exception {

	public NetstudyException() {
		super();
	}

	public NetstudyException(String message) {
		super(message);
	}
	
	public NetstudyException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public NetstudyException(Throwable cause) {
        super(cause);
    }

}
