package com.example.womenstorefx.UserInterface;

import com.example.womenstorefx.Products.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class ProductButtonPanel {
    @FXML private Button discountButton;
    @FXML
    private Button stopDiscountButton;
    @FXML private Button purchaseButton;
    @FXML private Button sellButton;

    @FXML private HBox buttonPanel;
    @FXML private TableView<Product> productTable;

    public ProductButtonPanel(TableView<ProductModel> tableView) {
        setupButtons();
        buttonPanel = new HBox(10, discountButton, stopDiscountButton, purchaseButton, sellButton);
    }

    private void setupButtons() {
        discountButton.setOnAction(event -> applyDiscount());
        stopDiscountButton.setOnAction(event -> stopDiscount());
        purchaseButton.setOnAction(event -> purchaseItems());
        sellButton.setOnAction(event -> sellItems());
    }

    private void applyDiscount() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            // Example: applying a 10% discount
            selectedProduct.applyDiscount(10);
            productTable.refresh();
        }
    }

    private void stopDiscount() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct.stopDiscount();
            productTable.refresh();
        }
    }

    private void purchaseItems() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            // Example: purchasing 5 items at a specific price
            selectedProduct.purchase(5, selectedProduct.getPrice() * 0.9); // 10% off for example
            productTable.refresh();
        }
    }

    private void sellItems() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            // Example: selling 3 items
            selectedProduct.sell(3);
            productTable.refresh();
        }
    }

    private void showProducts(){

    }

    public HBox getButtonPanel() {
        return buttonPanel;
    }
}

