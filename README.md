# SecretBoard
This is my semi-project for secret board

## Summary

- 이 프로그램의 컨셉은 게시판형 개인 일기장이다
- DB 커넥팅과 계정관리 및 회원관리, Swing 으로 로그인 및 게시판 기능 구현 등을 연습하고자 만든다

## 사용 도구

- Oracle 11g xe
- java 8
- UI: Swing
- Eclipse Photon

## 로직 흐름도

![flow](https://github.com/daesungRa/SecretBoard/blob/master/content/projectArchi/sd3.PNG)

## 테이블 관계도

![erd](https://github.com/daesungRa/SecretBoard/blob/master/content/projectArchi/sd4.PNG)

## 설명

![Login](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/Login.PNG)
![Join](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/Join.PNG)
![FindUser](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/FindUser.PNG)
![FindUser](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/FindUser.PNG)
![Main](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/Main.PNG)
![AllContent](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/AllContent.PNG)
![ContentByDate](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/ContentByDate.PNG)
![Write](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/Write.PNG)
![WriteAction](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/WriteAction.PNG)
![View](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/View.PNG)
![Update](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/Update.PNG)
![UpdateResult](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/UpdateResult.PNG)
![Delete](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/Delete.PNG)
![Logout](https://github.com/daesungRa/SecretBoard/blob/master/content/중간결과물/Logout.PNG)


## Fixes

- <strong>[181222]</strong> oracle 11g xe 와 JDBC 를 사용함 (BuildPath 에 jdbc 라이브러리를 추가해야 함)
- <strong>[181224]</strong> git 에 oracle 로 업로드 했으나, 집에서 쓰는 로컬 PC 의 DB 는 MySql 이므로 로컬에 oracle 을 새로 설치함 (일관된 형상관리에 문제가 예상됨, 쿼리 문법이나 dburl 세팅 상 다소간의 차이가 있음)<br/> >> 로그인, 회원가입 로직 완료
- <strong>[181225]</strong> Login.java > Join.java > FindUser.java 간 회원 로직과 로그인 후 메인 페이지로 넘어가 해당 유저 아이디에 해당하는 정보를 표현하는 로직 완료, 사용자의 글을 저장하기 위한 게시판형 테이블 및 관련 시퀀스를 DB 에 추가 (SBOARD, SEQ_SBOARD), 메인 창에서는 유저의 모든 글 목록과 환영 메시지를 출력한다 (개선할 점 > SBOARD 테이블 내 유저의 ID 컬럼에 FK 제약조건 추가)
- <strong>[181226]</strong> Main.java 에서 유저의 모든 게시글에 대해 페이징 처리 완료 (이전, 이후 페이지, 1 페이지에서 마지막 페이지까지 순차적으로 표현)<br/>SBOARD 테이블에서 PWD 컬럼을 삭제함<br/>Write.java 페이지 레이아웃 및 로직 작성
- <strong>[181227]</strong> 나머지 CRUD 기능 구현<br/>SBOARD 테이블의 ISPUBLIC 컬럼이 boolean 형을 저장하도록 속성 변경 및 제약조건 설정<br/>SBOARD 테이블의 ID 컬럼에 FK 제약조건 추가
- <strong>[181228]</strong> 완성 (개인 페이지까지)<br>메인 페이지의 상단 달력 세팅 및 일별 글 조회, 하단 페이지라벨링 처리
- <strong>[190102]</strong> USERS 테이블 제약조건 변경(drop USERS_ID_PK, add USERS_SERIAL_PK/USERS_ID_NN/USERS_ID_UK), SEQ_USERS 추가, 회원가입 쿼리 변경(SEQ_USERS.nextval 값이 serial 에 적용되도록), 회원가입 검증 로직 추가(공백, 이메일 포맷), 로그인 공백 검증 추가

## 개선할 점

- tag 기능을 추가하여 원하는 키워드로 필터링된 글 검색
- 페이지 라벨링 완료 (ok)
- 검색된 글 목록을 연월별, 일별 등 사용자가 원하는 대로 필터링해 화면에 예쁘게 표현해보기 (ok)
- 공용 페이지까지 확장
- 친구추가 기능 구현