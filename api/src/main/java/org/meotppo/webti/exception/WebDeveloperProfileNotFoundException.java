package org.meotppo.webti.exception;

import org.meotppo.webti.exception.common.BusinessException;
import org.meotppo.webti.exception.common.ExceptionType;

public class WebDeveloperProfileNotFoundException extends BusinessException {

    public WebDeveloperProfileNotFoundException() {
        super(ExceptionType.WEB_DEVELOPER_PROFILE_NOT_FOUND);
    }
}
