package View;

import Model.StudentProfile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * the content for the Overview Results tab
 * 
 * @author Sam
 *
 */
public class ResultsClass extends VBox{
	//Declarations
	ListView<String> lstOverview = new ListView<String>();
	Button btnSubmit = new Button("Save Overview");
	/**
	 * default constructor for the results class
	 * 
	 * called by MainScene.MainScene
	 */
	public ResultsClass() {
		this.setSpacing(20);
		this.setPadding(new Insets(50));
		this.setAlignment(Pos.CENTER);

		//Adding list view
		lstOverview.setPrefHeight(200);
		this.getChildren().add(lstOverview);

		//Adding save button
		this.getChildren().add(btnSubmit);

	}

	/**
	 * code to update the contents of the listview
	 * clears the listbox
	 * then adds profile details
	 * adds a space
	 * then adds the second year credits and average
	 * 
	 * called by Controller.updateOverview
	 * @param student
	 */
	public void update(StudentProfile student) {
		lstOverview.getItems().clear();

		lstOverview.getItems().add("Name: " + student.getStudentName().getFullName());
		lstOverview.getItems().add("P Number: " + student.getpNumber());
		lstOverview.getItems().add("Email: " + student.getEmail());
		lstOverview.getItems().add("Date: " + student.getDate().toString());
		lstOverview.getItems().add("Course: " + student.getCourse().getCourseName());

		lstOverview.getItems().add("");

		lstOverview.getItems().add("Second year credits: " + student.getCourse().creditsPassed());
		lstOverview.getItems().add("Second year average: " + student.getCourse().yearAverageMark());
	}

	public void addSaveHandler(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);		
	}
}