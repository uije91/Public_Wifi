<%@ page import="dao.BookmarkGroupDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<h2 style="font-weight: bold">북마크 그룹 관리</h2>
<%@include file="../include/menu.jsp" %>
<body>
<button class="btn btn-success btn-sm" onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>

<table id="customers">
    <thead>
    <tr>
        <th style="width: 10%;">ID</th>
        <th style="width: 20%;">북마크 이름</th>
        <th style="width: 10%;">순서</th>
        <th style="width: 23%;">등록일자</th>
        <th style="width: 23%;">수정일자</th>
        <th style="width: 14%;">비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        BookmarkGroupDAO dao = new BookmarkGroupDAO();
        List<BookmarkGroupDTO> list = dao.getBGList();
        if (!list.isEmpty()) {
            for (BookmarkGroupDTO bg : list) {
    %>
    <tr>
        <td><%=bg.getId()%>
        </td>
        <td><%=bg.getName()%>
        </td>
        <td><%=bg.getOrder()%>
        </td>
        <td>
            <%=bg.getRegDate()%>
        </td>

        <%
            if (!(bg.getEditDate() == null)) {
        %>
        <td><%=bg.getEditDate()%>
        </td>
        <%
        } else {
        %>
        <td></td>
        <%
            }
        %>
        <td style="text-align: center">
            <button class="btn btn-success btn-sm"
                    onclick="location.href='bookmark-group-edit.jsp?id=<%=bg.getId()%>'">수정
            </button>
            <button class="btn btn-success btn-sm"
                    onclick="location.href='bookmark-group-delete.jsp?id=<%=bg.getId()%>'">삭제
            </button>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6" style="font-weight: bold; text-align: center; height: 80px">
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
