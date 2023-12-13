package com.example.womenstorefx;

import com.example.womenstorefx.Products.Accessories;
import com.example.womenstorefx.Products.Clothes;
import com.example.womenstorefx.Products.Product;
import com.example.womenstorefx.Products.Shoes;
import com.example.womenstorefx.UserInterface.ProductButtonPanel;
import com.example.womenstorefx.UserInterface.ProductTableView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Women's Store Management");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void stopDiscount() {

    }

    private void applyDiscount() {
    }

    private void showProducts() {
    }

    public static void main(String[] args) {
        launch();
    }
}