module SudokuAlgorytmGenetyczny {
    requires javafx.controls;
    requires javafx.fxml;
    requires gen_alg;

    exports progui;
    opens progui to javafx.fxml;


}