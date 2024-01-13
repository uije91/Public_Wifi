package dao;

import controller.dbConn;
import dto.WifiDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDAO {
    dbConn dbConn = new dbConn();

    static final String KEY = "5376616562746a6436315863716b6e";
    static String baseUrl = "http://openapi.seoul.go.kr:8088/" + KEY + "/" + "json/TbPublicWifiInfo/";
    int wifiInsertCnt = 0;

    public List<WifiDTO> selectWifiList(String mgrNo) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        List<WifiDTO> list = new ArrayList<>();
        String sql = "select * from PUBLIC_WIFI WHERE MGR_NO = ?";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, mgrNo);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                WifiDTO wifi = new WifiDTO();
                wifi.setMrgNo(rs.getString("MGR_NO"));
                wifi.setCity(rs.getString("WRDOFC"));
                wifi.setWifiName(rs.getString("MAIN_NM"));
                wifi.setAddr1(rs.getString("ADRES1"));
                wifi.setAddr2(rs.getString("ADRES2"));
                wifi.setInsFloor(rs.getString("INSTL_FLOOR"));
                wifi.setInsType(rs.getString("INSTL_TY"));
                wifi.setInsAgency(rs.getString("INSTL_MBY"));
                wifi.setSvcType(rs.getString("SVC_SE"));
                wifi.setNetType(rs.getString("CMCWR"));
                wifi.setInsDate(rs.getString("CNSTC_YEAR"));
                wifi.setInOutDoor(rs.getString("INOUT_DOOR"));
                wifi.setWifiConn(rs.getString("REMARS3"));
                wifi.setLat(rs.getString("LAT"));
                wifi.setLnt(rs.getString("LNT"));
                wifi.setWorkDate(rs.getString("WORK_DTTM"));
                list.add(wifi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pStmt, rs);
        }

        return list;
    }

    public List<WifiDTO> getNearbyWifiList(String lat, String lnt) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<WifiDTO> list = new ArrayList<>();

        String sql = "SELECT *,round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT) -radians(?))+sin(radians(?))*sin(radians(LAT))), 4) AS distance"
                + " FROM public_wifi ORDER BY distance LIMIT 20;";


        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setDouble(1, Double.parseDouble(lat));
            pStmt.setDouble(2, Double.parseDouble(lnt));
            pStmt.setDouble(3, Double.parseDouble(lat));

            rs = pStmt.executeQuery();

            while (rs.next()) {
                WifiDTO wifi = new WifiDTO();
                wifi.setDistance(rs.getString("distance"));
                wifi.setMrgNo(rs.getString("MGR_NO"));
                wifi.setCity(rs.getString("WRDOFC"));
                wifi.setWifiName(rs.getString("MAIN_NM"));
                wifi.setAddr1(rs.getString("ADRES1"));
                wifi.setAddr2(rs.getString("ADRES2"));
                wifi.setInsFloor(rs.getString("INSTL_FLOOR"));
                wifi.setInsType(rs.getString("INSTL_TY"));
                wifi.setInsAgency(rs.getString("INSTL_MBY"));
                wifi.setSvcType(rs.getString("SVC_SE"));
                wifi.setNetType(rs.getString("CMCWR"));
                wifi.setInsDate(rs.getString("CNSTC_YEAR"));
                wifi.setInOutDoor(rs.getString("INOUT_DOOR"));
                wifi.setWifiConn(rs.getString("REMARS3"));
                wifi.setLat(rs.getString("LAT"));
                wifi.setLnt(rs.getString("LNT"));
                wifi.setWorkDate(rs.getString("WORK_DTTM"));
                list.add(wifi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConn.close(conn, pStmt, rs);
        }

        HisToryDAO dao = new HisToryDAO();
        dao.insertHistory(lat,lnt);
        return list;
    }

    public int insertWifi(List<WifiDTO> list) {
        Connection conn = null;
        PreparedStatement pStmt = null;

        String sql = "INSERT INTO PUBLIC_WIFI VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            for (WifiDTO wifi : list) {
                pStmt.setString(1, wifi.getMrgNo());
                pStmt.setString(1, wifi.getMrgNo());
                pStmt.setString(2, wifi.getCity());
                pStmt.setString(3, wifi.getWifiName());
                pStmt.setString(4, wifi.getAddr1());
                pStmt.setString(5, wifi.getAddr2());
                pStmt.setString(6, wifi.getInsFloor());
                pStmt.setString(7, wifi.getInsType());
                pStmt.setString(8, wifi.getInsAgency());
                pStmt.setString(9, wifi.getSvcType());
                pStmt.setString(10, wifi.getNetType());
                pStmt.setString(11, wifi.getInsDate());
                pStmt.setString(12, wifi.getInOutDoor());
                pStmt.setString(13, wifi.getWifiConn());
                pStmt.setString(14, wifi.getLat());
                pStmt.setString(15, wifi.getLnt());
                pStmt.setString(16, wifi.getWorkDate());
                int affected = pStmt.executeUpdate();
                if (affected > 0) {
                    wifiInsertCnt += 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt);
        }

        return wifiInsertCnt;
    }

    public int insertAPI() {
        int total = 0;
        int start = 0;
        int end = 0;

        int endPage = wifiCnt() / 1000;
        int endPageMod = wifiCnt() % 1000;

        for (int j = 0; j <= endPage; j++) {
            start = 1000 * j + 1;

            if (j == endPage) {
                end = start + endPageMod - 1;
            } else {
                end = 1000 * (j + 1);
            }

            StringBuilder sb = new StringBuilder();
            List<WifiDTO> wifiList = new ArrayList<>();

            try {
                URL url = new URL(baseUrl + start + "/" + end + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                conn.setDoOutput(true);

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while (br.ready()) {
                    sb.append(br.readLine());
                }

                conn.disconnect();

                JSONObject result = (JSONObject) new JSONParser().parse(sb.toString());
                JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
                JSONArray array = (JSONArray) data.get("row");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject tmp = (JSONObject) array.get(i);
                    WifiDTO wifi = new WifiDTO();
                    wifi.setMrgNo((String) tmp.get("X_SWIFI_MGR_NO"));
                    wifi.setCity((String) tmp.get("X_SWIFI_WRDOFC"));
                    wifi.setWifiName((String) tmp.get("X_SWIFI_MAIN_NM"));
                    wifi.setAddr1((String) tmp.get("X_SWIFI_ADRES1"));
                    wifi.setAddr2((String) tmp.get("X_SWIFI_ADRES2"));
                    wifi.setInsFloor((String) tmp.get("X_SWIFI_INSTL_FLOOR"));
                    wifi.setInsType((String) tmp.get("X_SWIFI_INSTL_TY"));
                    wifi.setInsAgency((String) tmp.get("X_SWIFI_INSTL_MBY"));
                    wifi.setSvcType((String) tmp.get("X_SWIFI_SVC_SE"));
                    wifi.setNetType((String) tmp.get("X_SWIFI_CMCWR"));
                    wifi.setInsDate((String) tmp.get("X_SWIFI_CNSTC_YEAR"));
                    wifi.setInOutDoor((String) tmp.get("X_SWIFI_INOUT_DOOR"));
                    wifi.setWifiConn((String) tmp.get("X_SWIFI_REMARS3"));
                    wifi.setLat((String) tmp.get("LAT"));
                    wifi.setLnt((String) tmp.get("LNT"));
                    wifi.setWorkDate((String) tmp.get("WORK_DTTM"));
                    wifiList.add(wifi);
                }
                try {
                    total = insertWifi(wifiList);
                } catch (Exception e) {
                    System.err.println("중복자료입니다.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return total;
    }


    public static int wifiCnt() {
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(baseUrl + "1/1/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-type", "application/json");
            con.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            while (br.ready()) {
                sb.append(br.readLine());
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject result = (JSONObject) new JSONParser().parse(sb.toString());
            JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
            return Integer.parseInt(data.get("list_total_count").toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
