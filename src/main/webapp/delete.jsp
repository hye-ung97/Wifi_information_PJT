<%@ page import="his.HistoryDB" %>
<%@ page import="static sun.misc.MessageUtils.out" %><%--
  Created by IntelliJ IDEA.
  User: hy_ung
  Date: 2023/01/02
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
<%
    String id = request.getParameter("num");
    int succ = HistoryDB.historyDel(id);
    if(succ > 0){
%>
    <script>
        location.href = './history.jsp';
    </script>
<%  }
%>

</body>
</html>


