package org.meotppo.webti.domain.entity.type;

public enum PersonalityType {
    EXTROVERSION("E"), // 외향형
    INTROVERSION("I"), // 내향형
    SENSING("S"), // 감각형
    INTUITION("N"), // 직관형
    THINKING("T"), // 사고형
    FEELING("F"), // 감정형
    JUDGING("J"), // 판단형
    PERCEIVING("P"); // 인식형

    private final String code;

    PersonalityType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}