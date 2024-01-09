<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page import="dao.BookmarkGroupDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<h2 style="font-weight: bold">북마크 그룹 관리</h2>
<%@include file="../include/menu.jsp" %>
<body>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    BookmarkGroupDTO dto = new BookmarkGroupDAO().getBGInfo(id);
%>

<form action="bookmark-group-edit-submit.jsp" method="post">
    <table id="customers">
        <tr>
            <th style="width: 20%">북마크 이름</th>
            <td>
                <input type="hidden" name = "id" value="<%=dto.getId()%>">
                <input style="width: 250px;" type="text" name="name" value="<%=dto.getName()%>">
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input style="width: 250px;" type="text" name="order" value="<%=dto.getOrder()%>">
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <button class="btn btn-success btn-sm" type="button" onclick="history.back()">돌아가기</button>
                <button class="btn btn-success btn-sm" type="submit">수정</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
