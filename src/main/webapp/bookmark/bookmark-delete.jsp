<%@ page import="dto.BookmarkDTO" %>
<%@ page import="dao.BookmarkDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<h2 style="font-weight: bold">북마크 관리</h2>
<%@include file="../include/menu.jsp" %>
<body>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    BookmarkDTO dto = new BookmarkDAO().getBookmarkInfo(id);
%>
<div style="margin-top: 20px; margin-bottom: 5px;">
    북마크를 삭제하시겠습니까?
</div>

<form action="bookmark-delete-submit.jsp" method="post">
    <table id="customers">
        <tr>
            <th style="width: 20%">북마크 이름</th>
            <td><%=dto.getName()%>
                <input type="hidden" name="id" value="<%=dto.getId()%>">
            </td>
        </tr>
        <tr>
            <th style="width: 20%">와이파이명</th>
            <td><%=dto.getWifiName()%>
            </td>
        </tr>
        <tr>
            <th>등록일자</th>
            <td><%=dto.getRegDate()%>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <button class="btn btn-success btn-sm" type="button" onclick="history.back()">돌아가기</button>
                <button class="btn btn-success btn-sm" type="submit">삭제</button>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
