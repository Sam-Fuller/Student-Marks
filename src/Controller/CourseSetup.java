package Controller;

import Model.Course;
import Model.Module;
/**
 * Code to populate an array of courses
 * used to populate cmbCourses
 * 
 * called by ProfileClass.ProfileClass
 *
 */
public class CourseSetup {
	public static Course[] comboSetup() {
		Module ctec2121 = new Module("CTEC2121", "Organisations, Project Management and Research", true);
		Module ctec2122 = new Module("CTEC2122", "Forensics and Security", false);
		Module ctec2602 = new Module("CTEC2602", "OO Software Design and Development", false);
		Module ctec2701 = new Module("CTEC2701", "Multi-tier Web Applications", true);
		Module ctec2901 = new Module("CTEC2901", "Data Structures and Algorithms", false);
		Module lawg2003 = new Module("LAWG2003", "Issues in Criminal Justice", false);
		Module ctec2903 = new Module("CTEC2903", "System Defence Strategies", true);

		Course compSci = new Course("Computer Science");
		compSci.addModule(ctec2121);
		compSci.addModule(ctec2602);
		compSci.addModule(ctec2701);
		compSci.addModule(ctec2901);

		Course softEng = new Course("Software Engineering");
		softEng.addModule(ctec2121);
		softEng.addModule(ctec2602);
		softEng.addModule(ctec2701);
		softEng.addModule(ctec2901);

		Course compSecu = new Course("Computer Security");
		compSecu.addModule(ctec2121);
		compSecu.addModule(ctec2122);
		compSecu.addModule(ctec2701);
		compSecu.addModule(ctec2903);

		Course forenComp = new Course("Forensic Computing");
		forenComp.addModule(ctec2121);
		forenComp.addModule(ctec2122);
		forenComp.addModule(ctec2701);
		forenComp.addModule(lawg2003);

		Course[] courses = new Course[4];
		courses[0] = compSci;
		courses[1] = softEng;
		courses[2] = compSecu;
		courses[3] = forenComp;
		
		return courses;
	}
}
