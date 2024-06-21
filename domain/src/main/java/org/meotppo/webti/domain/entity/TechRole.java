package org.meotppo.webti.domain.entity;

import lombok.Getter;

@Getter
public enum TechRole {
    BACKEND_TRAFFIC_CONTROLLER("트래픽 통제관 백엔드"),
    FRONTEND_UI_MAGICIAN("UI 마술사 프론트엔드");

    private final String description;

    TechRole(String description) {
        this.description = description;
    }

}
