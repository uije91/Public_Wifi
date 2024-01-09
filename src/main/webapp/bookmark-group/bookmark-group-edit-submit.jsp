<%@ page import="dao.BookmarkGroupDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    int order = Integer.parseInt(request.getParameter("order"));
    int id = Integer.parseInt(request.getParameter("id"));

    BookmarkGroupDAO dao = new BookmarkGroupDAO();
    int result = dao.updateBG(name, order, id);
%>
</body>
<script type="text/javascript">
    <%
        String text = result>0 ? "북마크 그룹 정보를 수정하였습니다" : "북마크 그룹 정보 수정을 실패했습니다.";
    %>
    alert("<%=text%>");
    location.href = 'bookmark-group.jsp'
</script>
</html>
