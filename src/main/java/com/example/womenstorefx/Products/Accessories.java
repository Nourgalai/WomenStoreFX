package com.example.womenstorefx.Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Accessories extends Product {
    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";
    public Accessories(int id, String name, double price, int nbItems) {
        super(id, name, price, nbItems);
    }


    @Override
    protected String getTableName() {
        return "accessories";
    }

    @Override
    public String toString() {
        return "Accessories {" +
                "number=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", nbItems=" + nbItems +
                '}';
    }

    public static void addAccessory(String name, double price, int nbItems) {
        String sql = "INSERT INTO accessories (name, price, nbItems) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, nbItems);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyAccessory(int id, String name, double price, int nbItems) {
        String sql = "UPDATE accessories SET name = ?, price = ?, nbItems = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, nbItems);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAccessory(int id) {
        String sql = "DELETE FROM accessories WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

