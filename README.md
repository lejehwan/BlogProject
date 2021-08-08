use springboot, jsp, mysql, jpa, security 

##*--focus--*   
___
**JPA(Java Persistence API)는 자바 진영의 ORM 기술 표준**

  - JPA가 제공하는 API를 사용하면 객체를 DB에 저장하고 관리할 때, 개발자가 직접 SQL을 작성하지 않아도 된다.
  - JPA가 개발자 대신 적절한 SQL을 생성해서 DB에 전달하고, 객체를 자동으로 Mapping 해준다.
  - JPA는 내부적으로 JDBC API를 활용하는데, 개발자가 직접 JDBC API를 활용하면 패러다임 불일치, SQL 의존성 등으로 인해 효율성이 떨어진다.
  하지만, JPA를 활용한다면 모든 SQL에 대해 개발자 대신 JPA가 자동으로 해결해 준다는 점에서 생산성을 크게 높인다.

ORM(Object-Relational Mapping)은 객체와 관계형 DB를 매핑한다는 뜻
  ORM 프레임워크를 사용하면 객체를 마치 자바 컬렉션에 저장하듯 저장할 수 있고, 이에 대해 ORM 프레임워크가 적절한 SQL을 생성해서 DB에 객체를 저장해준다.

Hibernate
  자바 진영의 다양한 ORM 프레임워크 중 가장 많이 사용되는 성숙한 프레임워크
  이러한 Hibernate 기반으로 만들어진 ORM 기술 표준이 바로 JPA다.
 
*즉, JPA라는 ORM 기술 표준을 구현한 것이 Hibernate이므로, JPA를 사용하려면 Hibernate를 사용하면 된다.*
___
##*--security--*
애플리케이션의 보안(인증과 권한)등을 담당하는 스프링의 하위 프레임워크 

  - 사용자가 로그인 정보와 함께 인증 요청(HttpRequest)
  - AuthenticationFilter가 요청을 가로채서 UsernamePasswordAuthenticationToken 객체를 생성한다
  - ProviderManager 에게 UsernamePasswordAuthenticationToken 객체를 전달
  - UserDetailsService 에게 DB로 부터 받은 사용자 정보를 넘겨줌(이때 loadUserByUsername() 에서 UserDetails 객체를 비교해 동작함,
    이로 인해 인증의 세부과정이 달라질 수 있음)
  - 이 정보를 통해 UserDetails 객체를 생성
  - AuthenticationProvider는 UserDetails를 받아 사용자 정보를 비교함
  - 인증이 되었다면, 사용자 정보를 담은 Authentication 객체를 반환
  - 최초의 AuthenticationFilter에 Authentication 객체가 반환됨
  - Authentication 객체를 SecurityContext에 저장

**즉, Authentication(인증) -> Authorization(인가) 이후에 해당 리소스에 대한 접근 권한을 가진다.**
