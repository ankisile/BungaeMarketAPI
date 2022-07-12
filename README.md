# 번개장터 REST API
### 구글 시트 링크
https://docs.google.com/spreadsheets/d/1QYZCyx8P5LWf_gQILggqD42_pHPkoZfg/edit?usp=sharing&ouid=100934178736454734095&rtpof=true&sd=true
### ERD 설계
<img src="/src/main/resources/KakaoTalk_20220401_012711250.png"  width="700" height="370">



## 2022-03-19 
1. EC2 서버구축 완료
2. 서브 도메인 완료
3. ERD 설계 중
4. api 리스트업 작성 중

## 2022-03-20
1. ERD 설계 완료
2. api 리스트업 작성 완료
3. 회원가입/로그인 완료
4. 상품등록 완료
5. 메인 페이지-이벤트 베너, 카테고리 불러오기 완료
6. 상품 상세페이지 이미지 불러오기 완료

## 2022-03-21
1. SSL 완료
2. 메인화면-상품 불러오기 완료
3. 상품 상세페이지 상품 정보 불러오기 완료
4. 사용자 주소 등록 완료

## 2022-03-22
1. 상품 상세페이지 수정중
2. 회원가입/로그인 수정완료
3. 유저 정보 수정 중

## 2022-03-23
1. 찜 조회, 등록/삭제 완료
2. 상품 상세페이지 수정완료
3. 최근 본 상품 조회 완료
4. 상품 등록-카테고리, 주소 수정완료
5. 상품 문의 제작중
6. 마이페이지 제작중

## 2022-03-24
1. 상품 문의 완료
2. 유저 정보 완료
3. 카테고리 이미지 받아오기 완료

## 2022-03-25
1. 상품 문의 완료
2. 상품 판매 상태 변경 완료
3. 마이페이지 완료
4. 상점 제작 중
5. 팔로우 완료
6. 검색 제작 중

## 2022-03-26
1. 상품 등록 수정
2. 마이페이지 문의 제작중
3. 리뷰 제작중

## 2022-03-27
1. 리뷰 제작 완료
2. 상품 상세페이지 수정
3. 주문하기 제작중

## 2022-03-28
1. 주문하기 완료
2. 주문 명세표 제작중
3. 채팅 제작중

## 2022-03-29
1. 주문 명세표 완료
2. 채팅 제작중
3. 주문 취소, 완료 완료

## 2022-03-30
1. API 수정 완료
2. 예외처리 다른방식으로

## 2022-03-31
1. try-catch 절 삭제

  
#  
#


#### @ControllerAdvice
Controller에 적용하기 위해 고안된 어노테이션\
모든 @Controller에 대한, 전역적으로 발생할 수 있는 예외를 잡아서 처리할 수 있다.
#### @RestControllerAdvice
@ControllerAdvice와 @ResponseBody를 합쳐놓은 어노테이션\
@ControllerAdvice는 예외만 잡아서 처리하는 반면, 
@RestControllerAdvice는 @ControllerAdvice 역할 뿐만 아니라, 
@ResponseBody를 통해 JSON 형태로 객체를 전달할 수 있다.
#### @ExceptionHandler
어노테이션을 메서드에 선언하고 특정 예외 클래스를 지정해주면
해당 예외가 발생했을 때 메서드에 정의한 로직으로 처리할 수 있다.  
이 어노테이션은 @Controller나 @RestController에 사용해야 한다. 
@Controller, @RestController가 적용된 Bean내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능을 한다.

전역적으로 에러를 핸들링하는 클래스를 생성하고 @ExceptionHandler 어노테이션과 함께 에러 핸들링 메소드를 추가함으로써 에러 처리를 위임할 수 있다.



https://jeong-pro.tistory.com/195  
https://javachoi.tistory.com/253  
https://mangkyu.tistory.com/205

  
https://mangkyu.tistory.com/174
