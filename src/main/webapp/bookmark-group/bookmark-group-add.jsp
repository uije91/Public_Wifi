<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<h2 style="font-weight: bold">북마크 그룹 관리</h2>
<%@include file="../include/menu.jsp" %>
<body>

<form action="bookmark-group-add-submit.jsp" method="post">
<table id="customers">
    <tr>
        <th style="width: 20%">북마크 이름</th>
        <td>
            <input style="width: 250px;" type="text" name="name">
        </td>
    </tr>
    <tr>
        <th>순서</th>
        <td>
            <input style="width: 250px;" type="text" name="order">
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <button class="btn btn-success btn-sm" type="submit">추가</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
