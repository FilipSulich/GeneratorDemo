module org.example.generatordemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens org.example.generatordemo to javafx.fxml;
    exports org.example.generatordemo;
    exports org.example.generatordemo.GUI;
    opens org.example.generatordemo.GUI to javafx.fxml;
    exports org.example.generatordemo.Customer;
    opens org.example.generatordemo.Customer to javafx.fxml;
    exports org.example.generatordemo.Test;
    opens org.example.generatordemo.Test to javafx.fxml;
}