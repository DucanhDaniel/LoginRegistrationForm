module jfx.loginregisterusingmysql {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires htmlunit;

    opens jfx.loginregisterusingmysql to javafx.fxml;
    exports jfx.loginregisterusingmysql;
}