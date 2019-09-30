package system;

import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class Configuration {

//	// PI MACHINE
//	public static KeyCode key1 = KeyCode.LEFT;
//	public static KeyCode key2 = KeyCode.RIGHT;
//	public static KeyCode key3 = KeyCode.UP;
//	public static KeyCode key4 = KeyCode.DOWN;
//	public static KeyCode key5 = KeyCode.CONTROL;

	// PC TESTING
	 public static KeyCode key1 = KeyCode.A;
	 public static KeyCode key2 = KeyCode.S;
	 public static KeyCode key3 = KeyCode.D;
	 public static KeyCode key4 = KeyCode.F;
	 public static KeyCode key5 = KeyCode.E;

	protected static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	// OSWEGO MACHINE
//	protected static final String DB_CONNECTION = "jdbc:mysql://pi.cs.oswego.edu:3306/wight6?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//	protected static final String DB_USER = "wight6";
//	protected static final String DB_PASSWORD = "wight6";
	
	
	// LOCAL MACHINE
//	protected static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/dare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//	protected static final String DB_USER = "root";
//	protected static final String DB_PASSWORD = "------";

	// FREE MYSQL DB
	protected static final String DB_CONNECTION = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9288643";
	protected static final String DB_USER = "sql9288643";
	protected static final String DB_PASSWORD = "EKRKQqZeyK";
	
	public static void setStage(String style) {
		if (style.equals("mobile")) {
			Window.getStage().setFullScreen(true);
			Window.getStage().getScene().setCursor(Cursor.NONE);
			key1 = KeyCode.LEFT;
			key2 = KeyCode.RIGHT;
			key3 = KeyCode.UP;
			key4 = KeyCode.DOWN;
			key5 = KeyCode.CONTROL;
		}
		
		Window.getStage().getScene().setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.K)
				System.exit(0);
		});
		
		Window.getStage().getScene().setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY)
				System.exit(0);
		});
	}

}
