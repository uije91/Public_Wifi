package dao;

import controller.dbConn;
import dto.HistoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HisToryDAO {
    dbConn dbConn = new dbConn();

    //Select
    public List<HistoryDTO> getHistoryList() {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        List<HistoryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM HISTORY_WIFI ORDER BY ID DESC";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                HistoryDTO history = new HistoryDTO();
                history.setId(rs.getInt("ID"));
                history.setLat(rs.getString("LAT"));
                history.setLnt(rs.getString("LNT"));
                history.setSearchDate(rs.getString("SEARCH_DTTM"));
                list.add(history);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
        return list;
    }


    public void insertHistory(String lat, String lnt) {
        Connection conn = null;
        PreparedStatement pStmt = null;

        String sql = "INSERT INTO HISTORY_WIFI(LAT,LNT,SEARCH_DTTM) VALUES (?,?,now())";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, lat);
            pStmt.setString(2, lnt);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt);
        }
    }

    //Delete
    public int deleteHistory(int id) {
        Connection conn = null;
        PreparedStatement pStmt = null;

        String sql = "DELETE FROM HISTORY_WIFI WHERE ID = ?";
        int result=0;

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            result = pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt);
        }
        return result;
    }

}
