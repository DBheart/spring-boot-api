# 스프링 버전에 따른 API를 만든다.

## 목표
API문서/시큐리티/컨트롤러 dto까지 해결한다.
내부 api는 jpa까지만 한다. jqueryDSL은 안할지도 모르겠다.

스프링 부트에 따라서 따로 만든다.
- spring boot 2.x버전
- spring boot 3.x버전

API 문서에 따라서 달리한다.
- swagger 버전
- rest doc 버전

## 사이드
1. 인텔리제이를 사용하여 개발한다. 그에 따라서 달라지는 설정이 있을 것이다.
2. 스프링부트 3.X부터 개발한다.
3. java 버전까지 달리해야 할까?

---

springboot3는 1차적으로 포기한다.
  - jpa에서 쓰는 persitent 와의 변경이 심하다
  - security에 대한 필터객체가 변했다.
  - security에 대한 깊은 이해가 필요하다.

스웨거 적용한 시스템 : springboot2_x
rest doc, 시큐리티없이 진행한 시스템 : restdocWithoutSecurity
  - [O] 컨트롤러까지 연결 성공
  - [O] 서비스까지 연결 확인
rest doc, 시큐리티 있게 진행한 시스템(basic auth) : restdocWithSwagger
  - [O] basic로그인적용되어서 컨트롤러요청까지 확인
  - [O] basic로그인적용되어서 서비스까지 연결 확인

rest doc + 시큐리티 + token적용 시스템 : 아직 없음. 해야함
  - 토큰을 통한 인증은 어떻게 진행하는가?
  - auth2로 인증하는 방법도 확인해보아야...

---
좀더 나가가 위한 심화학습
1. gradle에 대한 이해와 익숙함
2. 시큐리티
3. swagger, rest doc
  - request, response 설정을 위한 대한 이해
  - 설정에 대한 이해
  - swagger ui를 docker로 띄워서 보는 방법
    - 그럼 api spec에서 스웨거 설정은 없어도 되는것 같다.
4. springboot3와 시큐리티, api spec의 연결 사용접점


---

<api spec 사용방법>
우선은 gradle에 있는 아래것을 실행한다.
  - build와 clean은 Task - build에 있다
  - openapi3는 Task - documentation에 있다.

1. build를 실행한다.
2. clean을 한다.
3. openapi3를 실행한다.

호스트:port:/docs/index.html로 접속한다. swagger-ui가 보일 것이다.
  - ex : http://localhost:9020/docs/index.html