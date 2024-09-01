package org.meotppo.webti;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.entity.jpa.file.Image;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.jpa.question.Option;
import org.meotppo.webti.domain.entity.jpa.question.Question;
import org.meotppo.webti.domain.entity.jpa.result.Statistic;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.entity.type.PersonalityType;
import org.meotppo.webti.domain.repository.jpa.developertype.ProfileRepository;
import org.meotppo.webti.domain.repository.jpa.question.QuestionRepository;
import org.meotppo.webti.domain.repository.jpa.statistic.StatisticRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@org.springframework.context.annotation.Profile("local")
public class InitData {
    private final ProfileRepository profileRepository;
    private final QuestionRepository questionRepository;
    private final StatisticRepository statisticRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        String ENFJImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ENFJ.webp";
        String ENFPImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ENFP.webp";
        String ENTJImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ENTJ.webp";
        String ENTPImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ENTP.webp";
        String ESFJImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ESFJ.webp";
        String ESFPImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ESFP.webp";
        String ESTJImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ESFJ.webp";
        String ESTPImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ESTP.webp";
        String INFJImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/INFJ.webp";
        String INFPImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/INFP.webp";
        String INTJImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/INTJ.webp";
        String INTPImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/INTP.webp";
        String ISFJImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ISFJ.webp";
        String ISFPImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ISFP.webp";
        String ISTJImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ISTJ.webp";
        String ISTPImage = "https://webti-imagebucket.s3.eu-north-1.amazonaws.com/ISTP.webp";

        List<Profile> profileData = Arrays.asList(
                Profile.builder()
                        .mbtiType(MbtiType.ISTJ)
                        .result("철저한 데이터 관리형 백엔드 개발자")
                        .description(
                                "데이터의 정확성과 안정성을 최우선으로 하는 당신은 백엔드 개발의 수호자! 체계적이고 신뢰할 수 있는 방식으로 데이터를 관리하며, 버그는 당신에게 있어 전설 속 이야기일 뿐입니다. 당신의 코드는 그야말로 철옹성!")
                        .image(Image.builder()
                                .url(ISTJImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.INTJ)
                        .result("전략적 시스템 설계형 백엔드 개발자")
                        .description(
                                "소프트웨어 개발의 체스 그랜드마스터인 당신은, 몇 수 앞을 내다보는 전략가! 시스템 아키텍처를 전략적으로 설계하고, 최적의 경로를 찾아내는 당신의 코드는 예술 작품과 같습니다. 복잡한 문제도 순식간에 해결하는 능력자!")
                        .image(Image.builder()
                                .url(INTJImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ISTP)
                        .result("효율적인 문제 해결형 백엔드 개발자")
                        .description(
                                "프로그래밍 세계의 맥가이버, 당신! 실용적이고 논리적인 사고로 문제를 해결하며, 시스템 최적화와 효율적인 코드 작성에 탁월한 재능을 보입니다. 종이 클립 하나로도 문제를 해결할 수 있을 것 같은 당신의 능력은 그야말로 경이롭습니다.")
                        .image(Image.builder()
                                .url(ISTPImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.INTP)
                        .result("혁신적인 알고리즘 전문가 백엔드 개발자")
                        .description(
                                "코드의 아인슈타인인 당신은, 복잡한 알고리즘과 데이터 구조 설계에서 빛을 발합니다. 끊임없이 새로운 아이디어가 떠오르는 당신의 머릿속은 혁신으로 가득 차 있으며, 논리적이고 분석적인 사고로 누구도 해결하지 못하는 문제를 해결합니다.")
                        .image(Image.builder()
                                .url(INTPImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ESTJ)
                        .result("실행력 강한 관리자형 백엔드 개발자")
                        .description(
                                "당신은 체계적이고 조직적인 접근 방식을 통해 백엔드 시스템을 효율적으로 관리합니다. 철저한 계획과 규칙을 중시하며, 팀을 이끌어 프로젝트를 성공적으로 완수합니다. 당신의 코드는 언제나 깔끔하고 명확하며, 시스템의 신뢰성을 보장합니다.")
                        .image(Image.builder()
                                .url(ESTJImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ISFJ)
                        .result("섬세한 사용자 경험 디자이너형 프론트 엔드 개발자")
                        .description(
                                "사용자의 마음을 읽는 텔레파시 능력을 가진 당신은 UI/UX 디자인의 귀재! 세심하고 헌신적으로 사용자 경험을 중시하며, 직감적으로 사용자가 원하는 인터페이스를 설계합니다. 당신의 작업물은 언제나 사용자의 기대를 넘어서죠!")
                        .image(Image.builder()
                                .url(ISFJImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ESFP)
                        .result("창의적인 인터페이스 디자이너형 프론트엔드 개발자")
                        .description(
                                "프론트엔드 세계의 예술가인 당신은 사교적이고 에너제틱하며, 사용자와의 상호작용을 즐깁니다. 반응형 웹 디자인에 강점을 발휘하며, 당신의 작업물은 언제나 화려하고 눈길을 사로잡습니다. 당신의 웹 페이지는 마치 예술 작품과 같습니다!")
                        .image(Image.builder()
                                .url(ESFPImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ENFP)
                        .result("혁신적인 UX/UI 전문가형 프론트엔드 개발자")
                        .description(
                                "창의성과 열정의 화신인 당신은 새로운 아이디어와 혁신적인 디자인으로 사용자 경험을 극대화합니다. 사람들과의 상호작용을 즐기며, 당신의 작업은 언제나 신선하고 놀라운 요소들로 가득합니다. 당신의 웹사이트 탐험은 마치 모험과도 같죠!")
                        .image(Image.builder()
                                .url(ENFPImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ISFP)
                        .result("디테일에 강한 시각 예술가형 프론트엔드 개발자")
                        .description(
                                "당신은 섬세하고 창의적인 디자인 감각으로 사용자 인터페이스를 아름답게 구현합니다. 세밀한 부분까지 놓치지 않으며, 사용자가 시각적으로 즐길 수 있는 웹사이트를 만듭니다. 당신의 작업물은 예술 작품처럼 정교하고 매력적입니다.")
                        .image(Image.builder()
                                .url(ISFPImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.INFP)
                        .result("공감하는 이야기꾼형 프론트엔드 개발자")
                        .description(
                                "사용자 경험에 깊은 공감을 가지고 디자인하는 당신은, 감성적이고 창의적인 방식으로 웹 페이지를 만듭니다. 사용자와의 상호작용에서 진정성을 발휘하며, 웹사이트를 통해 따뜻한 이야기를 전합니다. 당신의 작업물은 사용자의 마음을 움직입니다.")
                        .image(Image.builder()
                                .url(INFPImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ESFJ)
                        .result("사용자 중심 인터페이스 개발자형 프론트엔드 개발자")
                        .description(
                                "사용자 중심의 접근 방식을 중시하는 당신은 사용자 인터페이스 디자인과 고객 요구 사항 반영에 탁월한 능력을 보입니다. 사용자가 원하는 모든 것을 제공하며 편안함을 줍니다. 당신의 작업물은 언제나 사용자에게 편안함을 줍니다!")
                        .image(Image.builder()
                                .url(ESFJImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.INFJ)
                        .result("전체 비전을 갖춘 개발자형 풀스택 개발자")
                        .description(
                                "창의적이고 통찰력 있는 당신은 전체적인 비전을 가지고 프로젝트를 추진합니다. 백엔드와 프론트엔드를 모두 이해하며, 사용자와 시스템 간의 조화를 고려하는 능력을 지니고 있습니다. 당신의 프로젝트는 시처럼 아름답게 완성됩니다!")
                        .image(Image.builder()
                                .url(INFJImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ENTP)
                        .result("다양한 기술을 아우르는 문제 해결사형 풀스택 개발자")
                        .description(
                                "새로운 기술을 빠르게 습득하고 다양한 문제를 해결할 수 있는 당신은 프로그래밍의 만능 해결사! 혁신적인 접근 방식과 도전 정신으로 프로젝트를 성공적으로 이끕니다. 당신의 프로젝트는 항상 혁신적입니다!")
                        .image(Image.builder()
                                .url(ENTPImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ENFJ)
                        .result("협력과 조정을 이끄는 개발자형 풀스택 개발자")
                        .description(
                                "팀의 리더이자 조정자인 당신은 카리스마 있고 협력적이며, 사람들을 이끄는 능력이 뛰어납니다. 팀의 비전을 공유하고 협력하여 목표를 달성하는 데 주력합니다. 당신이 이끄는 프로젝트는 언제나 성공적입니다!")
                        .image(Image.builder()
                                .url(ENFJImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ENTJ)
                        .result("전략적 프로젝트 리더형 풀스택 개발자")
                        .description(
                                "전략적이고 리더십이 뛰어난 당신은 장기적인 목표와 전략을 세워 프로젝트를 이끌며, 논리적이고 효율적인 접근 방식을 사용합니다. 프로젝트를 마치 군대처럼 철저히 계획하고 성공적으로 수행합니다. 당신의 프로젝트는 항상 성공적입니다!")
                        .image(Image.builder()
                                .url(ENTJImage)
                                .build())
                        .build(),
                Profile.builder()
                        .mbtiType(MbtiType.ESTP)
                        .result("유연하고 즉흥적인 개발자형 풀스택 개발자")
                        .description(
                                "당신은 변화와 도전에 강하며, 문제를 해결하는 데 있어 유연하고 즉흥적인 접근 방식을 취합니다. 실용적이고 효율적인 방법을 선호하며, 빠른 결단력으로 프로젝트를 성공적으로 이끌어갑니다. 당신의 작업은 항상 실용적이고 동적입니다.")
                        .image(Image.builder()
                                .url(ESTPImage)
                                .build())
                        .build()
        );

        profileRepository.saveAll(profileData);

        List<Question> questionData = Arrays.asList(
                Question.builder()
                        .question("새로운 프로젝트에 착수합니다. 팀원들이 다양한 아이디어를 내놓습니다. 당신은?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("모두 모여 브레인스토밍! 다양한 의견 환영!")
                                        .personalityType(PersonalityType.EXTROVERSION)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("깊이 생각해본 후 의견을 정리해서 제안")
                                        .personalityType(PersonalityType.INTROVERSION)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("개발을 할 장소를 선택할 수 있다면, 어디서 하시겠습니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("시끌벅적한 카페나 오픈 오피스, 활기찬 곳에서!")
                                        .personalityType(PersonalityType.EXTROVERSION)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("조용한 홈 오피스, 완전히 집중할 수 있는 곳!")
                                        .personalityType(PersonalityType.INTROVERSION)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("프로젝트 중간에 시스템 문제가 생겼습니다. 어떻게 대응하시겠습니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("동료들에게 물어보거나 온라인 포럼에 도움 요청!")
                                        .personalityType(PersonalityType.EXTROVERSION)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("차분히 문제 원인을 분석하고 스스로 해결책을 찾는다.")
                                        .personalityType(PersonalityType.INTROVERSION)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("새로운 프로그래밍 언어를 배우려 합니다. 어떻게 시작하시겠습니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("코딩 예제부터 따라하면서 직접 해본다.")
                                        .personalityType(PersonalityType.SENSING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("이론과 개념을 먼저 공부하고 나서 시작한다.")
                                        .personalityType(PersonalityType.INTUITION)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("오랜만에 개발 환경을 세팅합니다. 어떻게 접근하시겠습니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("예전에 쓰던 설정을 그대로 사용한다.")
                                        .personalityType(PersonalityType.SENSING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("최신 버전과 새로운 도구를 시도해본다.")
                                        .personalityType(PersonalityType.INTUITION)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("프로젝트를 기획할 때, 어떤 접근 방식을 선호합니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("이전에 성공했던 방법을 참고하여 기획한다.")
                                        .personalityType(PersonalityType.SENSING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("새로운 아이디어와 창의적인 접근 방식을 고려한다.")
                                        .personalityType(PersonalityType.INTUITION)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("팀원과의 의견 차이로 갈등이 생겼습니다. 어떻게 해결하시겠습니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("머리로 분석해서 해결합니다.")
                                        .personalityType(PersonalityType.THINKING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("마음을 열어서 대화하며 해결합니다.")
                                        .personalityType(PersonalityType.FEELING)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("중요한 결정을 내릴 때, 어떤 기준을 주로 고려하십니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("데이터와 사실을 중요시합니다.")
                                        .personalityType(PersonalityType.THINKING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("팀원들의 감정과 관계를 중시합니다.")
                                        .personalityType(PersonalityType.FEELING)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("프로젝트의 성공 여부를 평가할 때, 무엇을 중점적으로 보십니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("목표 달성과 성과 지표를 중시합니다.")
                                        .personalityType(PersonalityType.THINKING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("팀원들의 만족도와 협업을 중요시합니다.")
                                        .personalityType(PersonalityType.FEELING)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("프로젝트의 타임라인을 관리할 때, 어떤 방식을 선호하십니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("계획을 엄격하게 따르는 편입니다.")
                                        .personalityType(PersonalityType.JUDGING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("상황에 따라 유연하게 조정하는 편입니다.")
                                        .personalityType(PersonalityType.PERCEIVING)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("프로젝트 중 중요한 결정을 내릴 때, 어떤 절차를 따르십니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("빠르게 결정하고 실행에 옮깁니다.")
                                        .personalityType(PersonalityType.JUDGING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("여러 가지 옵션을 고려하며 천천히 결정합니다.")
                                        .personalityType(PersonalityType.PERCEIVING)
                                        .score(1)
                                        .build()
                        ))
                        .build(),
                Question.builder()
                        .question("프로젝트 도중 예상치 못한 변경 사항이 생겼습니다. 어떻게 대처하시겠습니까?")
                        .options(Arrays.asList(
                                Option.builder()
                                        .answer("계획을 유연하게 수정합니다.")
                                        .personalityType(PersonalityType.JUDGING)
                                        .score(1)
                                        .build(),
                                Option.builder()
                                        .answer("상황에 따라 적응하여 대처합니다.")
                                        .personalityType(PersonalityType.PERCEIVING)
                                        .score(1)
                                        .build()
                        ))
                        .build()
        );

        questionData.forEach(question ->
                question.getOptions().forEach(option -> option.setQuestion(question))
        );

        questionRepository.saveAll(questionData);

        List<Statistic> statisticData = profileData.stream()
                .map(profile -> Statistic.builder()
                        .profile(profile)
                        .count(0L)
                        .matchCount(0L)
                        .build())
                .collect(Collectors.toList());

        statisticRepository.saveAll(statisticData);
    }
}
