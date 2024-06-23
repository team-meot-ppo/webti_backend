package org.meotppo.webti.domain.entity;

import lombok.Getter;

@Getter
public enum TechRole { // TODO. 국제화
    BACKEND_DATA_MANAGER("백엔드 개발자", "ISTJ", "철저한 데이터 관리형 백엔드 개발자", "데이터의 정확성과 안정성을 최우선으로 하는 당신은 백엔드 개발의 수호자! 체계적이고 신뢰할 수 있는 방식으로 데이터를 관리하며, 버그는 당신에게 있어 전설 속 이야기일 뿐입니다. 당신의 코드는 그야말로 철옹성!"),
    BACKEND_SYSTEM_DESIGNER("백엔드 개발자", "INTJ", "전략적 시스템 설계형 백엔드 개발자", "소프트웨어 개발의 체스 그랜드마스터인 당신은, 몇 수 앞을 내다보는 전략가! 시스템 아키텍처를 전략적으로 설계하고, 최적의 경로를 찾아내는 당신의 코드는 예술 작품과 같습니다. 복잡한 문제도 순식간에 해결하는 능력자!"),
    BACKEND_PROBLEM_SOLVER("백엔드 개발자", "ISTP", "효율적인 문제 해결형 백엔드 개발자", "프로그래밍 세계의 맥가이버, 당신! 실용적이고 논리적인 사고로 문제를 해결하며, 시스템 최적화와 효율적인 코드 작성에 탁월한 재능을 보입니다. 종이 클립 하나로도 문제를 해결할 수 있을 것 같은 당신의 능력은 그야말로 경이롭습니다."),
    BACKEND_ALGORITHM_EXPERT("백엔드 개발자", "INTP", "혁신적인 알고리즘 전문가 백엔드 개발자", "코드의 아인슈타인인 당신은, 복잡한 알고리즘과 데이터 구조 설계에서 빛을 발합니다. 끊임없이 새로운 아이디어가 떠오르는 당신의 머릿속은 혁신으로 가득 차 있으며, 논리적이고 분석적인 사고로 누구도 해결하지 못하는 문제를 해결합니다."),
    FRONTEND_UX_DESIGNER("프론트엔드 개발자", "ISFJ", "섬세한 사용자 경험 디자이너형 프론트엔드 개발자", "사용자의 마음을 읽는 텔레파시 능력을 가진 당신은 UI/UX 디자인의 귀재! 세심하고 헌신적으로 사용자 경험을 중시하며, 직감적으로 사용자가 원하는 인터페이스를 설계합니다. 당신의 작업물은 언제나 사용자의 기대를 넘어서죠!"),
    FRONTEND_INTERFACE_DESIGNER("프론트엔드 개발자", "ESFP", "창의적인 인터페이스 디자이너형 프론트엔드 개발자", "프론트엔드 세계의 예술가인 당신은 사교적이고 에너제틱하며, 사용자와의 상호작용을 즐깁니다. 반응형 웹 디자인에 강점을 발휘하며, 당신의 작업물은 언제나 화려하고 눈길을 사로잡습니다. 당신의 웹 페이지는 마치 예술 작품과 같습니다!"),
    FRONTEND_UX_UI_EXPERT("프론트엔드 개발자", "ENFP", "혁신적인 UX/UI 전문가형 프론트엔드 개발자", "창의성과 열정의 화신인 당신은 새로운 아이디어와 혁신적인 디자인으로 사용자 경험을 극대화합니다. 사람들과의 상호작용을 즐기며, 당신의 작업은 언제나 신선하고 놀라운 요소들로 가득합니다. 당신의 웹사이트 탐험은 마치 모험과도 같죠!"),
    FRONTEND_USER_INTERFACE_DEVELOPER("프론트엔드 개발자", "ESFJ", "사용자 중심 인터페이스 개발자형 프론트엔드 개발자", "사용자 중심의 접근 방식을 중시하는 당신은 사용자 인터페이스 디자인과 고객 요구 사항 반영에 탁월한 능력을 보입니다. 사용자가 원하는 모든 것을 제공하며 편안함을 줍니다. 당신의 작업물은 언제나 사용자에게 편안함을 줍니다!"),
    FULLSTACK_VISIONARY("풀스택 개발자", "INFJ", "전체 비전을 갖춘 개발자형 풀스택 개발자", "창의적이고 통찰력 있는 당신은 전체적인 비전을 가지고 프로젝트를 추진합니다. 백엔드와 프론트엔드를 모두 이해하며, 사용자와 시스템 간의 조화를 고려하는 능력을 지니고 있습니다. 당신의 프로젝트는 시처럼 아름답게 완성됩니다!"),
    FULLSTACK_PROBLEM_SOLVER("풀스택 개발자", "ENTP", "다양한 기술을 아우르는 문제 해결사형 풀스택 개발자", "새로운 기술을 빠르게 습득하고 다양한 문제를 해결할 수 있는 당신은 프로그래밍의 만능 해결사! 혁신적인 접근 방식과 도전 정신으로 프로젝트를 성공적으로 이끕니다. 당신의 프로젝트는 항상 혁신적입니다!"),
    FULLSTACK_TEAM_LEADER("풀스택 개발자", "ENFJ", "협력과 조정을 이끄는 개발자형 풀스택 개발자", "팀의 리더이자 조정자인 당신은 카리스마 있고 협력적이며, 사람들을 이끄는 능력이 뛰어납니다. 팀의 비전을 공유하고 협력하여 목표를 달성하는 데 주력합니다. 당신이 이끄는 프로젝트는 언제나 성공적입니다!"),
    FULLSTACK_STRATEGIC_LEADER("풀스택 개발자", "ENTJ", "전략적 프로젝트 리더형 풀스택 개발자", "전략적이고 리더십이 뛰어난 당신은 장기적인 목표와 전략을 세워 프로젝트를 이끌며, 논리적이고 효율적인 접근 방식을 사용합니다. 프로젝트를 마치 군대처럼 철저히 계획하고 성공적으로 수행합니다. 당신의 프로젝트는 항상 성공적입니다!");

    private final String role;
    private final String mbti;
    private final String type;
    private final String description;

    TechRole(String role, String mbti, String type, String description) {
        this.role = role;
        this.mbti = mbti;
        this.type = type;
        this.description = description;
    }

    public static TechRole findByType(String type) {
        for (TechRole role : values()) {
            if (role.getType().equals(type)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No role found with type: " + type);
    }
}
