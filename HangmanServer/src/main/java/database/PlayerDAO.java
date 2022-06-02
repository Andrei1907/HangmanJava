package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public Map<String, Integer> getTopPlayers(int k) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT name, highscore FROM hangman.users ORDER BY highscore DESC LIMIT " + k)) {
            Map<String, Integer> map = new TreeMap<>();
            while(rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
            return map;
        }

    }
}
