package net.zdsoft.chnmaster.exception;

import net.zdsoft.common.exception.NetstudyException;

/**
 * 没有权限的总的异常
 * 
 * @author fangb
 *
 */
@SuppressWarnings("serial")
public class NoPrivacyException extends NetstudyException {

	private static String NO_PRIVACY_MSG = "没有权限访问该内容";
	
	public NoPrivacyException() {
		this(NO_PRIVACY_MSG);
	}

	public NoPrivacyException(String message) {
		super(message);
	}
	
	public NoPrivacyException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public NoPrivacyException(Throwable cause) {
        this(NO_PRIVACY_MSG,cause);
    }
}
