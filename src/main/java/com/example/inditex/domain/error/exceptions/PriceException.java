package com.example.inditex.domain.error.exceptions;


import com.example.inditex.domain.enums.ErrorDefinitionEnum;

import java.util.Map;

public class PriceException extends BaseException {
    public PriceException(ErrorDefinitionEnum error, Map<String, String> messageValues, int httpStatus, Throwable cause) {
        super(error, messageValues, httpStatus, cause);
    }

    public PriceException(ErrorDefinitionEnum error, Map<String, String> messageValues, int httpStatus) {
        super(error, messageValues, httpStatus);
    }

    public PriceException(ErrorDefinitionEnum error, String message, int httpStatus) {
        super(error, message, httpStatus);
    }
}
