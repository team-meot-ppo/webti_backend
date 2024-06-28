package org.meotppo.webti.response.exception.common;

public class WebDeveloperProfileNotFoundException extends MemberBusinessException {

    public WebDeveloperProfileNotFoundException() {
        super(ExceptionType.MEMBER_NOT_FOUND);
    }
}
