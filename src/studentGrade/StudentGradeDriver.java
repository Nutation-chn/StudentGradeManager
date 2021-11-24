package studentGrade;
/*
Class Name: CST8288_014
Author Name: Dong Zhang, Feng Chen, Jessie Shen
Class Description: StudentGradeDriver. This class contains the main loop of the application to accept and process the user choices.
Date: Nov. 24, 2021
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
	
	public void startApp() {
		int option;
		do {   //loop until '0' entered by user
			option = displayMenu();
			input.nextLine();
            executeMenuItem(option);
	       }while ( option != APP_EXIT) ;
	    System.out.println("Exit program");	
	}
	
	private int displayMenu() {
		return(input.nextInt());
		
	}
	
	private void executeMenuItem(int choice) {
		
	}

}
