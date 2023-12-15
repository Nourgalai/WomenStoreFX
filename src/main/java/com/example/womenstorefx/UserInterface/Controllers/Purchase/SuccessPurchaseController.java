package com.example.womenstorefx.UserInterface.Controllers.Purchase;

import com.example.womenstorefx.Store;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SuccessPurchaseController implements Initializable  {
    @FXML
    private Label capitalLabel;

    @FXML
    private Label purchaseLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayFinancialStatus();
    }

    public void displayFinancialStatus() {
        double capital = Store.getCapital();
        double purchase = Store.getTotalCost();

        capitalLabel.setText(String.format("$%.2f", capital));
        purchaseLabel.setText(String.format("$%.2f", purchase));
    }

}
