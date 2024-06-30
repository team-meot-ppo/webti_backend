package org.meotppo.webti.response.exception.common;

public class WebDeveloperProfileNotFoundException extends BusinessException {

    public WebDeveloperProfileNotFoundException() {
        super(ExceptionType.WEB_DEVELOPER_PROFILE_NOT_FOUND);
    }
}
