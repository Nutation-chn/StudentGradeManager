package studentGrade;
import java.util.List;
/**
 * Interface: StudentDAL
 * This interface will be implemented by StudentDALImp
 * @author Feng Chen, Dong Zhang, Jiasi Shen
 */
public interface StudentDAL {
	List<Student> getAll();
	Student getStudent( int id);           // Get student information by id
	boolean add(Student student);		   // Add a new student
	boolean updateStudent(int id, String column, String string);        // Update student information
	boolean updateStudent(int id, String column, int integer);          // Update student information
	boolean deleteStudent(int id);										// Delete a student by id
	boolean updateStudent(Student updateStudent);                       // Update student information
}