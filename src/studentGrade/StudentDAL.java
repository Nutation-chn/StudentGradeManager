package studentGrade;

import java.util.ArrayList;
import java.util.List;

public interface StudentDAL {
	List<Student> getAll();
	Student getStudent( int id);
	boolean add(Student student);
	void updateStudent(Student student);
	void deleteStudent(int id);
}
