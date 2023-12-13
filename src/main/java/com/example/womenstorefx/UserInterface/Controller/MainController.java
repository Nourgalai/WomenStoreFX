package com.example.womenstorefx.UserInterface.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button showProductButton;
    @FXML
    private Button applyDiscountButton;

    @FXML
    private TextField greenting;

    @FXML
    private Button stopDiscountButton;

    public void showProducts(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DisplayProductController.class.getResource("/com/example/womenstorefx/showProducts.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            // Configure the stage as needed
            stage.setTitle("Display Products");

            // Show the new stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void applyDiscount(MouseEvent mouseEvent) {
        System.out.println("Hello world !");

    }

    public void stopDiscount(MouseEvent mouseEvent) {
    }

}
