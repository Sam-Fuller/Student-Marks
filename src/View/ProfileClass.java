package View;

import java.time.LocalDate;

import Controller.CourseSetup;
import Model.Course;
import Model.StudentProfile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProfileClass extends VBox{

	Course[] Courses = CourseSetup.comboSetup();

	ComboBox<String> cmbCourse = new ComboBox<String>();
	TextField txtPNumber = new TextField();
	TextField txtFirstName = new TextField();
	TextField txtSurname = new TextField();
	TextField txtEmail = new TextField();
	DatePicker dtpDate = new DatePicker();

	public ProfileClass() {

		this.setSpacing(20);
		this.setPadding(new Insets(50));
		this.setAlignment(Pos.CENTER);

		//Adding course Text and ComboBox
		HBox hbxCourse = new HBox();
		hbxCourse.setSpacing(50);
		hbxCourse.setAlignment(Pos.CENTER_RIGHT);
		hbxCourse.setMaxWidth(400);

		hbxCourse.getChildren().add(new Text("Select course"));

		for(int i = 0; i < Courses.length; i++) {
			cmbCourse.getItems().add(Courses[i].getCourseName());
		}

		cmbCourse.setPrefWidth(200);
		hbxCourse.getChildren().add(cmbCourse);

		this.getChildren().add(hbxCourse);



		//Adding pnumber Text and TextField
		HBox hbxPNumber = new HBox();
		hbxPNumber.setSpacing(50);
		hbxPNumber.setAlignment(Pos.CENTER_RIGHT);
		hbxPNumber.setMaxWidth(400);

		hbxPNumber.getChildren().add(new Text("Input P number"));

		txtPNumber.setPrefWidth(200);
		hbxPNumber.getChildren().add(txtPNumber);

		this.getChildren().add(hbxPNumber);



		//Adding FirstName Text and TextField
		HBox hbxFirstName = new HBox();
		hbxFirstName.setSpacing(50);
		hbxFirstName.setAlignment(Pos.CENTER_RIGHT);
		hbxFirstName.setMaxWidth(400);

		hbxFirstName.getChildren().add(new Text("Input first name"));

		txtFirstName.setPrefWidth(200);
		hbxFirstName.getChildren().add(txtFirstName);

		this.getChildren().add(hbxFirstName);



		//Adding Surname Text and TextField
		HBox hbxSurname = new HBox();
		hbxSurname.setSpacing(50);
		hbxSurname.setAlignment(Pos.CENTER_RIGHT);
		hbxSurname.setMaxWidth(400);

		hbxSurname.getChildren().add(new Text("Input surname"));

		txtSurname.setPrefWidth(200);
		hbxSurname.getChildren().add(txtSurname);

		this.getChildren().add(hbxSurname);



		//Adding email Text and TextField
		HBox hbxEmail = new HBox();
		hbxEmail.setSpacing(50);
		hbxEmail.setAlignment(Pos.CENTER_RIGHT);
		hbxEmail.setMaxWidth(400);

		hbxEmail.getChildren().add(new Text("Input email"));

		txtEmail.setPrefWidth(200);
		hbxEmail.getChildren().add(txtEmail);

		this.getChildren().add(hbxEmail);



		//Adding Date Text and TextField
		HBox hbxDate = new HBox();
		hbxDate.setSpacing(50);
		hbxDate.setAlignment(Pos.CENTER_RIGHT);
		hbxDate.setMaxWidth(400);

		hbxDate.getChildren().add(new Text("Input date"));

		dtpDate.setPrefWidth(200);
		hbxDate.getChildren().add(dtpDate);

		this.getChildren().add(hbxDate);

		this.getChildren().add(new Text("Enter marks on Module Marks tab"));
	}

	public Course getCourse() {
		for(int i = 0; i < Courses.length; i++) {
			if(Courses[i].getCourseName() == cmbCourse.getSelectionModel().getSelectedItem()) {
				return Courses[i];
			}
		}
		return null;
	}

	public LocalDate getDate() {
		return dtpDate.getValue();
	}

	public String getEmail() {
		return txtEmail.getText();
	}

	public String getpNumber() {
		return txtPNumber.getText();
	}

	public String getFirstName() {
		return txtFirstName.getText();
	}

	public String getSurname() {
		return txtSurname.getText();
	}

	public boolean isValid() {
		if (cmbCourse.getSelectionModel().getSelectedItem() == null){
			return false;
		} else if (txtPNumber.getText().trim().isEmpty()) {
			return false;
		} else if (txtFirstName.getText().trim().isEmpty()) {
			return false;
		} else if (txtSurname.getText().trim().isEmpty()) {
			return false;
		} else if (txtEmail.getText().trim().isEmpty()) {
			return false;
		} else if (dtpDate.getValue() == null) {
			return false;
		} else {
			return true;
		}
	}

	public void setAll(StudentProfile model) {
		cmbCourse.getSelectionModel().select(model.getCourse().getCourseName());
		dtpDate.setValue(model.getDate());
		txtEmail.setText(model.getEmail());
		txtPNumber.setText(model.getpNumber());
		txtFirstName.setText(model.getStudentName().getFirstName());
		txtSurname.setText(model.getStudentName().getFirstName());
	}
	
	public void addSubmitHandler(EventHandler<ActionEvent> handler) {
		cmbCourse.setOnAction(handler);
	}
}