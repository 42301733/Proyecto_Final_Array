module laboratorio.galeriadeimagenesarray {
    requires javafx.controls;
    requires javafx.fxml;

    opens laboratorio.galeriadeimagenesarray to javafx.fxml;
    exports laboratorio.galeriadeimagenesarray;
}
