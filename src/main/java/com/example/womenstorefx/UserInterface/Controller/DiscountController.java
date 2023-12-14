package com.example.womenstorefx.UserInterface.Controller;

import com.example.womenstorefx.Products.Accessories;
import com.example.womenstorefx.Products.Clothes;
import com.example.womenstorefx.Products.Product;
import com.example.womenstorefx.Products.Shoes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DiscountController {
    @FXML
    private Button applyDiscountButton;

    @FXML
    private ChoiceBox<String> discountCategoryChoiceBox;

    @FXML
    private Button discountGoBackButton;

    @FXML
    private ChoiceBox<String> discountItemChoiceBox;

    @FXML
    private Spinner<Integer> percentageSpinner;

    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";

    @FXML
    public void initialize() {
        discountCategoryChoiceBox.getItems().addAll("Clothes", "Shoes", "Accessories");

        discountCategoryChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            updateItemChoiceBox(newVal);
        });

        discountCategoryChoiceBox.getSelectionModel().selectFirst();

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 90, 5, 5);
        percentageSpinner.setValueFactory(valueFactory);
        percentageSpinner.setEditable(true);
    }

    private void updateItemChoiceBox(String category) {
        discountItemChoiceBox.getItems().clear();

        String query = "SELECT name FROM " + category.toLowerCase();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                discountItemChoiceBox.getItems().add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void applyDiscount(MouseEvent event) {
        String selectedCategory = discountCategoryChoiceBox.getValue();
        String selectedItem = discountItemChoiceBox.getValue();
        int discountPercentage = percentageSpinner.getValue();

        if (selectedCategory == null || selectedItem == null || discountPercentage <= 0) {
            // handle validation error
            return;
        }
        applyDiscountToItem(selectedCategory, selectedItem, discountPercentage);
    }

    private void applyDiscountToItem(String selectedCategory, String selectedItem, int discountPercentage) {

        Product product = fetchProductFromDatabase(selectedCategory, selectedItem);
        int id = product.getId();
        product.applyDiscount(id,discountPercentage, selectedCategory);
    }



    private Product fetchProductFromDatabase(String selectedCategory, String selectedItem) {
        int id = 0;
        double price = 0.0;
        int nbItems = 0;

        // Corrected SQL query using placeholders
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
                //product.toString();
                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching product: " + e.getMessage());
        }

        return null;
    }

    @FXML
    void discountGoBack(MouseEvent event) {
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
