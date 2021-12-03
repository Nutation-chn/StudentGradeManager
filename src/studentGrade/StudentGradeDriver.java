package studentGrade;
/**
* Course Name: CST8288_014
* @Author: Dong Zhang, Feng Chen, Jessie Shen
* Class Description: StudentGradeDriver. This class contains the main loop of the application to accept and process the user choices.
* Date: Nov. 24, 2021
*/

public class StudentGradeDriver {
	
	private static final int APP_ADD_STU = 1;
	private static final int APP_FIND_STU = 2;
	private static final int APP_EDIT_STU = 3;
	private static final int APP_VIEW_ALL = 4;
	private static final int APP_REMOVE_STU = 5;
	private static final int APP_SHOW_STATISTICS = 6;
	private static final int APP_EXIT = 7;
	
	private static java.util.Scanner input = new java.util.Scanner(System.in);
	public StudentController stuControl = new StudentController();
	
	/**
	 * main loop to call the menu method and execution method
	 */
	public void startApp() {
		int option;
		do {   //loop until '7' entered by user
			option = displayMenu();
			input.nextLine();
            executeMenuItem(option);
	       }while ( option != APP_EXIT) ;
	    System.out.println("Exit program");	
	}
	
	/**
	 * menu method to display main menu and prompt user input
	 * @return option int for user to input
	 */
	private int displayMenu() {
		System.out.println("\n\n**********************************************************************************************");
		System.out.printf("%s\n\n           %s\n           %s\n           %s\n           %s\n           %s\n           %s\n           %s\n\n", 
				"Hi professor, please choose an operation: ", 
				APP_ADD_STU + ". Add a new student",
				APP_FIND_STU + ". Find a student by ID",
	    	    APP_EDIT_STU + ". Edit student by ID",
	    	    APP_VIEW_ALL + ". View all students",
	    	    APP_REMOVE_STU + ". Remove student by ID",
	    	    APP_SHOW_STATISTICS + ". Show class' statistics",
	    	    APP_EXIT + ". Exit program");
		return(input.nextInt());
		
	}
	
	/**
	 * Menu execution to call method in StudentController
	 * @param choice: Option user entered
	 * @return void
	 */
	private void executeMenuItem(int choice) {
		switch (choice) {
		case APP_ADD_STU:
			stuControl.addStudent();
			break;
		case APP_FIND_STU:
			stuControl.getStudentById();
			break;
		case APP_EDIT_STU:
			stuControl.updateStudent();
			break;
		case APP_VIEW_ALL:
			stuControl.getAll();
			break;
		case APP_REMOVE_STU:
			stuControl.deleteStudent();
			break;
		case APP_SHOW_STATISTICS:
			stuControl.getStatistics();
			break;
		case APP_EXIT:
			System.out.println("Good Bye!");
			break;
		default:
			System.out.println("Invalid input");
			System.out.println();
	}
		
	}

}
