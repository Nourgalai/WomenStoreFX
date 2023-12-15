package com.example.womenstorefx.UserInterface.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.sql.*;
import java.util.function.UnaryOperator;

public class PurchaseExistedItemController {
    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private Button goBackButton;

    @FXML
    private Button purchaseButton;

    @FXML
    private ChoiceBox<String> itemChoiceBox;

    @FXML
    private Button newItemButton;

    @FXML
    private TextField numberPurchasedInput;

    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";

    @FXML
    public void initialize() {
        categoryChoiceBox.getItems().addAll("Clothes", "Shoes", "Accessories");
        categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            updateItemChoiceBox(newVal);
        });
    }

    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("[0-9]*")) {  // Regex to only allow integer numbers
            return change;
        }
        return null;
    };
    TextFormatter<Integer> textFormatter = new TextFormatter<>(
            new IntegerStringConverter(), // Converter to handle the integer conversion
            0,                            // Default value
            integerFilter                 // Filter to apply
    );

    private void updateItemChoiceBox(String category) {
        itemChoiceBox.getItems().clear();
        String query = "SELECT name FROM " + category.toLowerCase();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                itemChoiceBox.getItems().add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void purchase(MouseEvent event) {
        String selectedCategory = categoryChoiceBox.getValue();
        String selectedItem = itemChoiceBox.getValue();
        int numberOfItemsToPurchase = Integer.parseInt(numberPurchasedInput.getText());

        String query = "UPDATE " + selectedCategory + " SET nbItems = nbItems + ? WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, numberOfItemsToPurchase);
            pstmt.setString(2, selectedItem);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Items purchased successfully and database updated.");
            } else {
                System.out.println("No rows affected. Check if the item name is correct.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating the database: " + e.getMessage());
        }
    }

    @FXML
    void newItemPurchase(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DisplayProductController.class.getResource("/com/example/womenstorefx/purchaseNew.fxml"));


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            stage.setTitle("Purchase New Items");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void goBack(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClothesTableController.class.getResource("/com/example/womenstorefx/clothesTable.fxml"));

            Parent root = loader.load();
            ClothesTableController clothesTableController = loader.getController();
            clothesTableController.populateTableView();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.setTitle("Purchase Existant Item");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
