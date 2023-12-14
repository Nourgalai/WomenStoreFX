package com.example.womenstorefx.UserInterface.Controller;

import com.example.womenstorefx.Store;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SuccessSaleController implements Initializable  {
    @FXML
    private Label capitalLabel;

    @FXML
    private Label totalIncomeLabel;

    @FXML
    private Label totalSaleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ... other initialization code ...
        displayFinancialStatus();
    }

    public void displayFinancialStatus() {
        double totalSale = Store.getTotalIncome();
        double capital = Store.getCapital();
        double totalIncome = Store.getTotalIncome();

        // Assuming this method is called from the JavaFX Application thread;
        // if not, wrap the updates in Platform.runLater()
        totalSaleLabel.setText(String.format("$%.2f", totalSale));
        capitalLabel.setText(String.format("$%.2f", capital));
        totalIncomeLabel.setText(String.format("$%.2f", totalIncome));
    }

}
