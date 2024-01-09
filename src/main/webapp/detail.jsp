<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/header.jsp" %>
<%@ page import="dto.WifiDTO" %>
<%@ page import="dao.WifiDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page import="dao.BookmarkGroupDAO" %>
<body>
<h2 style="font-weight: bold">와이파이 정보 구하기</h2>
<%@include file="include/menu.jsp" %>


<%
    WifiDAO wifiDao = new WifiDAO();
    String mgrNo = request.getParameter("mgrNo");
    String distance = request.getParameter("distance");
    List<WifiDTO> wifiList = wifiDao.selectWifiList(mgrNo);

    BookmarkGroupDAO BGDao = new BookmarkGroupDAO();
    List<BookmarkGroupDTO> BGList = BGDao.getBGList();
%>

<%for (WifiDTO wifi : wifiList) {%>
<div>
    <form style="margin-top: 10px;margin-bottom: 10px;" method="post" action="bookmark/bookmark_add_submit.jsp">
        <select name="selectGroup" class="form-select-sm" style="width: 230px;height: 2.8%;">
            <option value="none" selected>북마크 그룹 이름 선택</option>
            <% for (BookmarkGroupDTO dto : BGList) {%>
            <option value="<%=dto.getName()%>"><%=dto.getName()%>
            </option>
            <%} %>
        </select>
        <input type="submit" class="btn btn-success btn-sm" value="즐겨찾기 추가하기">
        <input type="hidden" name="mgr_no" value="<%=wifi.getMrgNo()%>">
        <input type="hidden" name="distance" value="<%=distance%>">
        <input type="hidden" name="wifi_name" value="<%=wifi.getWifiName()%>">
    </form>
</div>
<div>
    <table id="customers">
        <tr width="10%">
            <th style="width: 20%;">거리(km)</th>
            <td><%=distance%>
            </td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td><%=mgrNo%>
            </td>
        </tr>
        <tr>
            <th>자치구</th>
            <td><%=wifi.getCity()%>
            </td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=wifi.getWifiName()%>
            </td>
        </tr>
        <tr>
            <th>도로명 주소</th>
            <td><%=wifi.getAddr1()%>
            </td>
        </tr>
        <tr>
            <th>상세 주소</th>
            <td><%=wifi.getAddr2()%>
            </td>
        </tr>
        <tr>
            <th>설치 위치(층)</th>
            <td><%=wifi.getInsFloor()%>
            </td>
        </tr>
        <tr>
            <th>설치 기관</th>
            <td><%=wifi.getInsAgency()%>
            </td>
        </tr>
        <tr>
            <th>설치 유형</th>
            <td><%=wifi.getInsType()%>
            </td>
        </tr>
        <tr>
            <th>서비스 구분</th>
            <td><%=wifi.getSvcType()%>
            </td>
        </tr>
        <tr>
            <th>망 종류</th>
            <td><%=wifi.getNetType()%>
            </td>
        </tr>
        <tr>
            <th>설치 년도</th>
            <td><%=wifi.getInsDate()%>
            </td>
        </tr>
        <tr>
            <th>실내 외 구분</th>
            <td><%=wifi.getInOutDoor()%>
            </td>
        </tr>
        <tr>
            <th>WIFI 접속 환경</th>
            <td><%=wifi.getWifiConn()%>
            </td>
        </tr>
        <tr>
            <th>위도</th>
            <td><%=wifi.getLat()%>
            </td>
        </tr>
        <tr>
            <th>경도</th>
            <td><%=wifi.getLnt()%>
            </td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td><%=wifi.getWorkDate()%>
            </td>
        </tr>
    </table>
</div>
<%}%>
</body>
</html>

