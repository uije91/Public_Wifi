# 공공 와이파이 프로젝트

- OPEN API를 활용하여 자신의 위치 및 특정 위치에서 주변 와이파이를 찾는 기능 구현
<br>

## 사용 기술

- Language: JAVA / JSP
- DB: MariaDB
- Server: Tomcat 8.5
- OS: Windows11

## <br>기능 설명

- OPEN API를 활용하여 공공WIFI 정보를 DB에 저장
- 사용자가 입력한 좌표(또는 현재 위치의 좌표)를 기준으로 주변 20개의 와이파이 정보 출력
- 조회 시점으로 DB에 검색 히스토리 저장 및 정보 조회기능 제공
- 주변 WIFI의 상세 정보 제공
- 사용자는 북마크 그룹을 이용할 수 있으며, CRUD기능 제공

## <br>ERD
![PUBLIC_WIFI-1704610483298-2](https://github.com/uije91/Public_Wifi/assets/131138445/b7a5ef9f-3496-4751-90b8-2232336fdfb7)


## <br>시연 영상
[![Video Label](http://img.youtube.com/vi/87FVzyhuf4E/0.jpg)](https://youtu.be/87FVzyhuf4E)

## <br>SQL Query
  ```sql
  # 데이터베이스 생성
  create database PUBLIC_WIFI

  # 계정 생성
  CREATE USER 'admin'@'localhost' IDENTIFIED BY '1234'
  CREATE USER 'admin'@'%' IDENTIFIED BY '1234'

  # DB권한 부여
  GRANT ALL PRIVILEGES ON PUBLIC_WIFI.* TO 'admin'@'localhost' IDENTIFIED BY '1234'
  GRANT ALL PRIVILEGES ON PUBLIC_WIFI.* TO 'admin'@'%' IDENTIFIED BY '1234'

  # 테이블 생성
  CREATE TABLE PUBLIC_WIFI
  (
      MGR_NO      VARCHAR(20) primary key,
      WRDOFC      VARCHAR(10),
      MAIN_NM     VARCHAR(50),
      ADRES1      VARCHAR(100),
      ADRES2      VARCHAR(100),
      INSTL_FLOOR VARCHAR(20),
      INSTL_TY    VARCHAR(50),
      INSTL_MBY   VARCHAR(20),
      SVC_SE      VARCHAR(20),
      CMCWR       VARCHAR(20),
      CNSTC_YEAR  VARCHAR(10),
      INOUT_DOOR  VARCHAR(10),
      REMARS3     VARCHAR(100),
      LAT         VARCHAR(20),
      LNT         VARCHAR(20),
      WORK_DTTM   VARCHAR(30)
  );
  
  CREATE TABLE HISTORY_WIFI
  (
      ID          INTEGER AUTO_INCREMENT primary key,
      LAT         VARCHAR(20),
      LNT         VARCHAR(20),
      SEARCH_DTTM varchar(30)
  );
  
  CREATE TABLE BOOKMARK
  (
      ID       INTEGER AUTO_INCREMENT primary key,
      NAME     VARCHAR(50),
      MGR_NO      VARCHAR(20),
      DISTANCE VARCHAR(20),
      MAIN_NM  VARCHAR(50),
      REG_DTTM VARCHAR(30),
      FOREIGN KEY (MGR_NO) REFERENCES PUBLIC_WIFI(MGR_NO)
  );
  
  
  CREATE TABLE BOOKMARK_GROUP
  (
      ID        INTEGER AUTO_INCREMENT primary key,
      NAME      VARCHAR(50),
      BG_ORDER  INTEGER,
      REG_DTTM  VARCHAR(30),
      EDIT_DTTM VARCHAR(30)
  );
  ```
