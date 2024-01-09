<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.WifiDAO" %>
<%@include file="include/header.jsp"%>
<body>
<%
    WifiDAO wifiService = new WifiDAO();
    int result = wifiService.insertAPI();
%>
<h2 style="text-align:center; font-weight: bold;"><%=result%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
<br>
<div style="text-align: center">
    <a href="index.jsp">홈으로 가기</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>