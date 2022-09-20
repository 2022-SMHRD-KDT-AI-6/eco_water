package model;

import java.sql.*;
import java.util.ArrayList;

public class DamDataDAO {
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Connection conn = null;

    public void connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://project-db-stu.ddns.net:3307/smai4", "smai4", "smai4");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DamDataDTO> getDamDataDTO() {
        ArrayList<DamDataDTO> arr = new ArrayList<DamDataDTO>();
        connect();
        try {
            String sql = "select * from DamData";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                DamDataDTO dto = new DamDataDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));
                arr.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return arr;
    }
}