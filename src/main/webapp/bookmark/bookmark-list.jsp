<%@ page import="java.util.List" %>
<%@ page import="dao.BookmarkDAO" %>
<%@ page import="dto.BookmarkDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<h2 style="font-weight: bold">북마크 관리</h2>
<%@include file="../include/menu.jsp" %>
<body>
<table id="customers">
    <thead>
    <tr>
        <th style="width: 10%;">ID</th>
        <th style="width: 20%;">북마크 이름</th>
        <th style="width: 25%;">와이파이명</th>
        <th style="width: 25%;">등록일자</th>
        <th style="width: 10%;">비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        BookmarkDAO dao = new BookmarkDAO();
        List<BookmarkDTO> list = dao.getBookmarkList();
        if (!list.isEmpty()) {
            for (BookmarkDTO bookmark : list) {
    %>
    <tr>
        <td><%=bookmark.getId()%>
        </td>
        <td><%=bookmark.getName()%>
        </td>
        <td>
            <a href="../detail.jsp?mgrNo=<%=bookmark.getMrgNo()%>&distance=<%=bookmark.getDistance()%>"><%=bookmark.getWifiName()%>
        </td>
        <td><%=bookmark.getRegDate()%>
        </td>

        <td style="text-align: center">
            <button class="btn btn-success btn-sm"
                    onclick="location.href='bookmark-delete.jsp?id=<%=bookmark.getId()%>'">삭제
            </button>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5" style="font-weight: bold; text-align: center; height: 80px">
            정보가 존재하지 않습니다.
        </td>

    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
