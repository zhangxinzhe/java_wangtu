/* 
 * @(#)RemoteException.java    Created on 2011-2-16
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: RemoteException.java 12419 2011-02-16 09:59:49Z yeq $
 */
package net.zdsoft.chnmaster.exception;

public class RemoteException extends Exception {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7284883316587326931L;

    public RemoteException(String message) {
        super(message);
    }

}
