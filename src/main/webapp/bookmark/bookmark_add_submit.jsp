<%@ page import="dao.BookmarkDAO" %>
<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page import="dao.BookmarkGroupDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");

    String name = request.getParameter("selectGroup");
    String distance = request.getParameter("distance");
    String mgrNo = request.getParameter("mgr_no");
    String wifiName = request.getParameter("wifi_name");

    BookmarkDAO dao = new BookmarkDAO();
    int result = dao.insertBookmark(name, mgrNo, distance, wifiName);
%>
</body>
<script type="text/javascript">
    <%
        String text = result > 0 ? "북마크 정보를 추가하였습니다" : "북마크 정보 추가를 실패했습니다.";
    %>
    alert("<%=text%>");
    location.href = 'bookmark-list.jsp';
</script>
</html>
