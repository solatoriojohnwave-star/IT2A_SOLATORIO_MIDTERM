package com.yourdomain.dao;

import com.yourdomain.model.ScenarioEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public void addTicket(String customerName, String movieTitle, String seatNo, double price) {
        String sql = "INSERT INTO tickets(customer_name, movie_title, seat_no, price) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerName);
            pstmt.setString(2, movieTitle);
            pstmt.setString(3, seatNo);
            pstmt.setDouble(4, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding ticket: " + e.getMessage());
        }
    }

    public List<ScenarioEntity> getAllTickets() {
        List<ScenarioEntity> tickets = new ArrayList<>();
        String sql = "SELECT id, customer_name, movie_title, seat_no, price FROM tickets";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tickets.add(new ScenarioEntity(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getString("movie_title"),
                        rs.getString("seat_no"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tickets: " + e.getMessage());
        }
        return tickets;
    }
}