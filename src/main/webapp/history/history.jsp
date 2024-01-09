<%@ page import="dao.HisToryDAO" %>
<%@ page import="dto.HistoryDTO" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h2 style="font-weight: bold">위치 히스토리 목록</h2>
<%@include file="../include/menu.jsp" %>

<%
    HisToryDAO dao = new HisToryDAO();
    List<HistoryDTO> historyList = dao.getHistoryList();
%>
<div>
    <table id="customers">
        <thead>
        <tr>
            <th>ID</th>
            <th>위도</th>
            <th>경도</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (!historyList.isEmpty()) { //없으면 insertHistory 무한반복
                for (HistoryDTO history : historyList) {
        %>
        <tr>
            <td><%=history.getId()%>
            </td>
            <td><%=history.getLat()%>
            </td>
            <td><%=history.getLnt()%>
            </td>
            <td><%=history.getSearchDate()%>
            </td>
            <td style="text-align: center">
                <button class="btn btn-success btn-sm" onclick="location.href='history-delete.jsp?id=<%=history.getId()%>'">삭제</button>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td style="text-align: center; font-weight: bold; height: 80px;" colspan="5">저장된 위치정보가 없습니다.</td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
