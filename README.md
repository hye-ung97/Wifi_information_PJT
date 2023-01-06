# Wifi_information_PJT

서울시 공공와이파이 서비스 위치 정보 API 를 이용하여 내 위치 기반 공공 와이파이 정보를 제공하는 웹 서비스입니다.

<div>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white"/>
  <img src="https://img.shields.io/badge/MariaDB_Foundation-1F305F?style=flat&logo=MariaDB&Foundation&logoColor=white"/>
	<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white" />
	<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white" />
	<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white" />
</div>

## 파일 설명
### JSP 
1. index.jsp : main 페이지
2. addWifiInfo.jsp : <code>Api</code> 데이터를 가져오며, 가져온 데이터 수를 화면에 출력하는 페이지
3. history.jsp : 위치 정보 조회한 리스트를 보여주는 페이지
4. delete.jsp : history.jsp 에서 받아온 id 값으로 wifi_history DB 에서 해당 값을 삭제하는 페이지

### JAVA
1. WifiInfo : 공공 와이파이 정보를 데이터를 <code>JSON</code> 으로 읽어옴(AddWifi), 내 위치에서 가까운 와이파이 위치를 조회(SearchWifi)
2. WifiDetail : 공공 wifi 정보를 객체화
3. InsertWifiInfo : WifiInfo 에서 AddWifi 로 읽어온 <code>JSON</code> 파일을 데이터 베이스에 insert
4. CalDistance : 내 위치의 좌표와 공공 와이파이의 좌표를 통해 <code>두 좌표의 거리</code>를 계산
5. History : 위치 정보 조회한 리스트를 객체화
6. HistoryDB : 위치 정보 조회한 history 를 wifi_history DB 에 삽입(historyInsert), 삭제(historyDel), 조회(historySelect)
