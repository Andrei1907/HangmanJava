package database;

import java.sql.*;

public class PlayerDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into users (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from users where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : -1;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from users where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public Integer getHighscore(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select highscore from users where id='" + id + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public void updateHighscore(int id, int score) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE users SET highscore = (?) WHERE id = (?)")) {
            pstmt.setInt(1, score);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }
}
