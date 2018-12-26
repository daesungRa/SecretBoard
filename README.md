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

## 테이블 관계도

## Components

## Fixes

- [181222] oracle 11g xe 와 JDBC 를 사용함 (BuildPath 에 jdbc 라이브러리를 추가해야 함)
- [181224] git 에 oracle 로 업로드 했으나, 집에서 쓰는 로컬 PC 의 DB 는 MySql 이므로 로컬에 oracle 을 새로 설치함 (일관된 형상관리에 문제가 예상됨, 쿼리 문법이나 dburl 세팅 상 다소간의 차이가 있음)<br/> >> 로그인, 회원가입 로직 완료
- [181225] Login.java > Join.java > FindUser.java 간 회원 로직과 로그인 후 메인 페이지로 넘어가 해당 유저 아이디에 해당하는 정보를 표현하는 로직 완료, 사용자의 글을 저장하기 위한 게시판형 테이블 및 관련 시퀀스를 DB 에 추가 (SBOARD, SEQ_SBOARD), 메인 창에서는 유저의 모든 글 목록과 환영 메시지를 출력한다 (개선할 점 > SBOARD 테이블 내 유저의 ID 컬럼에 FK 제약조건 추가)
