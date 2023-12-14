package com.example.womenstorefx.Products;

import com.example.womenstorefx.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Clothes extends Product {
    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";

    private int size;
    public Clothes(int id, String name, double price, int nbItems, int size) {
        super(id, name, price, nbItems);
        setSize(size);
    }

    public Clothes(int id, String name, double price, int nbItems) {
        super(id, name, price, nbItems);
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if(size%2!=0 || size<34 || size>54){
            throw new IllegalArgumentException("Wrong size!");
        }
        this.size = size;
    }

    @Override
    protected String getTableName() {
        return "clothes";
    }

    @Override
    public String toString() {
        return "Clothes {" +
                "size=" + size +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", nbItems=" + nbItems +
                '}';
    }

    /*public void purchase(int nbItems, double purchasePrice){
        if (purchasePrice>= this.getPrice()){
            throw new IllegalArgumentException("Purchase price must be less than the selling price");
        }

        double totalCost = purchasePrice * nbItems;
        Store.updateCapitalAndCostAfterPurchase(totalCost);
        this.setNbItems(this.getNbItems()+nbItems);
    }*/


    public static void addProduct(String name, double price, int nbItems, int size) {
        String sql = "INSERT INTO clothes (name, price, nbItems, size) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, nbItems);
            pstmt.setInt(4, size);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void modifyClothes(int id, String name, double price, int nbItems, int size) {
        String sql = "UPDATE clothes SET name = ?, price = ?, nbItems = ?, size = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, nbItems);
            pstmt.setInt(4, size);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteClothes(int id) {
        String sql = "DELETE FROM clothes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}

