<%@ page import="his.HistoryDB" %>
<%@ page import="java.util.List" %>
<%@ page import="his.History" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WIFI HISTORY</title>
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
<h1>위치 히스토리 목록</h1>
<div style="padding-bottom: 1%">
    <span><a href="./index.jsp"> 홈 </a></span>
    <span> | </span>
    <span><a href="./history.jsp"> 위치 히스토리 목록 </a></span>
    <span> | </span>
    <span><a href="./addWifiInfo.jsp"> Open API 와이파이 정보 가져오기 </a></span>
</div>

<table id = "wifi_table">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>

    <tr>
    <% List<History> historyList = HistoryDB.historySelect();%>
        <% for(History item : historyList){ %>
            <tr>
            <td> <%=item.getNum()%></td>
            <td><%=item.getLat()%></td>
            <td><%=item.getLnt()%></td>
            <td><%=item.getSearchDate()%></td>
            <td><input type="button" onclick="location.href='./delete.jsp?num=<%=item.getNum()%>'" value="삭제"/></td>
        <%}%>
    </tr>

</table>

</body>
</html>