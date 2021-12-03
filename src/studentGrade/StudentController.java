package studentGrade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentController {

	private StudentDALFactory studentDALFactory = new StudentDALFactory();
	private StudentDAL studentDAL = studentDALFactory.getStudentDAL("jdbc");
	private Scanner scanner = new Scanner(System.in);

	public void addStudent() {
		System.out.println("Start to add new student information:");
		if (studentDAL.add(userInputStudent())) {
			System.out.println("Data add successfully.");
		} else {
			System.out.println("Data add failure.");
		}
	}

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
		if (deletionSuccessful) {
			System.out.println("Student not found, please check if the id is correct.");
		}

	}

	public void getStudentById() {
		Student student = studentDAL.getStudent(getIntInput("student ID"));
		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("Student not found, please check if the id is correct.");
		}
	}

	public void getAll() {
		List<Student> studentList = studentDAL.getAll();
		if (studentList != null) {
			for (Student student : studentList) {
				System.out.println(student);
			}
		}
	}

	public void updateStudent() {
	
		String msg = "new %s (press Enter key to skip)";
		int updateStudentId = getIntInput("student id to update");
		String inputString;
		int inputInt = 0;
		Student updateStudent;
		if((updateStudent= studentDAL.getStudent(updateStudentId))!=null) {
			inputString = getStringInput(String.format(msg, "first name"));
			if (inputString!=""){
				updateStudent.setFirstName(inputString);
			}
			inputString = getStringInput(String.format(msg, "last name"));
			if (inputString!=""){
				updateStudent.setLastName(inputString);
			}
			inputString = getStringDobUpdate();
			if (inputString!=""){
				updateStudent.setDob(inputString);
			}
			updateStudent.setEmailAddress(generateEmail(updateStudent.getFirstName(),
					updateStudent.getLastName(),updateStudent.getDob()));
			inputInt = getIntInputToUpdate(String.format(msg, "midterm1"));
			if (inputInt!=-1){
				updateStudent.setMidterm1(inputInt);
			}
			inputInt = getIntInputToUpdate(String.format(msg, "midterm2"));
			if (inputInt!=-1){
				updateStudent.setMidterm2(inputInt);
			}
			inputInt = getIntInputToUpdate(String.format(msg, "Assignment1"));
			if (inputInt!=-1){
				updateStudent.setAssignment1(inputInt);
			}
			inputInt = getIntInputToUpdate(String.format(msg, "Assignment2"));
			if (inputInt!=-1){
				updateStudent.setAssignment2(inputInt);
			}	
			inputInt = getIntInputToUpdate(String.format(msg, "Assignment3"));
			if (inputInt!=-1){
				updateStudent.setAssignment3(inputInt);
			}	
			inputInt = getIntInputToUpdate(String.format(msg, "Assignment4"));
			if (inputInt!=-1){
				updateStudent.setAssignment4(inputInt);
			}	
			inputInt = getIntInputToUpdate(String.format(msg, "Assignment5"));
			if (inputInt!=-1){
				updateStudent.setAssignment5(inputInt);
			}	
			updateStudent.setFinalScore(calculateFinalScore(updateStudent.getMidterm1(),updateStudent.getMidterm2(),
					updateStudent.getAssignment1(),updateStudent.getAssignment2(),updateStudent.getAssignment3(),
					updateStudent.getAssignment4(),updateStudent.getAssignment5())); 
			updateStudent.setFinalGrade(calculateFinalGrade(updateStudent.getFinalScore()));
		}else {
			System.out.println("Student not found, please check if the id is correct.");
		}

	}

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
					mid1AboveAveCount++;
				} else if (student.getMidterm2() < averageMid2) {
					mid1BelowAveCount++;
				}

				if (student.getAssignment1() > averageA1) {
					A1AboveAveCount++;
				} else if (student.getAssignment2() < averageA1) {
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

	private Student userInputStudent() {
		String fn, ln, dob;
		int mid1, mid2, a1, a2, a3, a4, a5, finalScore;

		Student inputStudent = new Student.Builder().setFirstName(fn = getStringInput("first name"))
				.setLastName(ln = getStringInput("last name"))
				.setDOB(dob = getStringDob())
				.setEmailAddress(generateEmail(fn, ln, dob))
				.setMidterm1(mid1 = getIntInput("midterm 1"))
				.setMidterm2(mid2 = getIntInput("midterm 2"))
				.setAssignment1(a1 = getIntInput("assignment 1"))
				.setAssignment2(a2 = getIntInput("assignment 2"))
				.setAssignment3(a3 = getIntInput("assignment 3"))
				.setAssignment4(a4 = getIntInput("assignment 4"))
				.setAssignment5(a5 = getIntInput("assignment 5"))
				.setFinalScore(finalScore = calculateFinalScore(mid1, mid2, a1, a2, a3, a4, a5))
				.setFinalGrade(calculateFinalGrade(finalScore))
				.build();
		return inputStudent;
	}

	private int calculateFinalScore(int mid1, int mid2, int a1, int a2, int a3, int a4, int a5) {

		return (int) (mid1 * 0.25 + mid2 * 0.25 + a1 * 0.1 + a2 * 0.1+ a3 * 0.1 + a4 * 0.1 + a5 * 0.1);// todo is score
																										// integer?
	}

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

	private String generateEmail(String fn, String ln, String dob) {
		String[] dateSplit = dob.split("-");
		return fn + dateSplit[2] + ln + "@algomail.com";
	}

	private String getStringInput(String message) {
		System.out.println("Please input " + message+":");
		return scanner.nextLine();
	}

	// ask user to input date of birth
	private String getStringDob() {
		boolean inputValid = false;
		String input = "";
		while (inputValid == false) {
			try {
				System.out.println("Please input date of birth(format yyyy-mm-dd):");
				input = scanner.nextLine();
				Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(input);
				inputValid = true;
			} catch (Exception e) {
				System.out.println("invalid input, please try again.");
			}
		}
		return input;
	}
	// ask user to input date of birth
	private String getStringDobUpdate() {
		boolean inputValid = false;
		String input = "";
		while (inputValid == false) {
			try {
				System.out.println("Please input date of birth, format yyyy-mm-dd (press Enter key to skip):");
				input = scanner.nextLine();
				if (input=="") {
					return "";
				}
				Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(input);
			} catch (Exception e) {
				System.out.println("invalid input, please try again.");
			}
		}
		return input;
	}
	// ask user to input a int value
	private int getIntInput(String message) {
		int result = -1;
		boolean inputValid = false;

		String input;
		while (inputValid == false) {
			try {
				System.out.println("Please input " + message + ":");
				input = scanner.nextLine();
				if (input=="") {
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
	

	// ask user to input a int value
	private int getIntInputToUpdate(String message) {
		int result = -1;
		boolean inputValid = false;

		String input;
		while (inputValid == false) {
			try {
				System.out.println("Please input " + message + ":");
				input = scanner.nextLine();
				if (input=="") {
					return -1;
				}
				result = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("invalid input, please try again.");
			}
		}
		return result;
	}
}