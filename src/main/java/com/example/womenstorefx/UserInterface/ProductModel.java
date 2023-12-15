package com.example.womenstorefx.UserInterface;

import javafx.beans.property.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty nbItems;
    private SimpleDoubleProperty originalPrice;


    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";

    public ProductModel(int id, String name, double price, int nbItems, double originalPrice) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.nbItems = new SimpleIntegerProperty(nbItems);
        this.originalPrice = new SimpleDoubleProperty(originalPrice);
    }

    public static List<ProductModel>  getAllProducts(String tableName) {
        List<ProductModel> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Fetch clothes
            String sql = "SELECT * FROM "+ tableName;
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new ProductModel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("nbItems"),
                            rs.getDouble("original_price")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public int getId() { return id.get(); }
    public String getName() { return name.get(); }
    public double getPrice() { return price.get(); }
    public int getNbItems(){
        return nbItems.get();
    }
    public double getOriginalPrice() {
        return originalPrice.get();
    }


    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public DoubleProperty priceProperty() { return price; }
    public IntegerProperty nbItemsProperty() { return nbItems; }
    public DoubleProperty originalPriceProperty() { return originalPrice; }
}
