module org.example.generatordemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens org.example.generatordemo to javafx.fxml;
    exports org.example.generatordemo;
}