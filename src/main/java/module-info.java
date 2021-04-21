module application{
    requires javafx.controls;
    requires javafx.fxml;

    opens application.game to javafx.fxml;
    exports application.game;
        }