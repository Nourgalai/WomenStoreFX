package com.example.womenstorefx.UserInterface.Controllers.Sale;

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
        displayFinancialStatus();
    }

    public void displayFinancialStatus() {
        double totalSale = Store.getTotalIncome();
        double capital = Store.getCapital();
        double totalIncome = Store.getTotalIncome();

        totalSaleLabel.setText(String.format("$%.2f", totalSale));
        capitalLabel.setText(String.format("$%.2f", capital));
        totalIncomeLabel.setText(String.format("$%.2f", totalIncome));
    }

}
