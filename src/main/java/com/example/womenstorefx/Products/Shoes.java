package com.example.womenstorefx.Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Shoes extends Product {
    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";

    private int shoeSize;

    // Constructor for new shoes that are not in the database
    public Shoes(String name, double price, int nbItems, int shoeSize) {
        super(-1, name, price, nbItems);
        setShoeSize(shoeSize);
        addShoe(this.name, this.price, this.nbItems, this.shoeSize);
    }

    // Constructor for existing shoes (already in the database)
    public Shoes(int id, String name, double price, int nbItems, int shoeSize) {
        super(id, name, price, nbItems);
        this.shoeSize = shoeSize;
    }

    public int getShoeSize() {
        return shoeSize;
    }



    public void setShoeSize(int shoeSize) {
        if(shoeSize<36 || shoeSize>50){
            throw new IllegalArgumentException("Wrong shoe size !");
        }
        this.shoeSize = shoeSize;
    }

    @Override
    protected String getTableName() {
        return "shoes";
    }

    @Override
    public String toString() {
        return "Shoes {" +
                "shoeSize=" + shoeSize +
                ", number=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", nbItems=" + nbItems +
                '}';
    }

    public static void addShoe(String name, double price, int nbItems, int shoeSize) {
        String sql = "INSERT INTO shoes (name, price, nbItems, shoeSize) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, nbItems);
            pstmt.setInt(4, shoeSize);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyShoe(int id, String name, double price, int nbItems, int shoeSize) {
        String sql = "UPDATE shoes SET name = ?, price = ?, nbItems = ?, shoeSize = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, nbItems);
            pstmt.setInt(4, shoeSize);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteShoe(int id) {
        String sql = "DELETE FROM shoes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

