package com.example.womenstorefx.UserInterface.Controllers.Purchase;

import com.example.womenstorefx.Store;
import com.example.womenstorefx.UserInterface.Controllers.TableDisplay.DisplayProductController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PurchaseNewItemController {

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField nbItemsTextField;

    @FXML
    private TextField sizeTextField;

    @FXML
    private Button puchaseNewButton;

    @FXML
    private Button goBackButton;

    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";

    @FXML
    public void initialize() {
        categoryChoiceBox.getItems().addAll("Clothes", "Shoes", "Accessories");
    }

    @FXML
    void purchaseNew(MouseEvent event) {
        String category = categoryChoiceBox.getValue();
        String id = idTextField.getText();
        String name = nameTextField.getText();
        String price = priceTextField.getText();
        String nbItems = nbItemsTextField.getText();
        String size = sizeTextField.getText();

        String query = "INSERT INTO " + category.toLowerCase() +
                " (id, name, price, nbItems, size, original_price) VALUES (?, ?, ?, ?, ?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.setString(2, name);
            pstmt.setDouble(3, Double.parseDouble(price));
            pstmt.setInt(4, Integer.parseInt(nbItems));
            pstmt.setInt(5, Integer.parseInt(size));
            pstmt.setDouble(6, Double.parseDouble(price));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("New item added successfully.");
            } else {
                System.out.println("No item was added to the database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Number format error: " + e.getMessage());
        }

        double priceNum = Double.parseDouble(price);
        int nbItemsNum = Integer.parseInt(nbItems);
        double purchaseCost = priceNum * nbItemsNum;
        Store.updateCapitalAndCostAfterPurchase(purchaseCost);



        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DisplayProductController.class.getResource("/com/example/womenstorefx/successPurchase.fxml"));


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            stage.setTitle("Purchase Success");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DisplayProductController.class.getResource("/com/example/womenstorefx/clothesTable.fxml"));


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            stage.setTitle("Display clothes");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
