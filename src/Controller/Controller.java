package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import Model.Course;
import Model.Module;
import Model.Name;
import Model.StudentProfile;
import View.MainScene;
import View.MenuBarClass;
import View.ModuleMarksClass;
import View.ProfileClass;
import View.ResultsClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller Class for the student profile mark submission tool
 * 
 * Holds all the handlers
 * contains a constructor for populating the global variables with the view and model passed to it from ApplicationLoader
 * contains an attachEventHandlers() function to attach the handlers to the controls (called by constructor)
 * contains alertDialog and errorDialog, popups for reporting to the user
 * 
 * @author Sam
 */
public class Controller {
	//Declarations
	private StudentProfile model;

	private MainScene view;
	private ModuleMarksClass moduleMarks;
	private ProfileClass profile;
	private ResultsClass results;
	private MenuBarClass menuBar;

	//Constructor
	/**
	 * constructor
	 * Populates the global variables with the view and model passed to it from ApplicationLoader
	 * calls attachEventHandlers()
	 * 
	 * called by ApplicationLoader.init()
	 * 
	 * @param view the view passed to it
	 * @param model the model passed to it
	 */
	public Controller(MainScene view, StudentProfile model) {
		this.model = model;
		this.view = view;

		moduleMarks = view.getModuleMarksClass();
		profile = view.getProfileClass();
		results = view.getResultsClass();
		menuBar = view.getMenuBarClass();

		this.attachEventHandlers();
	}

	/**
	 * attachEventHandlers calls the add handler functions of each control
	 * sends a new instance of each handler to the controls
	 * 
	 * called by Controller
	 */
	public void attachEventHandlers() {
		profile.addSubmitHandler(new handlerCourse());

		moduleMarks.addClearHandler(new handlerClear());
		moduleMarks.addNumericHandler((observable, oldValue, newValue) -> moduleMarks.setNumeric());

		results.addSaveHandler(new handlerSaveOverview());

		menuBar.addExitHandler(new handlerExit());
		//lambda expression handler
		//calls aboutDialog
		menuBar.addAboutHandler(e -> this.aboutDialog());
		menuBar.addSaveHandler(new handlerSave());
		menuBar.addLoadHandler(new handlerLoad());

		view.addOverviewListener((observable, oldValue, newValue) -> updateOverview());

		//bp.addSelectionChangeListener((observable, oldValue, newValue) -> selectionChangeHandler(newValue))
	}

	//Handlers
	/**
	 * handler for cmbCourse action
	 * 
	 * updates the model with the course selected
	 * updates the view with the course in the model
	 */
	private class handlerCourse implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//sets the model course to the course selected in profile.cmbCourse
			model.setCourse(profile.getCourse());
			//passes the models modules to the view
			//updates the ModuleMarks page to show the four module titles and sets the visibility for the exam textboxes
			moduleMarks.setModule(model.getCourse().getModulesOnCourse());
		}
	}

	/**
	 * handler for btnClear
	 * 
	 * clears the module marks inputted
	 *
	 */
	private class handlerClear implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			moduleMarks.clear();
		}
	}

	/**
	 * handler for tab change
	 * if the tab entered is the overview results page:
	 * 
	 * checks all the inputs are valid - all required inputs have been filled in
	 * updates the model with the profile inputs and the module marks inputs
	 * 
	 * updates the results page
	 * 
	 * sets the focus to the results page
	 *
	 */

	public void updateOverview() {
		if (view.getTab() == 2) {
			if (profile.isValid() == false) {
				//checking all inputs have been inputed on the profile tab
				//if at least 1 field is missing
				//output an error dialog
				errorDialog("Input Error", "All fields must be entered");
				//set the focus to the tab where the error is
				view.setFocus(0);
			} else if (moduleMarks.isValid() == false) {
				//checking all inputs have been inputed on the module marks tab
				//if at least 1 field is missing
				//output an error dialog
				errorDialog("Input Error", "All fields must be entered");
				//set the focus to the tab where the error is
				view.setFocus(1);
			}else {
				//if all inputs have been inputed

				//add all profile information to the model
				model.setDate(profile.getDate());
				model.setEmail(profile.getEmail());
				model.setpNumber(profile.getpNumber());
				model.setStudentName(new Name(profile.getFirstName(), profile.getSurname()));

				//to update all the module scores, a list of all the module codes are required, hence moduleList
				//moduleList can then be called similar to moduleList.get(0).getModuleCode() to return the code of the first module
				ArrayList<Module> moduleList = new ArrayList<Module> (model.getCourse().getModulesOnCourse());

				//setting the coursework marks of each course
				//model.getCourse.getModuleByCode(moduleList.get(0).getModuleCode()) returns the module for the marks to be inputted in to
				//.setCwkMark is then used to update the coursework mark

				//moduleList.get(i) could be looped to make it more efficient, however it also relys on the number in getCwk1
				//as getCwki would be a different variable name this part can not be looped
				//the code is ineloquent, but is the most simple code i could think of to add the coursework marks
				model.getCourse().getModuleByCode(moduleList.get(0).getModuleCode()).setCwkMark(moduleMarks.getCwk1());
				model.getCourse().getModuleByCode(moduleList.get(1).getModuleCode()).setCwkMark(moduleMarks.getCwk2());
				model.getCourse().getModuleByCode(moduleList.get(2).getModuleCode()).setCwkMark(moduleMarks.getCwk3());
				model.getCourse().getModuleByCode(moduleList.get(3).getModuleCode()).setCwkMark(moduleMarks.getCwk4());

				//This code updates the exam marks of each module
				//this time a module may be coursework only with no exam mark, setting an integer equal to null cannot be done, so the exam marks are only set if the module is not coursework only
				//similar to the block above, this is the simplest way of doing it without a loop i could think of
				if(!moduleList.get(0).isCwkOnly()) {
					model.getCourse().getModuleByCode(moduleList.get(0).getModuleCode()).setExamMark(moduleMarks.getExam1());
				}
				if(!moduleList.get(1).isCwkOnly()) {
					model.getCourse().getModuleByCode(moduleList.get(1).getModuleCode()).setExamMark(moduleMarks.getExam2());
				}
				if(!moduleList.get(2).isCwkOnly()) {
					model.getCourse().getModuleByCode(moduleList.get(2).getModuleCode()).setExamMark(moduleMarks.getExam3());
				}
				if(!moduleList.get(3).isCwkOnly()) {
					model.getCourse().getModuleByCode(moduleList.get(3).getModuleCode()).setExamMark(moduleMarks.getExam4());	
				}

				//update the results page according to the model, and set the focus of the tab pane to the results tab
				results.update(model);
			}
		}

	}

	/**
	 * handler for tab pane exit
	 * 
	 * closes the application
	 */
	private class handlerExit implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * save overview handler
	 * 
	 * creates a new directory if one does not exist
	 * 
	 * opens a file and saves the model to it in a similar way to how lstOverview displays it
	 *
	 */
	private class handlerSaveOverview implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			File file = new File("Student Results");
			if (!file.exists()) {
				file.mkdir();
			}

			try {
				FileWriter fwtOverview = new FileWriter("Student Results/" + model.getpNumber() + ".txt");

				fwtOverview.write("Name: " + model.getStudentName().getFullName());
				fwtOverview.write(System.lineSeparator() + "P Number: " + model.getpNumber());
				fwtOverview.write(System.lineSeparator() + "Email: " + model.getEmail());
				fwtOverview.write(System.lineSeparator() + "Date: " + model.getDate().toString());
				fwtOverview.write(System.lineSeparator() + "Course: " + model.getCourse().getCourseName());

				fwtOverview.write(System.lineSeparator() + "");

				fwtOverview.write(System.lineSeparator() + "Second year credits: " + model.getCourse().creditsPassed());
				fwtOverview.write(System.lineSeparator() + "Second year average: " + model.getCourse().yearAverageMark());

				fwtOverview.close();
			} catch (IOException ex) {
				System.out.println(ex);
			}		
		}
	}


	private class handlerSave implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			updateOverview();
			
			File file = new File("C://Student Profile Data");
			if (!file.exists()) {
				file.mkdir();
			}

			try {
				FileWriter fwtData = new FileWriter("C://Student Profile Data/" + model.getpNumber() + ".spdat");
				BufferedWriter bwtData = new BufferedWriter(fwtData);

				bwtData.write(model.getStudentName().getFirstName());
				bwtData.write("\n" + model.getStudentName().getFamilyName());
				bwtData.write("\n" + model.getpNumber());
				bwtData.write("\n" + model.getEmail());
				bwtData.write("\n" + model.getDate().toString());
				bwtData.write("\n" + model.getCourse().getCourseName());

				ArrayList<Module> moduleList = new ArrayList<Module> (model.getCourse().getModulesOnCourse());

				bwtData.write("\n" + model.getCourse().getModuleByCode(moduleList.get(0).getModuleCode()).getCwkMark());
				bwtData.write("\n" + model.getCourse().getModuleByCode(moduleList.get(0).getModuleCode()).getExamMark());

				bwtData.write("\n" + model.getCourse().getModuleByCode(moduleList.get(1).getModuleCode()).getCwkMark());
				bwtData.write("\n" + model.getCourse().getModuleByCode(moduleList.get(1).getModuleCode()).getExamMark());

				bwtData.write("\n" + model.getCourse().getModuleByCode(moduleList.get(2).getModuleCode()).getCwkMark());
				bwtData.write("\n" + model.getCourse().getModuleByCode(moduleList.get(2).getModuleCode()).getExamMark());

				bwtData.write("\n" + model.getCourse().getModuleByCode(moduleList.get(3).getModuleCode()).getCwkMark());
				bwtData.write("\n" + model.getCourse().getModuleByCode(moduleList.get(3).getModuleCode()).getExamMark());

				bwtData.close();
			} catch (IOException ex) {
				System.out.println(ex);
			}	 
		}
	}

	//DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MMM-dd");
	//DateTime dateTime = FORMATTER.parseDateTime("2005-nov-12");
	//LocalDate localDate = dateTime.toLocalDate();


	private class handlerLoad implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Student Profile Data File");
			fileChooser.setInitialFileName("C://Student Profile Data");

			File file = fileChooser.showOpenDialog(new Stage());

			try {
				FileReader frdData = new FileReader(file);
				BufferedReader brdData = new BufferedReader(frdData);

				model.setStudentName(new Name(brdData.readLine(), brdData.readLine()));
				model.setpNumber(brdData.readLine());
				model.setEmail(brdData.readLine());
				model.setDate(LocalDate.parse(brdData.readLine()));

				Course[] courses = CourseSetup.comboSetup();
				Course course = courses[0];
				String name = brdData.readLine();
				for(int i = 0; i < courses.length; i++) {
					if(courses[i].getCourseName() == name) {
						course = courses[i];
					}
				}

				model.setCourse(course);

				ArrayList<Module> moduleList = new ArrayList<Module> (model.getCourse().getModulesOnCourse());

				model.getCourse().getModuleByCode(moduleList.get(0).getModuleCode()).setCwkMark(Integer.parseInt(brdData.readLine()));
				model.getCourse().getModuleByCode(moduleList.get(0).getModuleCode()).setExamMark(Integer.parseInt(brdData.readLine()));

				model.getCourse().getModuleByCode(moduleList.get(1).getModuleCode()).setCwkMark(Integer.parseInt(brdData.readLine()));
				model.getCourse().getModuleByCode(moduleList.get(1).getModuleCode()).setExamMark(Integer.parseInt(brdData.readLine()));
				
				model.getCourse().getModuleByCode(moduleList.get(2).getModuleCode()).setCwkMark(Integer.parseInt(brdData.readLine()));
				model.getCourse().getModuleByCode(moduleList.get(2).getModuleCode()).setExamMark(Integer.parseInt(brdData.readLine()));
				
				model.getCourse().getModuleByCode(moduleList.get(3).getModuleCode()).setCwkMark(Integer.parseInt(brdData.readLine()));
				model.getCourse().getModuleByCode(moduleList.get(3).getModuleCode()).setExamMark(Integer.parseInt(brdData.readLine()));
				
				brdData.close();

			} catch (IOException ex){
				System.out.println(ex);
			}
			
			view.setFocus(0);
			profile.setAll(model);
			
			view.setFocus(1);
			moduleMarks.setModule(model.getCourse().getModulesOnCourse());
			moduleMarks.setAll(model);
			
			view.setFocus(2);
			results.update(model);
		}
	}

	//Dialogs
	/**
	 * creates an alert to give the details of the application to the user
	 * 
	 * called by attachEventHandlers
	 */
	private void aboutDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Student Profile Marks Submission Tool V1.0, p15194468 Sam Fuller");
		alert.showAndWait();
	}

	/**
	 * creates an alert with a given title and content
	 * 
	 * called by handlerOverview
	 * 
	 * @param title the title given for the alert
	 * @param content the content given for the alert
	 */
	private void errorDialog(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
