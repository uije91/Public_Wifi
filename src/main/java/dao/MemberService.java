package dao;

import controller.dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberService {
    dbConn dbConn = new dbConn();
    public void dbInsert() {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        String sql = "insert into member(id, password, name, phone, address) " +
                "values (?,?,?,?,?);";
        try {
            conn = dbConn.getConnection();

            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, "test02");
            pStmt.setString(2, "1234");
            pStmt.setString(3, "둘리");
            pStmt.setString(4, "010-5678-1234");
            pStmt.setString(5, "서울시 성동구");

            int affected = pStmt.executeUpdate();
            if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
    }
    public void dbUpdate() {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        String sql = "UPDATE member SET password = ? WHERE id = ?";

        try {
            conn = dbConn.getConnection();

            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, "test");
            pStmt.setString(2, "test02");

            int affected = pStmt.executeUpdate();
            if (affected > 0) {
                System.out.println("수정 성공");
            } else {
                System.out.println("수정 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
    }
    public void dbDelete() {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        String sql = "DELETE FROM member WHERE id = ?";

        try {
            conn = dbConn.getConnection();

            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, "test02");

            int affected = pStmt.executeUpdate();
            if (affected > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
    }
    public void dbSelect() {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        try {
            conn = dbConn.getConnection();
            String sql = "select id, password, name, phone, address from member";

            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                System.out.println(id + ", " + password + ", " + name + ", " + phone + ", " + address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConn.close(conn, pStmt, rs);
        }
    }
}
