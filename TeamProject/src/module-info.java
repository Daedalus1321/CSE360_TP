module FoundationCodeBaseline2 {
	requires javafx.controls;
	requires java.sql;
	requires javafx.base;
	
	opens applicationMainMethodClasses to javafx.graphics, javafx.fxml;
}
