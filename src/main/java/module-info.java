module com.example.aaroncorona_cs56_proj9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.aaroncorona_cs56_proj9 to javafx.fxml;
    exports com.example.aaroncorona_cs56_proj9.model;
    opens com.example.aaroncorona_cs56_proj9.model to javafx.fxml;
    exports com.example.aaroncorona_cs56_proj9.view;
    opens com.example.aaroncorona_cs56_proj9.view to javafx.fxml;
    exports com.example.aaroncorona_cs56_proj9.repository;
    opens com.example.aaroncorona_cs56_proj9.repository to javafx.fxml;
    exports com.example.aaroncorona_cs56_proj9.main;
    opens com.example.aaroncorona_cs56_proj9.main to javafx.fxml;
}