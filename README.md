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

스웨거 적용한 시스템 : springboot2_x
rest doc, 시큐리티없이 진행한 시스템 : restdocWithoutSecurity
  - [O] 컨트롤러까지 연결 성공
  - [ ] 서비스까지 연결 확인
rest doc, 시큐리티 있게 진행한 시스템(basic auth) : restdocWithSwagger
  - [ ] basic로그인적용되어서 컨트롤러요청까지 확인
  - [ ] basic로그인적용되어서 서비스까지 연결 확인

rest doc + 시큐리티 + token적용 시스템 : 아직 없음. 해야함