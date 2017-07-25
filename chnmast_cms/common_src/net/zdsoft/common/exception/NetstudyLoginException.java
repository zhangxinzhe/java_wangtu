package net.zdsoft.common.exception;

public class NetstudyLoginException extends Exception{
    private static final long serialVersionUID = -5670817133477113824L;

    public NetstudyLoginException(String message) {
        super(message);
    }
    
    public NetstudyLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
