<%@ page import="Wifi.WifiInfo" %>
<%@ page import="org.json.simple.parser.ParseException"%><%--
  Created by IntelliJ IDEA.
  User: hy_ung
  Date: 2023/01/02
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Wifi Info</title>
</head>
<body>
<center>
    <div style="padding: 1%;">
        <h1><%=WifiInfo.AddWifi()%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    </div>
    <div><a href="index.jsp">홈 으로 가기</a></div>
</center>

</body>
</html>
