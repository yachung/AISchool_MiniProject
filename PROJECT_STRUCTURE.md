# CLI/Web 양용 프로젝트 구조 제안

CLI와 웹 애플리케이션에서 공통으로 비즈니스 로직을 사용하기 위해서는 **계층형 아키텍처(Layered Architecture)**를 도입하여 **핵심 로직**과 **사용자 인터페이스(UI)**를 완전히 분리해야 합니다.

가장 이상적인 방법은 Gradle 또는 Maven의 **멀티 모듈(Multi-module)** 기능을 활용하는 것입니다.

## 이상적인 멀티 모듈 구조

```
MiniProject/
├── pom.xml (또는 build.gradle)  <-- 전체 프로젝트를 관리하는 부모 빌드 파일

├── core-module/  <-- 핵심: 게임의 모든 비즈니스 로직 (UI 독립적)
│   └── src/main/java/minigame/
│       ├── domain/         // 데이터와 핵심 규칙 (Player, Pokemon, Skill...)
│       ├── service/        // 비즈니스 로직 처리 (GameService, BattleService...)
│       └── repository/     // 데이터 저장/조회 인터페이스 (PlayerRepository...)

├── cli-module/   <-- CLI UI 계층 (core-module에 의존)
│   └── src/main/java/minigame/cli/
│       ├── Main.java       // 현재의 GameManager와 유사한 CLI 루프
│       └── ConsoleView.java // 콘솔 출력 담당

└── web-module/   <-- Web UI 계층 (core-module에 의존)
    └── src/main/java/minigame/web/
        ├── controller/     // Spring MVC 컨트롤러 (BattleController, MapController...)
        ├── dto/            // 데이터 전송 객체 (PlayerDto, BattleRequestDto...)
        └── Application.java// Spring Boot 시작점
```

---

## 각 계층(Layer)의 역할

### 1. `core-module` (도메인 및 서비스 계층) - **가장 중요**

이 모듈은 프로젝트의 심장부로, **어떤 UI 기술에도 의존하지 않는** 순수 자바 코드로만 작성되어야 합니다.

-   **`domain`**: `Player`, `Pokemon` 등 게임의 기본 데이터와 핵심 규칙을 정의합니다.
-   **`service`**: `GameService`, `BattleService` 등 실제 비즈니스 로직을 수행합니다. UI로부터 받은 요청을 처리하고, 도메인 객체를 조작합니다. **이 계층에서는 `System.out` 이나 웹 프레임워크 관련 코드를 절대 사용하면 안 됩니다.**
-   **`repository`**: `PlayerRepository`와 같이 데이터 영속성을 위한 **인터페이스**를 정의합니다. 실제 구현(DB, 파일 등)은 다른 모듈에서 제공할 수 있습니다.

### 2. `cli-module` (CLI 표현 계층)

`core-module`을 사용하여 콘솔 환경을 구현합니다.

-   사용자 입력을 받아(`Scanner`) `core-module`의 서비스를 호출합니다.
-   서비스로부터 받은 결과(데이터)를 콘솔에 출력(`System.out.println`)하는 책임을 집니다.

### 3. `web-module` (Web 표현 계층)

`core-module`을 사용하여 웹 환경을 구현합니다. (예: Spring Boot)

-   사용자의 HTTP 요청을 받는 **Controller**를 정의합니다.
-   요청에 따라 `core-module`의 서비스를 호출합니다.
-   서비스로부터 받은 결과(데이터)를 JSON이나 HTML(Thymeleaf 등) 형태로 변환하여 사용자에게 응답합니다.

## 상태 패턴의 적용

상태 패턴은 `core-module`의 `service` 계층 내부에서 사용될 수 있습니다.

-   상태 객체(`MapState`, `BattleState` 등)는 더 이상 UI 출력 코드를 포함해서는 안 됩니다.
-   대신, 로직 처리 후 **결과와 다음 상태에 대한 정보를 담은 데이터 객체(DTO)**를 반환해야 합니다.
-   UI 계층(CLI 또는 Web)은 이 데이터 객체를 받아 사용자에게 적절한 방식으로 보여줍니다.

이 구조를 통해 **하나의 핵심 로직(`core-module`)**을 두고, 필요에 따라 CLI 또는 웹 애플리케이션을 선택적으로 빌드하고 실행할 수 있습니다.
