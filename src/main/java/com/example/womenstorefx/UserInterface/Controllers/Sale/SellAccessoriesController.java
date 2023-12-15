package com.example.womenstorefx.UserInterface.Controllers.Sale;

import com.example.womenstorefx.Products.Accessories;
import com.example.womenstorefx.Products.Product;
import com.example.womenstorefx.Store;
import com.example.womenstorefx.UserInterface.Controllers.TableDisplay.AccessoriesTableController;
import com.example.womenstorefx.UserInterface.Controllers.TableDisplay.DisplayProductController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SellAccessoriesController {

    private static final String url = "jdbc:mysql://localhost:3306/womens_store";
    private static final String user = "root";
    private static final String password = "Nour2012";
    private Map<String, Integer> accessoriesNameToIdMap = new HashMap<>();
    @FXML
    private ChoiceBox<String> accessoriesChoiceBox;

    @FXML
    private Spinner<Integer> numberSpinner;

    @FXML
    private Button confirmSellaccessoriesButton;

    @FXML
    private Button goBackButton;

    @FXML
    public void initialize() {
        populateaccessoriesChoiceBox();
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);

        numberSpinner.setValueFactory(valueFactory);

        numberSpinner.setEditable(true);
    }

    private void populateaccessoriesChoiceBox() {

        String query = "SELECT id, name FROM accessories";
        try (Connection conn = DriverManager.getConnection(url, user,password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                accessoriesNameToIdMap.put(name, id);
                accessoriesChoiceBox.getItems().add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void sellAccessories(MouseEvent event) {
        String selectedaccessoriesName = accessoriesChoiceBox.getValue();
        int numberOfItemsToSell = numberSpinner.getValue();

        if (selectedaccessoriesName == null || numberOfItemsToSell <= 0) {
            System.out.println("No item selected or invalid number of items to sell.");
            return;
        }

        Product productToSell = fetchProductFromDatabase(selectedaccessoriesName);

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

        double saleAmount = productToSell.getPrice() * numberOfItemsToSell;
        Store.updateTotalIncome(saleAmount);
        Store.updateCapitalAfterSale(saleAmount);


        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DisplayProductController.class.getResource("/com/example/womenstorefx/successSale.fxml"));


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            stage.setTitle("Display Products");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Product fetchProductFromDatabase(String selectedaccessoriesName) {
        int id = 0;
        double price = 0.0;
        int nbItems = 0;

        // Corrected SQL query using placeholders
        String query = "SELECT id, price, nbItems FROM accessories WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the value of the placeholder with the selectedaccessoriesName
            pstmt.setString(1, selectedaccessoriesName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
                price = rs.getDouble("price");
                nbItems = rs.getInt("nbItems");

                // Since Product is abstract, you need to instantiate a subclass instead, such as accessories
                Accessories accessories= new Accessories(id, selectedaccessoriesName, price, nbItems);
                System.out.println(accessories.toString());
                System.out.println("miId :"+ accessories.getId());
                return accessories;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching product: " + e.getMessage());
        }
        return null;
    }

    @FXML
    void goBack(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AccessoriesTableController.class.getResource("/com/example/womenstorefx/accessoriesTable.fxml"));

            Parent root = loader.load();
            AccessoriesTableController accessoriesTableController = loader.getController();
            accessoriesTableController.populateTableView();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.setTitle("Display accessories");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
