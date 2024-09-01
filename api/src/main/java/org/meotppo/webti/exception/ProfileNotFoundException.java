package org.meotppo.webti.exception;

import org.meotppo.webti.exception.common.BusinessException;
import org.meotppo.webti.exception.common.ExceptionType;

public class ProfileNotFoundException extends BusinessException {

    public ProfileNotFoundException() {
        super(ExceptionType.PROFILE_NOT_FOUND_EXCEPTION);
    }
}
