package studentGrade;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentController {

	
	private StudentDALFactory studentDALFactory = new StudentDALFactory();
	private StudentDAL studentDAL =studentDALFactory.getStudentDAL("jdbc");
	private Scanner scanner = new Scanner(System.in);

	public void addStudent() {
		System.out.println("Start to add new student information:");
		if(!studentDAL.add(userInputStudent())) {
			System.out.println("Data add successfully.");
		}else {
			System.out.println("Data add failure.");
		}
	}
	
	public void deleteStudent() {
		
	}
	public void getStudentById() {
		Student student = studentDAL.getStudent(getIntInput("student ID"));
		if (student!=null) {
			System.out.println(student);
		}else {
			System.out.println("Student not found, please check if the id is correct.");
		}
	}
	public void getAll() {
		List<Student> studentList=studentDAL.getAll();
		if(studentList!=null) {
			for (Student student:studentList) {
				System.out.println(student);
			}
		}
	}
	
	public void updateStudent() {
		
	}
	
	public void getStatics() {
		List<Student> studentList=studentDAL.getAll();
		int studentCount=studentList.size();
		int averageMid1=0, averageMid2=0, averageA1=0, averageA2=0, averageA3=0, averageA4=0, averageA5=0;
		int averageFinalScore=0;
		int passCount=0, failCount=0, highestFinalScore=0, lowestFinalScore=100, 
				mid1AboveAveCount=0,  mid1BelowAveCount=0,
				mid2AboveAveCount=0,  mid2BelowAveCount=0,
				A1AboveAveCount=0,A1BelowAveCount=0,
				A2AboveAveCount=0,A2BelowAveCount=0,
				A3AboveAveCount=0,A3BelowAveCount=0,
				A4AboveAveCount=0,A4BelowAveCount=0,
				A5AboveAveCount=0,A5BelowAveCount=0;
			
		if (studentList!=null) {
			for (Student student:studentList) {
				averageMid1+=student.getMidterm1();
				averageMid2+=student.getMidterm2();
				averageA1+=student.getAssignment1();
				averageA2+=student.getAssignment2();
				averageA3+=student.getAssignment3();
				averageA4+=student.getAssignment4();
				averageA5+=student.getAssignment5();
				int currentFinalScore = student.getFinalScore();
				averageFinalScore+=currentFinalScore;
				if (currentFinalScore>=50) {
					passCount+=1;
				}
				highestFinalScore=(highestFinalScore<currentFinalScore?currentFinalScore:highestFinalScore);
				lowestFinalScore=(lowestFinalScore>currentFinalScore?currentFinalScore:highestFinalScore);			
			}
			averageMid1/=studentCount;
			averageMid2/=studentCount;
			averageA1/=studentCount;
			averageA2/=studentCount;
			averageA3/=studentCount;
			averageA4/=studentCount;
			averageA5/=studentCount;
			averageFinalScore/=studentCount;
			for (Student student:studentList) {
				if (student.getMidterm1()>averageMid1) {
					mid1AboveAveCount++;
				}else if (student.getMidterm1()<averageMid1) {
					mid1BelowAveCount++;
				}
				
				if (student.getMidterm2()>averageMid2) {
					mid1AboveAveCount++;
				}else if (student.getMidterm2()<averageMid2) {
					mid1BelowAveCount++;
				}
				
				if (student.getAssignment1()>averageA1) {
					A1AboveAveCount++;
				}else if (student.getAssignment2()<averageA1) {
					A1BelowAveCount++;
				}
				
				if (student.getAssignment2()>averageA2) {
					A2AboveAveCount++;
				}else if (student.getAssignment2()<averageA2) {
					A2BelowAveCount++;
				}
				
				if (student.getAssignment3()>averageA3) {
					A3AboveAveCount++;
				}else if (student.getAssignment3()<averageA3) {
					A3BelowAveCount++;
				}
				
				if (student.getAssignment4()>averageA4) {
					A4AboveAveCount++;
				}else if (student.getAssignment4()<averageA4) {
					A4BelowAveCount++;
				}
				
				if (student.getAssignment5()>averageA5) {
					A5AboveAveCount++;
				}else if (student.getAssignment5()<averageA5) {
					A5BelowAveCount++;
				}
				
			}
			System.out.println("");//todo print statistics
		}else 
		{
			System.out.println("No student record.");
		}

	}
	private Student userInputStudent(){
		String fn,ln,dob;
		int mid1 ,mid2, a1,a2,a3,a4,a5,finalScore;
		Student inputStudent = new Student.Builder()
				.setFirstName(fn=getStringInput("first name"))
				.setLastName(ln=getStringInput("last name"))
				.setDOB(dob=getStringDob())
				.setEmailAddress(generateEmail(fn,ln,dob))
				.setMidterm1(mid1=getIntInput("midterm 1"))
				.setMidterm2(mid2=getIntInput("midterm 2"))
				.setAssignment1(a1=getIntInput("assignment 1"))
				.setAssignment2(a2=getIntInput("assignment 2"))
				.setAssignment3(a3=getIntInput("assignment 3"))
				.setAssignment4(a4=getIntInput("assignment 4"))
				.setAssignment5(a5=getIntInput("assignment 5"))
				.setFinalScore(finalScore=calculateFinalScore(mid1,mid2,a1,a2,a3,a4,a5))
				.setFinalGrade(calculateFinalGrade(finalScore))
				.build();
		return inputStudent;
	}
	
	private int calculateFinalScore(int mid1, int mid2, int a1, int a2, int a3, int a4, int a5) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String calculateFinalGrade(int finalScore) {
		// TODO Auto-generated method stub
		return "";
	}

	private String generateEmail(String fn, String ln, String dob) {
		// TODO generate email using fn ln and dob.
		return null;
	}

	private String getStringInput(String message) {
		System.out.println("Please input "+message);
		return scanner.nextLine();
	}
	
	//ask user to input date of birth
	private String getStringDob() {
		boolean inputValid=false;
		String input="";
		while (inputValid ==false) {
		try {
			System.out.println("Please input date of birth(format dd/mm/yyyy):");
			input =  scanner.nextLine();
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(input);  
		}catch(Exception e) {
			System.out.println("invalid input, please try again.");
		}
		}
		return input;
	}
	
	//ask user to input a int value
	private int getIntInput(String message) {
		int result=-1;
		boolean inputValid=false;
		
		String input;
		while (inputValid ==false) {
		try {
			System.out.println("Please input "+message+":");
			input =  scanner.nextLine();
			result=Integer.parseInt(input);
		}catch(Exception e) {
			System.out.println("invalid input, please try again.");
		}
		}
		return result;
	}

}
