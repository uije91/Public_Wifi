package dao;

import controller.dbConn;
import dto.BookmarkDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDAO {
    dbConn dbConn = new dbConn();

    public BookmarkDTO getBookmarkInfo(int id) {
        BookmarkDTO bookmark = new BookmarkDTO();

        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM BOOKMARK WHERE ID = ?";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                bookmark.setId(rs.getInt("ID"));
                bookmark.setName(rs.getString("NAME"));
                bookmark.setMrgNo(rs.getString("MGR_NO"));
                bookmark.setDistance(rs.getString("DISTANCE"));
                bookmark.setWifiName(rs.getString("MAIN_NM"));
                bookmark.setRegDate(rs.getString("REG_DTTM"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
        return bookmark;
    }

    public List<BookmarkDTO> getBookmarkList() {
        List<BookmarkDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM BOOKMARK ORDER BY ID";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                BookmarkDTO bookmark = new BookmarkDTO();
                bookmark.setId(rs.getInt("ID"));
                bookmark.setName(rs.getString("NAME"));
                bookmark.setMrgNo(rs.getString("MGR_NO"));
                bookmark.setDistance(rs.getString("DISTANCE"));
                bookmark.setWifiName(rs.getString("MAIN_NM"));
                bookmark.setRegDate(rs.getString("REG_DTTM"));
                list.add(bookmark);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
        return list;
    }

    public int insertBookmark(String name, String mgrNo, String distance, String wifiName) {
        int result = 0;

        Connection conn = null;
        PreparedStatement pStmt = null;

        String sql = "INSERT INTO BOOKMARK(NAME,MGR_NO,DISTANCE,MAIN_NM,REG_DTTM) VALUES (?,?,?,?,now())";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, mgrNo);
            pStmt.setString(3, distance);
            pStmt.setString(4, wifiName);
            result = pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt);
        }

        return result;
    }

    public int deleteBookmark(int id) {
        int result = 0;

        Connection conn = null;
        PreparedStatement pStmt = null;

        String sql = "DELETE FROM BOOKMARK WHERE ID = ?";

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
