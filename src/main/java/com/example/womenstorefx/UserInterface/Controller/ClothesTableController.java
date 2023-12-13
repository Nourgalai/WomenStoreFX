package com.example.womenstorefx.UserInterface.Controller;

import com.example.womenstorefx.UserInterface.ProductModel;
import com.example.womenstorefx.UserInterface.ProductTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClothesTableController implements Initializable {
    @FXML
    private Button clothesPurchaseButton;

    @FXML
    private Button clothesSellButton;

    @FXML
    private TableView<ProductModel> clothesTableView;

    @FXML
    private TableColumn<ProductModel, Integer> idColumn;

    @FXML
    private TableColumn<ProductModel, String> nameColumn;

    @FXML
    private TableColumn<ProductModel, Integer> nbItemsColumns;

    @FXML
    private TableColumn<ProductModel, Double> priceColumn;

    /*@FXML
    private TableColumn<ProductModel, Integer>sizeColumns;*/

    @FXML
    void productPurchase(MouseEvent event) {

    }

    @FXML
    void productSell(MouseEvent event) {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DisplayProductController.class.getResource("/com/example/womenstorefx/SellingProcess1.fxml"));
            Parent root = loader.load();

            // Change the scene on the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.setTitle("Clothes Sale");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        nbItemsColumns.setCellValueFactory(new PropertyValueFactory<>("nbItems"));
        //sizeColumns.setCellValueFactory(new PropertyValueFactory<>("size"));


        populateTableView();
    }

    public void populateTableView() {
        ObservableList<ProductModel> clothesData = ProductTableView.loadProductsFromDatabase("clothes");
        clothesTableView.setItems(clothesData);
    }
}
