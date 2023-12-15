package com.example.womenstorefx.UserInterface.Controllers.Discount;

import com.example.womenstorefx.Products.Accessories;
import com.example.womenstorefx.Products.Clothes;
import com.example.womenstorefx.Products.Product;
import com.example.womenstorefx.Products.Shoes;
import com.example.womenstorefx.UserInterface.Controllers.TableDisplay.DisplayProductController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class StopDiscountController {

    @FXML
    private Button stopDiscountButton;

    @FXML
    private ChoiceBox<String> stopDiscountCategoryChoiceBox;

    @FXML
    private Button stopDiscountGoBackButton;

    @FXML
    private ChoiceBox<String> stopDiscountItemChoiceBox;

    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";

    @FXML
    public void initialize() {
        stopDiscountCategoryChoiceBox.getItems().addAll("Clothes", "Shoes", "Accessories");
        // Add listener to the category choice box
        stopDiscountCategoryChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            updateItemChoiceBox(newVal);
        });
    }

    private void updateItemChoiceBox(String category) {
        stopDiscountItemChoiceBox.getItems().clear();
        String query = "SELECT name FROM " + category.toLowerCase();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                stopDiscountItemChoiceBox.getItems().add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void stopDiscount(MouseEvent event) {
        String selectedCategory = stopDiscountCategoryChoiceBox.getValue();
        String selectedItem = stopDiscountItemChoiceBox.getValue();

        if (selectedItem == null || selectedCategory == null) {
            return;
        }
        stopDicountonItem(selectedCategory,selectedItem);
    }

    private void stopDicountonItem(String selectedCategory, String selectedItem) {
        Product product = fetchProductFromDatabase(selectedCategory, selectedItem);
        int id = product.getId();
        product.stopDiscount(id,selectedCategory);
    }

    private Product fetchProductFromDatabase(String selectedCategory, String selectedItem) {
        int id = 0;
        double price = 0.0;
        int nbItems = 0;

        String query = "SELECT id, price, nbItems FROM " + selectedCategory + " WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, selectedItem);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
                System.out.println("myId: " + id);
                price = rs.getDouble("price");
                System.out.println("myPrice: " + price);
                nbItems = rs.getInt("nbItems");
                System.out.println("myNbItems: " + nbItems);

                Product product = null;
                if ("clothes".equalsIgnoreCase(selectedCategory)) {
                    product = new Clothes(id, selectedItem, price, nbItems);
                    product.toString();
                } else if ("shoes".equalsIgnoreCase(selectedCategory)) {
                    product = new Shoes(id, selectedItem, price, nbItems);
                } else if ("accessories".equalsIgnoreCase(selectedCategory)) {
                    product = new Accessories(id, selectedItem, price, nbItems);
                }
                product.toString();
                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching product: " + e.getMessage());
        }

        return null;
    }

    @FXML
    void stopDiscountGoBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DisplayProductController.class.getResource("/com/example/womenstorefx/sample.fxml"));


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            stage.setTitle("Women's Store Management");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
