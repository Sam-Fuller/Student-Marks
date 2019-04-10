package View;

import java.util.ArrayList;
import java.util.Collection;
import Model.Module;
import Model.StudentProfile;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * the content for the ModuleMarks tab
 * 
 * @author Sam
 *
 */
public class ModuleMarksClass extends VBox{

	//Declarations
	Text txtMod1 = new Text("Profile not created");
	Text txtMod2 = new Text("Profile not created");
	Text txtMod3 = new Text("Profile not created");
	Text txtMod4 = new Text("Profile not created");

	TextField txtCwk1 = new TextField();
	TextField txtCwk2 = new TextField();
	TextField txtCwk3 = new TextField();
	TextField txtCwk4 = new TextField();

	TextField txtExam1 = new TextField();
	TextField txtExam2 = new TextField();
	TextField txtExam3 = new TextField();
	TextField txtExam4 = new TextField();

	Button btnClear = new Button("Clear");

	public ModuleMarksClass() {
		this.setSpacing(20);
		this.setPadding(new Insets(50));
		this.setAlignment(Pos.CENTER);

		//Adding Titles
		HBox hbxTitles = new HBox();
		hbxTitles.setSpacing(20);
		hbxTitles.setAlignment(Pos.CENTER_RIGHT);
		hbxTitles.setMaxWidth(500);

		Text txtModule = new Text("Module");
		Text txtCwk = new Text("Cwk Mark");
		Text txtExam = new Text("Exam Mark");
		hbxTitles.getChildren().addAll(txtModule,txtCwk,txtExam);

		this.getChildren().add(hbxTitles);


		//Adding Row 1
		HBox hbxMod1 = new HBox();
		hbxMod1.setSpacing(30);
		hbxMod1.setAlignment(Pos.CENTER_RIGHT);
		hbxMod1.setMaxWidth(500);

		hbxMod1.getChildren().add(txtMod1);

		txtCwk1.setPrefWidth(50);
		hbxMod1.getChildren().add(txtCwk1);

		txtExam1.setPrefWidth(50);
		hbxMod1.getChildren().add(txtExam1);

		this.getChildren().add(hbxMod1);


		//Adding Row 2
		HBox hbxMod2 = new HBox();
		hbxMod2.setSpacing(30);
		hbxMod2.setAlignment(Pos.CENTER_RIGHT);
		hbxMod2.setMaxWidth(500);

		hbxMod2.getChildren().add(txtMod2);

		txtCwk2.setPrefWidth(50);
		hbxMod2.getChildren().add(txtCwk2);

		txtExam2.setPrefWidth(50);
		hbxMod2.getChildren().add(txtExam2);

		this.getChildren().add(hbxMod2);


		//Adding Row 3
		HBox hbxMod3 = new HBox();
		hbxMod3.setSpacing(30);
		hbxMod3.setAlignment(Pos.CENTER_RIGHT);
		hbxMod3.setMaxWidth(500);

		hbxMod3.getChildren().add(txtMod3);

		txtCwk3.setPrefWidth(50);
		hbxMod3.getChildren().add(txtCwk3);

		txtExam3.setPrefWidth(50);
		hbxMod3.getChildren().add(txtExam3);

		this.getChildren().add(hbxMod3);


		//Adding Row 4
		HBox hbxMod4 = new HBox();
		hbxMod4.setSpacing(30);
		hbxMod4.setAlignment(Pos.CENTER_RIGHT);
		hbxMod4.setMaxWidth(500);

		hbxMod4.getChildren().add(txtMod4);

		txtCwk4.setPrefWidth(50);
		hbxMod4.getChildren().add(txtCwk4);

		txtExam4.setPrefWidth(50);
		hbxMod4.getChildren().add(txtExam4);

		this.getChildren().add(hbxMod4);


		//adding control buttons
		HBox hbxControls = new HBox();
		hbxControls.setSpacing(30);
		hbxControls.setAlignment(Pos.CENTER);
		hbxControls.getChildren().add(btnClear);

		this.getChildren().add(hbxControls);
		
		Text txtHelp = new Text("View overview on Overview Results tab");
		this.getChildren().add(txtHelp);
	}
	
	/**
	 * set the text and visibility of text fields on the form
	 * @param Modules the modules to be displayed
	 */
	public void setModule(Collection<Module> Modules) {

		ArrayList<Module> ModuleList = new ArrayList<Module> (Modules);

		txtMod1.setText(ModuleList.get(0).getModuleName());
		txtMod2.setText(ModuleList.get(1).getModuleName());
		txtMod3.setText(ModuleList.get(2).getModuleName());
		txtMod4.setText(ModuleList.get(3).getModuleName());

		txtExam1.setVisible(!ModuleList.get(0).isCwkOnly());
		txtExam2.setVisible(!ModuleList.get(1).isCwkOnly());
		txtExam3.setVisible(!ModuleList.get(2).isCwkOnly());
		txtExam4.setVisible(!ModuleList.get(3).isCwkOnly());
	}

	/**
	 * Clears all the text fields on the tab
	 */
	public void clear() {
		txtCwk1.setText("");
		txtCwk2.setText("");
		txtCwk3.setText("");
		txtCwk4.setText("");

		txtExam1.setText("");
		txtExam2.setText("");
		txtExam3.setText("");
		txtExam4.setText("");
	}

	/**
	 * returns the value of the text field
	 * @return the text value
	 */
	public int getCwk1() {
		return Integer.parseInt(txtCwk1.getText());
	}
	/**
	 * returns the value of the text field
	 * @return the text value
	 */
	public int getCwk2() {
		return Integer.parseInt(txtCwk2.getText());
	}
	/**
	 * returns the value of the text field
	 * @return the text value
	 */
	public int getCwk3() {
		return Integer.parseInt(txtCwk3.getText());
	}
	/**
	 * returns the value of the text field
	 * @return the text value
	 */
	public int getCwk4() {
		return Integer.parseInt(txtCwk4.getText());
	}
	
	/**
	 * returns the value of the text field
	 * @return the text value
	 */
	public int getExam1() {
		return Integer.parseInt(txtExam1.getText());
	}
	/**
	 * returns the value of the text field
	 * @return the text value
	 */
	public int getExam2() {
		return Integer.parseInt(txtExam2.getText());
	}
	/**
	 * returns the value of the text field
	 * @return the text value
	 */
	public int getExam3() {
		return Integer.parseInt(txtExam3.getText());
	}
	/**
	 * returns the value of the text field
	 * @return the text value
	 */
	public int getExam4() {
		return Integer.parseInt(txtExam4.getText());
	}

	/**
	 * returns false if a text box is empty and visible
	 * returns true otherwise
	 * 
	 * @return
	 */
	public boolean isValid() {
		if (txtCwk1.getText().trim().isEmpty()) {
			return false;
		} else if (txtCwk2.getText().trim().isEmpty()) {
			return false;
		} else if (txtCwk3.getText().trim().isEmpty()) {
			return false;
		} else if (txtCwk4.getText().trim().isEmpty()) {
			return false;
		} else if (txtExam1.getText().trim().isEmpty() && txtExam1.isVisible()) {
			return false;
		} else if (txtExam2.getText().trim().isEmpty() && txtExam2.isVisible()) {
			return false;
		} else if (txtExam3.getText().trim().isEmpty() && txtExam3.isVisible()) {
			return false;
		} else if (txtExam4.getText().trim().isEmpty() && txtExam4.isVisible()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * removes all characters in text fields that are not numeric 0-9 on the tab page
	 */
	public void setNumeric() {
		if (!txtCwk1.getText().matches("\\d*")) {
            txtCwk1.setText(txtCwk1.getText().replaceAll("[^\\d]", ""));
        }
		if (!txtCwk2.getText().matches("\\d*")) {
            txtCwk2.setText(txtCwk2.getText().replaceAll("[^\\d]", ""));
        }
		if (!txtCwk3.getText().matches("\\d*")) {
            txtCwk3.setText(txtCwk3.getText().replaceAll("[^\\d]", ""));
        }
		if (!txtCwk4.getText().matches("\\d*")) {
            txtCwk4.setText(txtCwk4.getText().replaceAll("[^\\d]", ""));
        }
		if (!txtExam1.getText().matches("\\d*")) {
			txtExam1.setText(txtExam1.getText().replaceAll("[^\\d]", ""));
        }
		if (!txtExam2.getText().matches("\\d*")) {
			txtExam2.setText(txtExam2.getText().replaceAll("[^\\d]", ""));
        }
		if (!txtExam3.getText().matches("\\d*")) {
			txtExam3.setText(txtExam3.getText().replaceAll("[^\\d]", ""));
        }
		if (!txtExam4.getText().matches("\\d*")) {
			txtExam4.setText(txtExam4.getText().replaceAll("[^\\d]", ""));
        }
	}

	/**
	 * attaching handler to btnClear
	 * @param handler the handler to be attached
	 */
	public void addClearHandler(EventHandler<ActionEvent> handler) {
		btnClear.setOnAction(handler);
	}
	
	public void setAll(StudentProfile model) {
		
		this.clear();
		
		ArrayList<Module> ModuleList = new ArrayList<Module> (model.getCourse().getModulesOnCourse());
		
		txtCwk1.setText(Integer.toString(model.getCourse().getModuleByCode(ModuleList.get(0).getModuleCode()).getCwkMark()));
		txtExam1.setText(Integer.toString(model.getCourse().getModuleByCode(ModuleList.get(0).getModuleCode()).getExamMark()));
		
		txtCwk2.setText(Integer.toString(model.getCourse().getModuleByCode(ModuleList.get(1).getModuleCode()).getCwkMark()));
		txtExam2.setText(Integer.toString(model.getCourse().getModuleByCode(ModuleList.get(1).getModuleCode()).getExamMark()));
		
		txtCwk3.setText(Integer.toString(model.getCourse().getModuleByCode(ModuleList.get(2).getModuleCode()).getCwkMark()));
		txtExam3.setText(Integer.toString(model.getCourse().getModuleByCode(ModuleList.get(2).getModuleCode()).getExamMark()));
		
		txtCwk4.setText(Integer.toString(model.getCourse().getModuleByCode(ModuleList.get(3).getModuleCode()).getCwkMark()));
		txtExam4.setText(Integer.toString(model.getCourse().getModuleByCode(ModuleList.get(3).getModuleCode()).getExamMark()));	
	}

	/**
	 * adding listener to all the text fields
	 * @param listener the listener to be attached
	 */
	public void addNumericHandler(ChangeListener<String> listener) {
		txtCwk1.textProperty().addListener(listener);	
		txtCwk2.textProperty().addListener(listener);	
		txtCwk3.textProperty().addListener(listener);	
		txtCwk4.textProperty().addListener(listener);	
		
		txtExam1.textProperty().addListener(listener);	
		txtExam2.textProperty().addListener(listener);	
		txtExam3.textProperty().addListener(listener);	
		txtExam4.textProperty().addListener(listener);	

	}
}
