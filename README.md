Field injection 대신 Constructor Injection을 추천하는 이유
----

Field injection은 보기에 매우 깔끔하고 직관적이다. Constructor injection은 쓸데없는 코드가 늘어나는 걸로 보이고 조금 더 무례하게 말하면 좀 구닥다리로 보이기도 한다. 그런데 왜 이 방식을 추천하는걸까? 찾아본 결과 이유를 간추리면 대략 이러하다. 

1. 필수적인 의존관계 주입에 적합
객체에 필요한 파라미터를 생성자로 넘기는걸로 오브젝트가 구축되는 순간에 그 오브젝트가 사용 가능한지 확인할 수 있다. 생성자에서 주입된 필드가 최종적으로 만들어진 필드가 되며 오브젝트는 완전 불변한 상태가 되던가 적어도 필요한 필드를 보호할 수 있게 된다. 

2. 단일책임의 원칙 준수 확인
Constructor injection이 번잡하게 느껴질 경우 클래스에 많은 의존관계가 있다는 것을 알려주게 된다. 이건 그 클래스에 지나치게 많은 책임이 지워지고 있다는 증거가 된다. 이건 단일책임의 원칙(http://www.nextree.co.kr/p6960/)을 위반한다. 생성자 주입을 사용하는걸로 그 맹점을 깨닫기 쉬워진다. 

3. 의존관계 명시
그 클래스에 필수적인 의존관계가 무엇인지 명확히 할 수 있게 된다. 필수적인 의존관계의 경우 Constructor injection을 사용하고, 옵셔너블한 의존관계일 경우 Setter injection을 사용하는 등 그 의존관계가 필수적인지 옵셔너블한지 명확히 구별할 수 있게 된다. 

4. 재이용성
의존성 콘테이너로 관리되는 클래스는 특정 의존성 콘테이너에 의존하지 않고 POJO(http://limmmee.tistory.com/8) 상태로 있어야 한다. 이렇게 하면 의존성 콘테이너를 사용하지 않고 인스턴스화해서 단일 테스트가 가능해지고 또 다른 DI 프레임워크로 바꾸는 것도 가능해진다. 그러나 Field injection을 사용하고 있는 경우 그 클래스에서 필요한 의존관계를 인스턴스화할 방법이 없어진다. (리플렉션 제외) 그 클래스를 인스턴스화 하여 의존관계 클래스를 사용하면 NullpointException이 발생하고 만다. 즉 의존성 콘테이너 이외에는 재이용할 수 없다는 것. Constructor injection을 사용하는 것으로 인스턴스화 할 때 필요한 의존관계를 강제하는 것이 가능하다. 

5. 불변성
Constructor injection에서는 필드는 final로 선언한다. 그렇기에 모두 불변 객체로 하거나 필요한 의존관계만을 불변으로 설정하는 것이 가능하다. field injection의 경우 필드는 final로 선언할 수 없게 되므로 의존관계는 가변 상태로 남아있게 된다. 

6. 순환의존성
Constructor injection을 사용하는 경우 순환의존이 문제가 되는 경우가 있다. 이건 A클래스가 B클래스를 inject. B클래스가 C클래스를 inject, C클래스가 A클래스를 inject하는 케이스가 있는 경우 BeanCurrentlyCreationException이 발생한다. 순환의존을 사용하고 싶을 때는 constructor injection을 사용할 수 없다. 하지만 순환의존이란 보통 안티패턴화 되어있으므로 클래스 설계가 잘못되어있을 가능성이 크다. 

사실 대부분의 스프링 프로젝트는 Field injection으로 구축되어있을거고 지금 내가 있는 회사 또한 그러하다. 즉 Field injection은 다소 단점과 취약함이 있긴 하지만 검증된 패턴이고, 많은 프로젝트를 움직이게 하는 방식임을 부정할 수는 없을 것 같다. 그래도 좀 더 나은 방식이 있다는 점을 알았으니 이제부터라도 Constructor injection을 사용하도록 신경써봐야겠다.



참고 사이트 
----
글을 작성하면서 특정 내용을 모를때마다 찾아보고 참고했던 사이트들의 목록

1. 스프링에서 field injection보다 constructor injection이 추천되는 이유 (http://pppurple.hatenablog.com/entry/2016/12/29/233141) (일본어)
2. Field Dependency Injection Considered Harmful (http://vojtechruzicka.com/field-dependency-injection-considered-harmful/) (영어)
3. 왜 생성자 주입(Constructor Injection)을 사용해야 하는가? (http://home.zany.kr:9003/board/bView.asp?bCode=52299899&aCode=14064) (한국어)
4. Core container refinements in Spring Framework 4.3 (https://spring.io/blog/2016/03/04/core-container-refinements-in-spring-framework-4-3) (영어)
5. 객체지향 개발 5대 원리:SOLID (http://www.nextree.co.kr/p6960/) (한국어)
6. POJO에 대하여 (http://limmmee.tistory.com/8) (한국어)