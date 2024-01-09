<%@ page import="dao.BookmarkGroupDAO" %>
<%@ page import="dao.BookmarkDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    int id = Integer.parseInt(request.getParameter("id"));
    int result = new BookmarkDAO().deleteBookmark(id);
%>
</body>
<script type="text/javascript">
    <%
        String text = result > 0 ? "북마크 정보를 삭제하였습니다" : "북마크 정보 삭제에 실패했습니다.";
    %>
    alert("<%=text%>");
    location.href = 'bookmark-list.jsp'
</script>
</html>
