# 공공 와이파이 프로젝트

- OPEN API를 활용하여 자신의 위치 및 특정 위치에서 주변 와이파이를 찾는 기능 구현
<br>

### 사용 기술

- Language: JAVA / JSP
- DB: MariaDB
- Server: Tomcat 8.5
- OS: Windows11

### 기능 설명

- OPEN API를 활용하여 공공WIFI 정보를 DB에 저장
- 사용자가 입력한 좌표(또는 현재 위치의 좌표)를 기준으로 주변 20개의 와이파이 정보 출력
- 조회 시점으로 트리거를 이용하여 DB에 저장 및 정보 조회기능 제공
- 주변 WIFI의 상세 정보 제공
- 사용자는 북마크 그룹을 이용할 수 있으며, CRUD기능 제공

### ERD
![PUBLIC_WIFI-1704610483298-2](https://github.com/uije91/Public_Wifi/assets/131138445/b7a5ef9f-3496-4751-90b8-2232336fdfb7)
