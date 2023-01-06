<%@ page import="his.HistoryDB" %>
<%@ page import="java.util.*" %>
<%@ page import="Wifi.WifiDetail" %>
<%@ page import="Wifi.WifiInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WIFI</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <style>
        #wifi_table {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #wifi_table td, #wifi_table th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #wifi_table tr:nth-child(even){background-color: #f2f2f2;}

        #wifi_table tr:hover {background-color: #ddd;}

        #wifi_table th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>

<body>
<h1>와이파이 정보 구하기</h1>
<div style="padding-bottom: 1%">
    <span><a href="./index.jsp"> 홈 </a></span>
    <span> | </span>
    <span><a href="./history.jsp"> 위치 히스토리 목록 </a></span>
    <span> | </span>
    <span><a href="./addWifiInfo.jsp"> Open API 와이파이 정보 가져오기 </a></span>
</div>

<%
    String lat = request.getParameter("latitude");
    String lnt = request.getParameter("longitude");
    List<WifiDetail> wifilist = new ArrayList<>();
    boolean check = false;
    if (lat == null) {
        lat = "0.0";
        lnt = "0.0";
    } else {
        HistoryDB.historyInsert(lat, lnt);
        wifilist = WifiInfo.SearchWifi(lat, lnt);
        check = true;
    }
%>

<div style="padding-bottom: 1%">
    <form action="index.jsp">
        <span> LAT : </span> <input id="latitude" name="latitude" type="text" value = "<%=lat%>" required/> ,
        <span> LNT : </span> <input id="longitude" name="longitude" type="text" value = "<%=lnt%>" required/>
        <input id="ip_btn" type="button" value="내 위치 가져오기" />
        <input id="wifi_info" type="submit" value="근처 WIFI 정보 보기" />
    </form>
</div>

<script>
    $('#ip_btn').click(function() {

        if ("geolocation" in navigator) { /* geolocation 사용 가능 */
            navigator.geolocation.getCurrentPosition(function(data) {

                var latitude = data.coords.latitude;
                var longitude = data.coords.longitude;

                $('#latitude').val(latitude);
                $('#longitude').val(longitude);
            }, function(error) {
                alert(error);
            }, {
                enableHighAccuracy : true,
                timeout : Infinity,
                maximumAge : 0
            });
        } else { /* geolocation 사용 불가능 */
            alert('geolocation 사용 불가능');
        }

    });

</script>

<table id = "wifi_table">
    <tr>
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>

    <tr>
        <% if(!check){%>
                <tr>
                    <td colspan="17" style="text-align:center; padding: 5%;">위치 정보를 입력 한 후에 조회 해주세요</td>
                </tr>
            <%}
             else if(check){
                int cnt = 1;
                for(WifiDetail item : wifilist){
                    if(cnt == 20){
                        break;
                    }
                    cnt = cnt + 1;
            %>
            <tr>
                <td><%=item.getDis()%></td>
                <td><%=item.getX_SWIFI_MGR_NO()%></td>
                <td><%=item.getX_SWIFI_WRDOFC()%></td>
                <td><%=item.getX_SWIFI_MAIN_NM()%></td>
                <td><%=item.getX_SWIFI_ADRES1()%></td>
                <td><%=item.getX_SWIFI_ADRES2()%></td>
                <td><%=item.getX_SWIFI_INSTL_FLOOR()%></td>
                <td><%=item.getX_SWIFI_INSTL_TY()%></td>
                <td><%=item.getX_SWIFI_INSTL_MBY()%></td>
                <td><%=item.getX_SWIFI_SVC_SE()%></td>
                <td><%=item.getX_SWIFI_CMCWR()%></td>
                <td><%=item.getX_SWIFI_CNSTC_YEAR()%></td>
                <td><%=item.getX_SWIFI_INOUT_DOOR()%></td>
                <td><%=item.getX_SWIFI_REMARS3()%></td>
                <td><%=item.getLAT()%></td>
                <td><%=item.getLNT()%></td>
                <td><%=item.getWORK_DTTM()%></td>
            </tr>
        <%
                }
            }
        %>
    </tr>
</table>
</body>
</html>