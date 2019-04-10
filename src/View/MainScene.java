package View;


import javafx.beans.value.ChangeListener;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;

public class MainScene extends VBox {

	ProfileClass profile = new ProfileClass();
	ModuleMarksClass moduleMarks = new ModuleMarksClass();
	ResultsClass results = new ResultsClass();
	
	
	MenuBarClass menuBar = new MenuBarClass();
	TabPane tbpContent = new TabPane();
		
	public MainScene() {
		//Setting up the tab pane
		tbpContent.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//Adding Profile page to tab pane
		Tab tabProfile = new Tab();
		tabProfile.setText("Create Profile");
		tabProfile.setContent(profile);
		tbpContent.getTabs().add(tabProfile);

		//Adding Profile page to tab pane
		Tab tabModuleMarks = new Tab();
		tabModuleMarks.setText("Input Marks");
		tabModuleMarks.setContent(moduleMarks);
		tbpContent.getTabs().add(tabModuleMarks);

		//Adding Profile page to tab pane
		Tab tabResults = new Tab();
		tabResults.setText("Overview Results");
		tabResults.setContent(results);
		tbpContent.getTabs().add(tabResults);
				
		//Adding tbpContent to the scene
		this.getChildren().add(menuBar);
		this.getChildren().add(tbpContent);	
	}
	
	public ProfileClass getProfileClass() {
		return profile;
	}
	
	public ModuleMarksClass getModuleMarksClass() {
		return moduleMarks;
	}
	
	public ResultsClass getResultsClass() {
		return results;
	}
	
	public MenuBarClass getMenuBarClass() {
		return menuBar;
	}
	
	public void setFocus(int index) {
		tbpContent.getSelectionModel().select(index);
	}
	
	
	public void addOverviewListener(ChangeListener<Number> listener) {
		tbpContent.getSelectionModel().selectedIndexProperty().addListener(listener);
	}

	public int getTab() {
		return tbpContent.getSelectionModel().getSelectedIndex();
	}
}
