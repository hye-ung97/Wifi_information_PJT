# ๐ก Wifi_information_PJT

์์ธ์ ๊ณต๊ณต์์ดํ์ด ์๋น์ค ์์น ์ ๋ณด API ๋ฅผ ์ด์ฉํ์ฌ ๋ด ์์น ๊ธฐ๋ฐ ๊ณต๊ณต ์์ดํ์ด ์ ๋ณด๋ฅผ ์ ๊ณตํ๋ ์น ์๋น์ค์๋๋ค. 

<div>
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white"/>
<img src="https://img.shields.io/badge/MariaDB_Foundation-1F305F?style=flat&logo=MariaDB&Foundation&logoColor=white"/>
<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white" />
<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white" />
<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white" />
</div>

<div>
<img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat&logo=IntelliJIDEA&logoColor=white"/>
<img src="https://img.shields.io/badge/DataGrip-000000?style=flat&logo=DataGrip&Foundation&logoColor=white"/>
<img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white" />
<img src="https://img.shields.io/badge/Apache_Tomcat-F8DC75?style=flat&logo=ApacheTomcat&logoColor=white" />
</div>

## ํ์ผ ์ค๋ช
### JSP 
1. index.jsp : main ํ์ด์ง
<p>
<img width="49%" alt="main" src="https://user-images.githubusercontent.com/117243197/210958735-0513114e-e6ea-4fb8-b080-7c4319c50364.png">
<img width="49%" alt="search" src="https://user-images.githubusercontent.com/117243197/210958950-2908e2cd-ade1-4ab0-bb5b-0435e67b03c3.png">
</p>

2. addWifiInfo.jsp : <code>Api</code> ๋ฐ์ดํฐ๋ฅผ ๊ฐ์ ธ์ค๋ฉฐ, ๊ฐ์ ธ์จ ๋ฐ์ดํฐ ์๋ฅผ ํ๋ฉด์ ์ถ๋ ฅํ๋ ํ์ด์ง
<img width="50%" alt="api" src="https://user-images.githubusercontent.com/117243197/210958947-af47a419-c717-4095-8ebb-746a8a693d24.png">

3. history.jsp : ์์น ์ ๋ณด ์กฐํํ ๋ฆฌ์คํธ๋ฅผ ๋ณด์ฌ์ฃผ๋ ํ์ด์ง
<img width="50%" alt="history" src="https://user-images.githubusercontent.com/117243197/210958949-35c3eb4b-4ba7-48d4-b6e5-94f0000d0e0e.png">

4. delete.jsp : history.jsp ์์ ๋ฐ์์จ id ๊ฐ์ผ๋ก wifi_history DB ์์ ํด๋น ๊ฐ์ ์ญ์ ํ๋ ํ์ด์ง

### JAVA
1. WifiInfo : ๊ณต๊ณต ์์ดํ์ด ์ ๋ณด๋ฅผ ๋ฐ์ดํฐ๋ฅผ <code>JSON</code> ์ผ๋ก ์ฝ์ด์ด(AddWifi), ๋ด ์์น์์ ๊ฐ๊น์ด ์์ดํ์ด ์์น๋ฅผ ์กฐํ(SearchWifi)
2. WifiDetail : ๊ณต๊ณต wifi ์ ๋ณด๋ฅผ ๊ฐ์ฒดํ
3. InsertWifiInfo : WifiInfo ์์ AddWifi ๋ก ์ฝ์ด์จ <code>JSON</code> ํ์ผ์ ๋ฐ์ดํฐ ๋ฒ ์ด์ค์ insert
4. CalDistance : ๋ด ์์น์ ์ขํ์ ๊ณต๊ณต ์์ดํ์ด์ ์ขํ๋ฅผ ํตํด <code>๋ ์ขํ์ ๊ฑฐ๋ฆฌ</code>๋ฅผ ๊ณ์ฐ
5. History : ์์น ์ ๋ณด ์กฐํํ ๋ฆฌ์คํธ๋ฅผ ๊ฐ์ฒดํ
6. HistoryDB : ์์น ์ ๋ณด ์กฐํํ history ๋ฅผ wifi_history DB ์ ์ฝ์(historyInsert), ์ญ์ (historyDel), ์กฐํ(historySelect)
