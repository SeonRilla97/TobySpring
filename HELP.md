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

### ApplicationContext 가 Bean을 어떻게 관리하고 관계를 형성할까?

Factory Method 패턴 [Object를 생성하는 메소드]

공장처럼 ->  제품을 만들고 소비자가 쓰도록 던져준다

