package com.kamilmade.persistance;

import com.kamilmade.pojos.Seat;
import org.joda.time.DateTime;

import java.sql.*;
import javax.sql.DataSource;
//http://www.mkyong.com/spring/maven-spring-jdbc-example/
public class SeatDaoImpl implements SeatDao{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Seat seat){

        String sql = "INSERT INTO CUSTOMER " +
                "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, seat.getId());
            ps.setInt(2, seat.getRow());
            ps.setInt(3, seat.getSeat());
           // ps.setTimestamp(4, now.get);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public Seat findById(int custId){
        return new Seat();
    /*    String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, custId);
            Seat seat = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                seat = new Seat(
                        rs.getInt("CUST_ID"),
                        rs.getString("NAME"),
                        rs.getInt("Age")
                );
            }
            rs.close();
            ps.close();
            return seat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }*/
    }

}
