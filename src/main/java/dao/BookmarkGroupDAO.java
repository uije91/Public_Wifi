package dao;

import controller.dbConn;
import dto.BookmarkGroupDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDAO {
    dbConn dbConn = new dbConn();


    public BookmarkGroupDTO getBGInfo(int id) {
        BookmarkGroupDTO bg = new BookmarkGroupDTO();

        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM BOOKMARK_GROUP WHERE ID = ?";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                bg.setId(rs.getInt("ID"));
                bg.setName(rs.getString("NAME"));
                bg.setOrder(rs.getInt("BG_ORDER"));
                bg.setRegDate(rs.getString("REG_DTTM"));
                bg.setEditDate(rs.getString("EDIT_DTTM"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
        return bg;
    }

    public List<BookmarkGroupDTO> getBGList() {
        List<BookmarkGroupDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM BOOKMARK_GROUP ORDER BY ID";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                BookmarkGroupDTO bg = new BookmarkGroupDTO();
                bg.setId(rs.getInt("ID"));
                bg.setName(rs.getString("NAME"));
                bg.setOrder(rs.getInt("BG_ORDER"));
                bg.setRegDate(rs.getString("REG_DTTM"));
                bg.setEditDate(rs.getString("EDIT_DTTM"));
                list.add(bg);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
        return list;
    }

    public int insertBG(String name, int order) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pStmt = null;

        String sql = "INSERT INTO BOOKMARK_GROUP(NAME,BG_ORDER,REG_DTTM) VALUES (?,?,now())";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setInt(2, order);
            result = pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt);
        }
        return result;
    }

    public int updateBG(String name, int order, int id) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pStmt = null;

        String sql = "UPDATE BOOKMARK_GROUP SET NAME = ?, BG_ORDER = ?, EDIT_DTTM = now() WHERE ID = ?";

        try {
            conn = dbConn.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setInt(2, order);
            pStmt.setInt(3, id);
            result = pStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt);
        }
        return result;
    }

    public int deleteBG(int id) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pStmt = null;

        String sql = "DELETE FROM BOOKMARK_GROUP WHERE ID = ?";

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
