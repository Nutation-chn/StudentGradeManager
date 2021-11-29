package studentGrade;

import java.util.List;

/**
 * Interface: StudentDAL
 * Implemented by StudentDALImp
 * @author Andrew
 *
 */
public interface StudentDAL {
	List<Student> getAll();
	Student getStudent( int id);
	boolean add(Student student);
	void updateStudent(Student student);
	void deleteStudent(int id);
}
