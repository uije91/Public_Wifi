<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.HisToryDAO" %>
<body>
<%
    HisToryDAO dao = new HisToryDAO();
    int id = Integer.parseInt(request.getParameter("id"));
    int result = dao.deleteHistory(id);

    if (result > 0) {
%>
<script type="text/javascript">
    alert("위치 히스토리를 삭제하였습니다.");
    location.href = "history.jsp"
</script>
<%
} else {
%>
<script type="text/javascript">
    alert("위치 히스토리 삭제에 실패했습니다.");
    location.href = "history.jsp"
</script>
<%}%>
</body>
</html>
