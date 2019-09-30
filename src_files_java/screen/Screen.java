package screen;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import system.Database;
import system.Window;

public class Screen {
	
	private Text[] menuText;
	private Text topText;
	protected int menuIndex;
	
	public Screen(Text[] menuText, Text topText) {
		this.menuText = menuText;
		this.topText = topText;
		menuIndex = 0;
	}
	
	protected void setMenu(int idx) {
		HBox mainPane = new HBox(10);
		for (int i = 0; i < menuText.length; i++) {
			mainPane.getChildren().add(menuText[i]);
		}
		mainPane.setAlignment(Pos.BOTTOM_CENTER);
		Window.getSp().getChildren().add(mainPane);
		setCurrentMenu(idx);
		
		if (topText != null) {
			HBox box = new HBox(5);
			box.getChildren().addAll(topText, new Text(""));
			box.setAlignment(Pos.TOP_RIGHT);
			topText.setFill(Color.DIMGREY);
			Window.getSp().getChildren().add(box);
		}
	}

	protected void setCurrentMenu(int idx) {
		if (idx == -1) {
			for (int i = 0; i < menuText.length; i++)
				menuText[i].setFill(Color.WHITE);
			return;
		}
		
		for (int i = 0; i < menuText.length; i++)
			menuText[i].setFill(Color.WHITE);
		menuText[idx].setFill(Color.DARKORANGE);
		
		menuIndex = idx;
	}
	
	protected boolean isOnline() {
		try {
			final URL url = new URL("http://www.google.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	protected void checkConnectivity() {
		if (!isOnline())
			new ErrorScreen("Connection Required", "Please connect to the\nnearest wifi connection\nto continue using services.");
		else if (Database.getDBConnection() == null)
			new ErrorScreen("Database Error", "Connection to mySQL database\nfailed. Please try again later!");
	}

	public Text[] getMenuText() {
		return menuText;
	}

	public void setMenuText(Text[] menuText) {
		this.menuText = menuText;
	}

	public Text getTopText() {
		return topText;
	}

	public void setTopText(Text topText) {
		this.topText = topText;
	}

	public int getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(int menuIndex) {
		this.menuIndex = menuIndex;
	}
	

}
