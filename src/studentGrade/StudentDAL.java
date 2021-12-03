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

	boolean updateStudent(Student student);

	boolean updateStudent(int id, String column, String string);
	boolean updateStudent(int id, String column, int integer);

	boolean deleteStudent(int id);
}
