package studentGrade;

import java.util.ArrayList;
import java.util.List;

public interface StudentDAL {
	List<Student> getAll();
	Student getStudent( int id);
	boolean add(Student student);
	void updateStudent(int id, String firstName, String lastName, String dob, String emailAddress, int midterm1,
			int midterm2, int assignment1, int assignment2, int assignment3, int assignment4, int assignment5,
			int finalScore, String finalGrade);
	void deleteStudent(int id);
}
