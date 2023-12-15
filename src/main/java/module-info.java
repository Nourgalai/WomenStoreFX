module com.example.womenstorefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.womenstorefx to javafx.fxml;
    exports com.example.womenstorefx;
    exports com.example.womenstorefx.Products;
    opens com.example.womenstorefx.Products to javafx.fxml;
    exports com.example.womenstorefx.UserInterface to javafx.fxml;
    opens com.example.womenstorefx.UserInterface to javafx.fxml, javafx.base;
    exports com.example.womenstorefx.UserInterface.Controllers to javafx.fxml;
    opens com.example.womenstorefx.UserInterface.Controllers to javafx.fxml;
    exports com.example.womenstorefx.UserInterface.Controllers.Discount to javafx.fxml;
    opens com.example.womenstorefx.UserInterface.Controllers.Discount to javafx.fxml;
    exports com.example.womenstorefx.UserInterface.Controllers.Sale to javafx.fxml;
    opens com.example.womenstorefx.UserInterface.Controllers.Sale to javafx.fxml;
    exports com.example.womenstorefx.UserInterface.Controllers.Purchase to javafx.fxml;
    opens com.example.womenstorefx.UserInterface.Controllers.Purchase to javafx.fxml;
    exports com.example.womenstorefx.UserInterface.Controllers.TableDisplay to javafx.fxml;
    opens com.example.womenstorefx.UserInterface.Controllers.TableDisplay to javafx.fxml;
}