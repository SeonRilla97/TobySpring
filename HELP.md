## 빈 Servlet Container 생성

## Servlet 생성 후, Container에 삽입

## Mapping, Request, Response 처리

## FrontController 생성 (공통 기능 처리 / 다시 매핑)

## Logic 제외

## Spring Container 생성 // Bean 등록

## DI (추상화에 의존, 구현클래스에는 의존X)

-------------------------------

## ContainerLess (Servlet Container를 보고싶지 않다!)

문제점 : 바인딩(데이터 타입을 맞춰주는것)
과 매핑(URL Path정보 로직호출)이 하드코딩 되어있다.

-> FrontController의 기능을 대신 만들어준다면?

spring은 dispatcherServlet을 이용
- ApplicationContext와 이어주기 위해 매개변수로 준다 (로직을 실행할 수 있게 Servlet COntainer와 Spring Container가 연결된다)

GenericApplicationContext -> DI / Bean관리
GenericWebApplicationContext -> FrontController로 (dispatcherServlet)을 삽입, 기존 Container에서 Web기능 확장
AnnotationConfigWebApplicationContext -> 구성정보 Bean 등록 기능 추가


> 1. Mapping => Method / URL Mapping


@GetMapping("Hello") => Get Method // URL 정보
=> Bean 정보중, Mapping 정보를 불러와 Table생성 요청시 꺼내준다

=> 아무 정보없이 메소드까지 들여다보는것은 성능상 문제로 인해, @RequestMapping이 선언된 클래스에서 찾는다


> 2. dispatcher의 return 처리 :: View로 처리, Data로 처리

Default 설정 : String Return-> 해당 Return 문자열에 해당하는 Temaplte 찾기
@ResponseBody/@RestController : 응답 바디에 넣어서 전달

---

### Servlet Container를 제작하는것을 Application Context 제작하는 도중에 만들도록 코드를 변경 -> Spring Boot가 이렇게 만드니까!

Template Method 패턴

---

### ApplicationContext 가 Bean을 생성하는 방법

1. Factory Method 패턴 [Object를 생성하는 메소드]
    - 공장처럼 ->  제품을 만들고 소비자가 쓰도록 던져준다
2. Register Bean Method 이용
3. Component Scan 이용
4. Meta Annotation
 - 계층별 구분을 위한 커스터마이징 어노테이션 (역할을 표현하고 싶을 때) -> Controller , Service ,RestController, Repository Configuration


### Servlet Container와 Dispatcher Servlet을 생성하는 방법을 Spring Bean 등록을 통해 만들어보자

왜 해야 하지??

    Servlet Cotainer를 Bean 등록을 해놓으면 유연한 구성이 가능하다.

그전 까지는?

    Servlet Container와 dispatcherServlet을 하드코딩 시켜서 등록했다 -> 바꾸려면 코드를 수정해야한다
    하지만 이마저도 SpringContainer가 관리하는 것으로 넘어가면? 서버를 선택할수 있지 않을까?



### Spring Boot의 모양과 똑같이...

최초 구성정보를 담고있는 클래스의 정보, args의 정보를 넘겨줘서 Spring App 구동시키는 메소드를 타 클래스의 정적 메소드 (Static  Method)로 따로 빼서

메소드를 실행시키는 방식으로 하면 Main이 깔끔해짐 

최종버전.java 를 보면 알겠지만,

이를 했다고 해서, 구성정보 까지 모두 버릴 수는 없었음. -> 아마 다른클래스에서 구성정보를 등록하면 main에서는 빠질수 있어도, Servlet Container를 직접 주입하는 코드가
사용자에게 보여진다는것은 변함이 없다.














https://action713.tistory.com/entry/CC-%EC%86%8C%EC%8A%A4%EC%BD%94%EB%93%9C%EB%A5%BC-%EA%B3%B5%EA%B0%9C%ED%95%98%EB%8A%94-Site-%EB%AA%A8%EC%9D%8C