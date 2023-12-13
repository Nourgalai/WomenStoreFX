package com.example.womenstorefx.Products;

import com.example.womenstorefx.Discount;
import com.example.womenstorefx.Store;

import java.sql.*;

public abstract class Product implements Discount, Comparable<Product>{

    protected int id;
    private static int lastId = 0;
    protected String name;
    protected double price;
    protected int nbItems;
    protected boolean isDiscounted = false;
    protected double originalPrice;

    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";

    static {
        initializeLastId();
    }

    private static void initializeLastId() {
        // Query the database to find the maximum ID
        String sql = "SELECT MAX(id) FROM shoes AS max_ids";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Product(int id, String name, double price, int nbItems){
        if (price<0){
            throw new IllegalArgumentException("Negative price!");
        }
        if(nbItems<0){
            throw new IllegalArgumentException("Negative number of items!");
        }
        this.id = ++lastId;
        this.name=name;
        this.price =price;
        this.nbItems=nbItems;
    }

    @Override
    public int compareTo(Product otherProduct){
        return Double.compare(this.price, otherProduct.getPrice());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price <0){
            throw new IllegalArgumentException("Negative price!");
        }
        this.price = price;
    }

    protected abstract String getTableName();
    public void setNbItems(int nbItems) {
        if(nbItems<0){
            throw new IllegalArgumentException("Negative number of items!");
        }
        this.nbItems = nbItems;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public int getNbItems() {
        return nbItems;
    }


    @Override
    public String toString() {
        return "Product {" +
                "number=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", nbItems=" + nbItems +
                '}';
    }

    @Override
    public void applyDiscount(double discountPercentage){
        if(!isDiscounted){
            originalPrice=price; // To save the original price for when we remove the discount
            price -= price * (discountPercentage/100.0);
            isDiscounted = true;
        }
    }

    @Override
    public void stopDiscount(){
        if(isDiscounted){
            price = originalPrice; // Revert to the original price
            isDiscounted = false;
        }
    }

    public void purchase(int nbItems, double purchasePrice){
        if (purchasePrice < 0) {
            throw new IllegalArgumentException("Purchase price cannot be negative.");
        }
        if (purchasePrice>= this.getPrice()){
            throw new IllegalArgumentException("Purchase price must be less than the selling price");
        }
        if (nbItems < 0) {
            throw new IllegalArgumentException("Number of items purchased cannot be negative.");
        }

        double totalCost = purchasePrice * nbItems;
        Store.updateCapitalAndCostAfterPurchase(totalCost);
        this.setNbItems(this.getNbItems()+nbItems);
    }

    public void sell(int nbItems){
        if(this.getNbItems()<nbItems){
            throw new IllegalArgumentException(this.getName() + " : Product Unavailable");
        }
        if (nbItems < 0) {
            throw new IllegalArgumentException("Number of items sold cannot be negative.");
        }
        this.nbItems-= nbItems;
        updateDatabaseStock(this.getId(), this.nbItems, getTableName());
        double income = this.getPrice() * nbItems;
        Store.updateCapitalAfterSale(income);
        this.setNbItems(this.getNbItems() - nbItems);
    }

    private void updateDatabaseStock(int productId, int nbItems, String productTable) {
        String sql = "UPDATE " + productTable + " SET nbItems = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, nbItems);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating the database: " + e.getMessage());
        }

    }

}

