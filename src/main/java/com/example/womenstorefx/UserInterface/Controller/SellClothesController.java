package com.example.womenstorefx.UserInterface.Controller;

import com.example.womenstorefx.Products.Clothes;
import com.example.womenstorefx.Products.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SellClothesController {

    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";
    private Map<String, Integer> clothesNameToIdMap = new HashMap<>();
    @FXML
    private ChoiceBox<String> clothesChoiceBox;

    @FXML
    private Spinner<Integer> numberSpinner;

    @FXML
    private Button confirmSellClothesButton;

    @FXML
    public void initialize() {
        populateClothesChoiceBox();
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);

        numberSpinner.setValueFactory(valueFactory);

        numberSpinner.setEditable(true);
    }

    private void populateClothesChoiceBox() {

        String query = "SELECT id, name FROM clothes";
        try (Connection conn = DriverManager.getConnection(url, user,password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                clothesNameToIdMap.put(name, id);
                clothesChoiceBox.getItems().add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void sellClothes(MouseEvent event) {
        String selectedClothesName = clothesChoiceBox.getValue();
        int numberOfItemsToSell = numberSpinner.getValue();

        if (selectedClothesName == null || numberOfItemsToSell <= 0) {
            System.out.println("No item selected or invalid number of items to sell.");
            return;
        }

        Product productToSell = fetchProductFromDatabase(selectedClothesName);

        if (productToSell != null) {
            try {
                productToSell.sell(numberOfItemsToSell);
                System.out.println("Article sold successfully");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Product fetchProductFromDatabase(String selectedClothesName) {
        int id = 0;
        int nbItems = 0;

        // Corrected SQL query using placeholders
        String query = "SELECT id, nbItems FROM clothes WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the value of the placeholder with the selectedClothesName
            pstmt.setString(1, selectedClothesName);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // If the product is found, retrieve its details
            if (rs.next()) {
                id = rs.getInt("id");
                System.out.println("id :"+ id);
                nbItems = rs.getInt("nbItems");
                System.out.println("nbItems :"+ nbItems);

                // Since Product is abstract, you need to instantiate a subclass instead, such as Clothes
                Clothes clothes= new Clothes(id, selectedClothesName, nbItems);
                System.out.println(clothes.toString());
                System.out.println("miId :"+ clothes.getId());
                return clothes;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching product: " + e.getMessage());
        }

        return null; // Or handle accordingly if no product is found
    }

}
