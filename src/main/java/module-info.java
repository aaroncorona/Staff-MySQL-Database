module com.example.aaroncorona_cs56_proj9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.aaroncorona_cs56_proj9 to javafx.fxml;
    exports com.example.aaroncorona_cs56_proj9;
}