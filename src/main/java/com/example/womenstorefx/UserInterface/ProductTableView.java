package com.example.womenstorefx.UserInterface;

import com.example.womenstorefx.Products.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductTableView {
    private TableView<ProductModel> tableView;
    private ObservableList<ProductModel> products;

    public ProductTableView() {
        tableView = new TableView<>();
        products = FXCollections.observableArrayList();
        setupTableView();
    }

    private void setupTableView() {
        // Define columns based on the ProductModel properties
        TableColumn<ProductModel, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ProductModel, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductModel, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ProductModel, Number> nbItemsColumn = new TableColumn<>("Number of Items");
        nbItemsColumn.setCellValueFactory(new PropertyValueFactory<>("nbItems"));

        // Add columns to the TableView
        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(priceColumn);
        tableView.getColumns().add(nbItemsColumn);
    }
    public TableView<ProductModel> getTableView() {
        return tableView;
    }

    public void setProducts(ObservableList<ProductModel> products) {
        this.products = products;
        tableView.setItems(products);
    }

    // Fetch data from the database and convert it into ProductModel objects
    public static ObservableList<ProductModel> loadProductsFromDatabase(String tableName) {
        ObservableList<ProductModel> productList = FXCollections.observableArrayList();
        productList.addAll(ProductModel.getAllProducts(tableName));
        return productList;
    }
}
