package Main;

import Controller.Controller;
import Model.StudentProfile;
import View.MainScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application loader for the student profile mark submission tool
 * 
 * Creates new instances of view, model, and controller
 * Uses the view to create a stage
 * 
 * @author Sam
 *
 */
public class ApplicationLoader extends Application {
	
	//Declarations
	private MainScene view;
		
	/**
	 * Initialisation
	 * Initialises view and model
	 * Initialises Controller passing the view and model in to it
	 */
	public void init() {
		view = new MainScene();
		StudentProfile model = new StudentProfile();
		new Controller(view, model);
	}
	
	/**
	 * Sets up the stage of the program
	 * Sets a title, the content and then shows the stage
	 */
	public void start(Stage stage) {
		stage.setTitle("Student Profile Mark Submission Tool");
		stage.setScene(new Scene(view, 550, 500));
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}	
}