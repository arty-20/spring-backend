/**
 * (C) 2017 Agilysys NV, LLC.  All Rights Reserved.  Confidential Information of Agilysys NV, LLC.
 */
package com.sales.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class OperationNotPermitException extends RuntimeException {
    public OperationNotPermitException() {
        super();
    }

    public OperationNotPermitException(String message) {
        super(message);
    }

    public OperationNotPermitException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotPermitException(Throwable cause) {
        super(cause);
    }

    protected OperationNotPermitException(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
