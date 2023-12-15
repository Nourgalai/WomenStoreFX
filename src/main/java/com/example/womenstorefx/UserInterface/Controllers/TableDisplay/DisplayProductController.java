package com.example.womenstorefx.UserInterface.Controllers.TableDisplay;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayProductController {
    @FXML
    private Button AccessoriesButton;

    @FXML
    private Button ShoesButton;

    @FXML
    private Button clothesButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField greenting;

    @FXML
    void showAccesories(MouseEvent event) {

    }

    @FXML
    void showClothes(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClothesTableController.class.getResource("/com/example/womenstorefx/clothesTable.fxml"));

            Parent root = loader.load(); // Load the FXML file
            ClothesTableController clothesTableController = loader.getController();
            clothesTableController.populateTableView();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.setTitle("Display Clothes");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showShoes(MouseEvent event) {

    }


    @FXML
    void goBack(MouseEvent event) {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DisplayProductController.class.getResource("/com/example/womenstorefx/sample.fxml"));
            Parent root = loader.load();

            // Change the scene on the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.setTitle("Women's Store Management");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
