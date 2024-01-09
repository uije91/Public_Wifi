<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/header.jsp" %>
<%@ page import="dao.WifiDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.WifiDTO" %>
<body>
<h2 style="font-weight: bold">와이파이 정보 구하기</h2>
<%@include file="include/menu.jsp" %>
<%
    String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
    String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
%>
<div>
    LAT: <input type="text" id="latitude" value="<%=lat%>">
    LNT: <input type="text" id="longitude" value="<%=lnt%>">

    <button type="button" class="btn btn-success btn-sm" onclick="getLocation()">내 위치 가져오기
    </button>
    <button type="button" class="btn btn-success btn-sm" onclick="getNearbyWifiList()">
        근처 WIFI 정보 보기
    </button>
</div>
<div>
    <table id="customers">
        <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>위도</th>
            <th>경도</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (lat.equals("0.0") || lnt.equals("0.0") || lat.isEmpty() || lnt.isEmpty()) {
        %>
        <tr>
            <td style="text-align: center; font-weight: bold; height: 80px;" colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        <%
        } else {
            WifiDAO wifiService = new WifiDAO();
            List<WifiDTO> list = wifiService.getNearbyWifiList(lat, lnt);
            if (!list.isEmpty()) {
                for (WifiDTO wifi : list) {
        %>
        <tr>
            <td><%=wifi.getDistance()%>
            </td>
            <td><%=wifi.getMrgNo()%>
            </td>
            <td><%=wifi.getCity()%>
            </td>
            <td>
                <a href="detail.jsp?mgrNo=<%=wifi.getMrgNo()%>&distance=<%=wifi.getDistance()%>"><%=wifi.getWifiName()%>
                </a>
            </td>
            <td><%=wifi.getAddr1()%>
            </td>
            <td><%=wifi.getAddr2()%>
            </td>
            <td><%=wifi.getInsFloor()%>
            </td>
            <td><%=wifi.getInsType()%>
            </td>
            <td><%=wifi.getInsAgency()%>
            </td>
            <td><%=wifi.getSvcType()%>
            </td>
            <td><%=wifi.getNetType()%>
            </td>
            <td><%=wifi.getInsDate()%>
            </td>
            <td><%=wifi.getInOutDoor()%>
            </td>
            <td><%=wifi.getWifiConn()%>
            </td>
            <td><%=wifi.getLat()%>
            </td>
            <td><%=wifi.getLnt()%>
            </td>
            <td><%=wifi.getWorkDate()%>
            </td>
        </tr>
        <%
                    }
                }
            }
        %>
        </tbody>
    </table>
</div>
<script src="resources/js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous">
</script>
</body>
</html>