package studentGrade;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * this class is the controller handles all the user requests
 * @author Dong Zhang, Jiasi Shen
 * 
 */

public class StudentController {

	/**
	 * Factory to create DAL
	 */
	private StudentDALFactory studentDALFactory = new StudentDALFactory();
	/**
	 * DAL that handles database requests.
	 */
	private StudentDAL studentDAL = studentDALFactory.getStudentDAL("jdbc");
	/**
	 * scanner to get user input
	 */
	private Scanner scanner = new Scanner(System.in);

	public void addStudent() {
		System.out.println("Start to add new student information:");
		if (studentDAL.add(userInputStudent())) {
			System.out.println("Data add successfully.");
		} else {
			System.out.println("Data add failure.");
		}
	}

	/**
	 * delete student information of give student id
	 */
	public void deleteStudent() {
		int id;
		Student deleteStudent;
		boolean deletionSuccessful = false;
		if ((deleteStudent = studentDAL.getStudent(id = getIntInput("ID of student to delete"))) != null) {
			if (studentDAL.deleteStudent(id)) {
				System.out.println(String.format("Student %s %s has been deleted.", deleteStudent.getLastName(),
						deleteStudent.getFirstName()));
				deletionSuccessful = true;
			}
		}
		if (!deletionSuccessful) {
			System.out.println("Student not found, please check if the id is correct.");
		}

	}

	/**
	 * get student information of give student id
	 */
	public void getStudentById() {
		Student student = studentDAL.getStudent(getIntInput("student ID"));
		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("Student not found, please check if the id is correct.");
		}
	}

	/**
	 * print information of all students
	 */
	public void getAll() {
		List<Student> studentList = studentDAL.getAll();
		if (studentList != null) {
			for (Student student : studentList) {
				System.out.println(student);
			}
		}
		if(studentList.size()==0) {
			System.out.println("No student record in the database.");
		}
	}

	/**
	 * update information of student of given id, press enter to skip item.
	 */
	public void updateStudent() {
	
		String msg = "new %s (press Enter key to skip)";
		int updateStudentId = getIntInput("student id to update");
		String inputString;
		int inputInt = 0;
		Student updateStudent;
		if((updateStudent= studentDAL.getStudent(updateStudentId))!=null) {
			inputString = getStringInput(String.format(msg, "first name"));
			if (!inputString.equals("")){
				updateStudent.setFirstName(inputString);
			}
			inputString = getStringInput(String.format(msg, "last name"));
			if (!inputString.equals("")){
				updateStudent.setLastName(inputString);
			}
			inputString = getStringDobUpdate();
			if (!inputString.equals("")){
				updateStudent.setDob(inputString);
			}
			updateStudent.setEmailAddress(generateEmail(updateStudent.getFirstName(),
					updateStudent.getLastName(),updateStudent.getDob()));
			inputInt = getIntScoreToUpdate(String.format(msg, "midterm1"));
			if (inputInt!=-1){
				updateStudent.setMidterm1(inputInt);
			}
			inputInt = getIntScoreToUpdate(String.format(msg, "midterm2"));
			if (inputInt!=-1){
				updateStudent.setMidterm2(inputInt);
			}
			inputInt = getIntScoreToUpdate(String.format(msg, "Assignment1"));
			if (inputInt!=-1){
				updateStudent.setAssignment1(inputInt);
			}
			inputInt = getIntScoreToUpdate(String.format(msg, "Assignment2"));
			if (inputInt!=-1){
				updateStudent.setAssignment2(inputInt);
			}	
			inputInt = getIntScoreToUpdate(String.format(msg, "Assignment3"));
			if (inputInt!=-1){
				updateStudent.setAssignment3(inputInt);
			}	
			inputInt = getIntScoreToUpdate(String.format(msg, "Assignment4"));
			if (inputInt!=-1){
				updateStudent.setAssignment4(inputInt);
			}	
			inputInt = getIntScoreToUpdate(String.format(msg, "Assignment5"));
			if (inputInt!=-1){
				updateStudent.setAssignment5(inputInt);
			}	
			updateStudent.setFinalScore(calculateFinalScore(updateStudent.getMidterm1(),updateStudent.getMidterm2(),
					updateStudent.getAssignment1(),updateStudent.getAssignment2(),updateStudent.getAssignment3(),
					updateStudent.getAssignment4(),updateStudent.getAssignment5())); 
			updateStudent.setFinalGrade(calculateFinalGrade(updateStudent.getFinalScore()));
			if(studentDAL.updateStudent(updateStudent)) {
					System.out.println("Data updated successfully.");				
			}else {
				System.out.println("Data update failure.");
			}
		}else {
			System.out.println("Student not found, please check if the id is correct.");
		}

	}

	/**
	 * generate and print statistics
	 * For each deliverable in the course (midterms & assignments), calculate the class's average score for that deliverable.
	 * For each deliverable in the course (midterms & assignments), find out how many students scored above the class’s average, and how many students scored below that average.
	 * For the Overall Final Score of the students, calculate the average Overall Final Score of the class and the highest and lowest Overall Final Score. Also, find out how many students passed (scored above 50) and how many failed the class.
	 */
	
	public void getStatistics() {
		List<Student> studentList = studentDAL.getAll();
		int studentCount;
		int averageMid1 = 0, averageMid2 = 0, averageA1 = 0, averageA2 = 0, averageA3 = 0, averageA4 = 0, averageA5 = 0;
		int averageFinalScore = 0;
		int passCount = 0, failCount = 0, highestFinalScore = 0, lowestFinalScore = 100, mid1AboveAveCount = 0,
				mid1BelowAveCount = 0, mid2AboveAveCount = 0, mid2BelowAveCount = 0, A1AboveAveCount = 0,
				A1BelowAveCount = 0, A2AboveAveCount = 0, A2BelowAveCount = 0, A3AboveAveCount = 0, A3BelowAveCount = 0,
				A4AboveAveCount = 0, A4BelowAveCount = 0, A5AboveAveCount = 0, A5BelowAveCount = 0;

		if (studentList != null) {
			studentCount = studentList.size();
			for (Student student : studentList) {
				averageMid1 += student.getMidterm1();
				averageMid2 += student.getMidterm2();
				averageA1 += student.getAssignment1();
				averageA2 += student.getAssignment2();
				averageA3 += student.getAssignment3();
				averageA4 += student.getAssignment4();
				averageA5 += student.getAssignment5();
				int currentFinalScore = student.getFinalScore();
				averageFinalScore += currentFinalScore;
				if (currentFinalScore >= 50) {
					passCount += 1;
				}
				highestFinalScore = (highestFinalScore < currentFinalScore ? currentFinalScore : highestFinalScore);
				lowestFinalScore = (lowestFinalScore > currentFinalScore ? currentFinalScore : highestFinalScore);
			}
			averageMid1 /= studentCount;
			averageMid2 /= studentCount;
			averageA1 /= studentCount;
			averageA2 /= studentCount;
			averageA3 /= studentCount;
			averageA4 /= studentCount;
			averageA5 /= studentCount;
			averageFinalScore /= studentCount;

			for (Student student : studentList) {
				if (student.getMidterm1() > averageMid1) {
					mid1AboveAveCount++;
				} else if (student.getMidterm1() < averageMid1) {
					mid1BelowAveCount++;
				}

				if (student.getMidterm2() > averageMid2) {
					mid2AboveAveCount++;
				} else if (student.getMidterm2() < averageMid2) {
					mid2BelowAveCount++;
				}

				if (student.getAssignment1() > averageA1) {
					A1AboveAveCount++;
				} else if (student.getAssignment1() < averageA1) {
					A1BelowAveCount++;
				}

				if (student.getAssignment2() > averageA2) {
					A2AboveAveCount++;
				} else if (student.getAssignment2() < averageA2) {
					A2BelowAveCount++;
				}

				if (student.getAssignment3() > averageA3) {
					A3AboveAveCount++;
				} else if (student.getAssignment3() < averageA3) {
					A3BelowAveCount++;
				}

				if (student.getAssignment4() > averageA4) {
					A4AboveAveCount++;
				} else if (student.getAssignment4() < averageA4) {
					A4BelowAveCount++;
				}

				if (student.getAssignment5() > averageA5) {
					A5AboveAveCount++;
				} else if (student.getAssignment5() < averageA5) {
					A5BelowAveCount++;
				}

			}
			System.out.println(String.format("%15s%15s%15s%15s%15s\n%15s%15d%15d%15d%15d\n%15s%15d%15d%15d%15d"
					+ "\n%15s%15d%15d%15d%15d\n%15s%15d%15d%15d%15d\n%15s%15d%15d%15d%15d\n%15s%15d%15d%15d%15d\n%15s%15d%15d%15d%15d", 
					"Deliverable","Average Score","Above","Below","At Average"
					,"Midterm1",  averageMid1,mid1AboveAveCount,mid1BelowAveCount,studentCount-mid1AboveAveCount-mid1BelowAveCount
					,"Midterm2",  averageMid2,mid2AboveAveCount,mid2BelowAveCount,studentCount-mid1AboveAveCount-mid2BelowAveCount
					,"Assignment1",averageA1,A1AboveAveCount,A1BelowAveCount,studentCount-A1AboveAveCount-A1BelowAveCount
					,"Assignment2",averageA2,A2AboveAveCount,A2BelowAveCount,studentCount-A2AboveAveCount-A2BelowAveCount
					,"Assignment3",averageA3,A3AboveAveCount,A3BelowAveCount,studentCount-A3AboveAveCount-A3BelowAveCount
					,"Assignment4",averageA4,A4AboveAveCount,A4BelowAveCount,studentCount-A4AboveAveCount-A4BelowAveCount
					,"Assignment5",averageA5,A5AboveAveCount,A5BelowAveCount,studentCount-A5AboveAveCount-A5BelowAveCount));
					
			System.out.println(String.format("%30s%10d","Overall final Score average:",averageFinalScore));
			System.out.println(String.format("%30s%10d","Highest final Score:",highestFinalScore));
			System.out.println(String.format("%30s%10d","Lowest final Score:",lowestFinalScore));
			System.out.println(String.format("%30s%10d","Number of student passed:",passCount));
			System.out.println(String.format("%30s%10d","Number of student failed:",studentCount-passCount));
		} else {
			System.out.println("No student record.");
		}

	}

	/**ask user to input information of a new student 
	 * @return student information
	 */
	private Student userInputStudent() {
		String fn, ln, dob;
		int mid1, mid2, a1, a2, a3, a4, a5, finalScore;

		Student inputStudent = new Student.Builder().setFirstName(fn = getStringInput("first name"))
				.setLastName(ln = getStringInput("last name"))
				.setDOB(dob = getStringDob())
				.setEmailAddress(generateEmail(fn, ln, dob))
				.setMidterm1(mid1 = getIntScore("midterm 1"))
				.setMidterm2(mid2 = getIntScore("midterm 2"))
				.setAssignment1(a1 = getIntScore("assignment 1"))
				.setAssignment2(a2 = getIntScore("assignment 2"))
				.setAssignment3(a3 = getIntScore("assignment 3"))
				.setAssignment4(a4 = getIntScore("assignment 4"))
				.setAssignment5(a5 = getIntScore("assignment 5"))
				.setFinalScore(finalScore = calculateFinalScore(mid1, mid2, a1, a2, a3, a4, a5))
				.setFinalGrade(calculateFinalGrade(finalScore))
				.build();
		return inputStudent;
	}

	/**calculate final score using mid term and assignment scores, mid term weight 25% each and assignment weight 10% each.
	 * @param midterm1: mid term 1 score
	 * @param midterm2: mid term 2 score
	 * @param assignment1: assignment 1 score
	 * @param assignment2: assignment 2 score
	 * @param assignment3: assignment 3 score
	 * @param assignment4: assignment 4 score
	 * @param assignment5: assignment 5 score
	 * @return final score
	 */
	private int calculateFinalScore(int midterm1, int midterm2, int assignment1, int assignment2, int assignment3, int assignment4, int assignment5) {

		return (int) (midterm1 * 0.25 + midterm2 * 0.25 + assignment1 * 0.1 + assignment2 * 0.1+ assignment3 * 0.1 + assignment4 * 0.1 + assignment5 * 0.1);// todo is score
																										// integer?
	}

	/** calculate letter final grade using final score (https://www.algonquincollege.com/policies/files/2017/06/AA14.pdf).
	 * @param finalScore integer between 0 and 100
	 * @return letter final grade 
	 */
	private String calculateFinalGrade(int finalScore) {
		if (finalScore > 89) {
			return "A+";
		} else if (finalScore > 84) {
			return "A";
		} else if (finalScore > 79) {
			return "A-";
		} else if (finalScore > 76) {
			return "B+";
		} else if (finalScore > 72) {
			return "B";
		} else if (finalScore > 69) {
			return "B-";
		} else if (finalScore > 66) {
			return "C+";
		} else if (finalScore > 62) {
			return "C";
		} else if (finalScore > 59) {
			return "C-";
		} else if (finalScore > 56) {
			return "D+";
		} else if (finalScore > 52) {
			return "D";
		} else if (finalScore > 49) {
			return "D-";
		} else {
			return "F";
		}

	}

	
	
	/**this method generate Email address using following given strings
	 * @param fn first name
	 * @param ln last name
	 * @param dob date of birth in the format of yyyy-mm-dd
	 * @return Email address
	 */
	private String generateEmail(String fn, String ln, String dob) {
		String[] dateSplit = dob.split("-");
		return fn + dateSplit[2] + ln + "@algomail.com";
	}

	
	/**this method ask user to input a string 
	 * @param message name of the value user need to input
	 * @return string input
	 */
	private String getStringInput(String message) {
		System.out.print("Please input " + message+":");
		return scanner.nextLine();
	}

	/**this method ask user to input a string date of birth in the format of yyyy-mm-dd, 
	 * it will continue looping until input is valid.
	 * @param message name of the value user need to input
	 * @return string date of birth in the format of yyyy-mm-dd
	 */
	private String getStringDob() {
		boolean inputValid = false;
		String input = "";
		while (inputValid == false) {
			try {
				System.out.print("Please input date of birth(format yyyy-mm-dd):");
				input = scanner.nextLine();
				Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(input);
				inputValid = true;
			} catch (Exception e) {
				System.out.println("invalid input, please try again.");
			}
		}
		return input;
	}

	/**this method ask user to input a string date of birth in the format of yyyy-mm-dd, 
	 * it will continue looping until input is valid, press enter will return "" indicating skipping this input.
	 * @param message name of the value user need to input
	 * @return string date of birth in the format of yyyy-mm-dd or "" indicating skipping this input
	 */
	private String getStringDobUpdate() {
		boolean inputValid = false;
		String input = "";
		while (inputValid == false) {
			try {
				System.out.print("Please input date of birth, format yyyy-mm-dd (press Enter key to skip):");
				input = scanner.nextLine();
				if (input.length()==0) {//input==""
             			return "";
				}
				Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(input);
				inputValid = true;
			} catch (Exception e) {
				System.out.println("invalid input, please try again.");
			}
		}
		return input;
	}


	/**this method ask user to input a score of integer format between 0 and 100, 
	 * it will continue looping until input is valid.
	 * @param message name of the value user need to input
	 * @return score between 1 and 100
	 */
	private int getIntScore(String message) {
		int result = -1;
		boolean inputValid = false;

		String input;
		while (inputValid == false) {
			try {
				System.out.print("Please input " + message + "(between 0 and 100):");
				input = scanner.nextLine();
				if (input.equals("")) {
					return 0;
				}
				result = Integer.parseInt(input);
				if (result>=0&&result<=100) {
					inputValid=true;
				}else {
					System.out.println("Invalid input, try again.");
				}
			} catch (Exception e) {
				System.out.println("invalid input, please try again.");
			}
		}
		return result;
	}
	

	/**this method ask user to input a score of integer format, 
	 * it will continue looping until input is valid.
	 * @param message name of the value user need to input
	 * @return integer of input
	 */
	private int getIntInput(String message) {
		int result = -1;
		boolean inputValid = false;

		String input;
		while (inputValid == false) {
			try {
				System.out.print("Please input " + message + ":");
				input = scanner.nextLine();
				if (input.equals("")) {
					return 0;
				}
				result = Integer.parseInt(input);
				inputValid = true;
			} catch (Exception e) {
				System.out.println("invalid input, please try again.");
			}
		}
		return result;
	}
	
	


	/**this method ask user to input a score of integer format between 0 and 100, 
	 * it will continue looping until input is valid, press enter will return -1 indicating skipping this input.
	 * @param message name of the value user need to input
	 * @return score between 1 and 100, or -1 indicating skipping this input.
	 */
	private int getIntScoreToUpdate(String message) {
		int result = -1;
		boolean inputValid = false;

		String input;
		while (inputValid == false) {
			try {
				System.out.println("Please input " + message + "(between 0 and 100):");
				input = scanner.nextLine();
				if (input.equals("")) {
					return -1;
				}
				result = Integer.parseInt(input);
				if (result>=0&&result<=100) {
					inputValid=true;
				}else {
					System.out.println("Invalid input, try again.");
				}
				
			} catch (Exception e) {
				System.out.println("invalid input, please try again.");
			}
		}
		return result;
	}
}