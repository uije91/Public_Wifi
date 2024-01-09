package controller;

import java.sql.*;

public class dbConn {
    public Connection getConnection() {
        String url = "jdbc:mariadb://192.168.55.160:3306/zero_base";
        String userId = "sung131";
        String userPwd = "1234";

        Connection conn;

        try {
            // 1. 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");
            // 2. 커넥션 객체 생성
            conn = DriverManager.getConnection(url, userId, userPwd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
    public void close(Connection conn) {
        // 6. 객체 연결 해제(conn)
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(Connection conn, PreparedStatement pStmt) {
        // 6. 객체 연결 해제(conn, stmt)
        try {
            if (pStmt != null && !pStmt.isClosed()) {
                pStmt.close();
            }
            if (conn != null && (!conn.isClosed())) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(Connection conn, PreparedStatement pStmt, ResultSet rs) {
        // 6. 객체 연결 해제(conn, stmt, rs)
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pStmt != null && !pStmt.isClosed()) {
                pStmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
