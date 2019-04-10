package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarClass extends MenuBar {
	
	MenuItem Load = new MenuItem("Load Student Data");
	MenuItem Save = new MenuItem("Save Student Data");
	MenuItem Exit = new MenuItem("Exit");
	MenuItem About = new MenuItem("About");
	
	
	public MenuBarClass() {
		//Adding File to the menu bar
		Menu menuFile = new Menu("File");
		menuFile.getItems().add(Load);
		menuFile.getItems().add(Save);
		menuFile.getItems().add(Exit);
		this.getMenus().add(menuFile);


		//Adding Help to the menu bar
		Menu menuHelp = new Menu("Help");
		menuHelp.getItems().add(About);
		this.getMenus().add(menuHelp);
	}
	
	public void addExitHandler(EventHandler<ActionEvent> handler) {
		Exit.setOnAction(handler);
	}
	
	public void addAboutHandler(EventHandler<ActionEvent> handler) {
		About.setOnAction(handler);
	}

	public void addSaveHandler(EventHandler<ActionEvent> handler) {
		Save.setOnAction(handler);
	}

	public void addLoadHandler(EventHandler<ActionEvent> handler) {
		Load.setOnAction(handler);
	}
	
}