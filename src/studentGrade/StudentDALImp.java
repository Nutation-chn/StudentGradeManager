package studentGrade;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Class: StudentDALImp
 * This class implements interface: StudentDAL
 * @author Feng Chen, Dong Zhang, Jiasi Shen
 *
 */
public class StudentDALImp implements StudentDAL {
	
	private static final String URL = "jdbc:mysql://localhost:3306/StudentDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "cst8288";
	private static final String PASS = "8288";
	
	private static final String SQL_GET_ALL = "select * from StudentDB.StudentRecord";         
	private static final String SQL_GET_WITH_ID = "select * from StudentDB.StudentRecord where Id=?";
	private static final String SQL_INSERT = "insert into StudentDB.StudentRecord (FirstName, LastName, DateOfBirth, EmailAddress, Midterm1, Midterm2, "
			+ "Assignment1, Assignment2, Assignment3, Assignment4, Assignment5, FinalScore, FinalGrade) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SQL_UPDATE = "update StudentDB.StudentRecord set ? = ?  where Id=?";
	private static final String SQL_DELETE = "delete from StudentDB.StudentRecord where Id=?";
	private static final String SQL_UPDATE_ROW = "	update StudentDB.StudentRecord set FirstName=?,LastName=?, DateOfBirth=?, EmailAddress=?, "
			+ "			Midterm1=?, Midterm2=?, Assignment1=?, Assignment2=?, "
			+ "			Assignment3=?, Assignment4=?, Assignment5=?, FinalScore=?, FinalGrade=?  where Id=?";

	
	@Override
	public List<Student> getAll() {
		List<Student> list = new ArrayList<Student>();
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_GET_ALL);
				ResultSet result = statement.executeQuery()){

			while( result.next()){
				list.add( new Student(
						result.getInt( "Id"),
						result.getString( "FirstName"),
						result.getString( "LastName"),
						result.getString("DateOfBirth"),
						result.getString("EmailAddress"),
						result.getInt("Midterm1"),
						result.getInt("Midterm2"),
						result.getInt("Assignment1"),
						result.getInt("Assignment2"),
						result.getInt("Assignment3"),
						result.getInt("Assignment4"),
						result.getInt("Assignment5"),
						result.getInt("FinalScore"),
						result.getString("FinalGrade")));
			}

		}catch( SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Student getStudent(int id) {
		Student student = null;
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_GET_WITH_ID)){
			statement.setInt( 1, id);
			try( ResultSet result = statement.executeQuery()){

				while( result.next()){
					student = new Student(
							result.getInt( "Id"),
							result.getString( "FirstName"),
							result.getString( "LastName"),
							result.getString("DateOfBirth"),
							result.getString("EmailAddress"),
							result.getInt("Midterm1"),
							result.getInt("Midterm2"),
							result.getInt("Assignment1"),
							result.getInt("Assignment2"),
							result.getInt("Assignment3"),
							result.getInt("Assignment4"),
							result.getInt("Assignment5"),
							result.getInt("FinalScore"),
							result.getString("FinalGrade"));
				}
			}

		}catch( SQLException e){
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public boolean add(Student student) {
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_INSERT)){
			statement.setString( 1, student.getFirstName());
			statement.setString( 2, student.getLastName());
			statement.setString( 3, student.getDob());
			statement.setString( 4, student.getEmailAddress());
			statement.setInt( 5, student.getMidterm1());
			statement.setInt( 6, student.getMidterm2());
			statement.setInt( 7, student.getAssignment1());
			statement.setInt( 8, student.getAssignment2());
			statement.setInt( 9, student.getAssignment3());
			statement.setInt( 10, student.getAssignment4());
			statement.setInt( 11, student.getAssignment5());
			statement.setInt( 12, student.getFinalScore());
			statement.setString( 13, student.getFinalGrade());
			return statement.executeUpdate()>=1;

		}catch( SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStudent(int id, String column, String string) {
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_UPDATE)){
			statement.setInt( 1, id);
			statement.setString( 2, column);
			statement.setString( 3, string);
			return statement.executeUpdate()>=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateStudent(int id, String column, int integer) {
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_UPDATE)){
			statement.setInt( 1, id);
			statement.setString( 2, column);
			statement.setInt( 3, integer);
			return statement.executeUpdate()>=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteStudent(int id) {
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_DELETE)){
			statement.setInt( 1, id);
			return statement.executeUpdate()>=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStudent(Student updateStudent) {
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_UPDATE_ROW)){
			statement.setString( 1, updateStudent.getFirstName());
			statement.setString( 2, updateStudent.getLastName());
			statement.setString( 3, updateStudent.getDob());
			statement.setString( 4, updateStudent.getEmailAddress());
			statement.setInt( 5, updateStudent.getMidterm1());
			statement.setInt( 6, updateStudent.getMidterm2());
			statement.setInt( 7, updateStudent.getAssignment1());
			statement.setInt( 8, updateStudent.getAssignment2());
			statement.setInt( 9, updateStudent.getAssignment3());
			statement.setInt( 10, updateStudent.getAssignment4());
			statement.setInt( 11, updateStudent.getAssignment5());
			statement.setInt( 12, updateStudent.getFinalScore());
			statement.setString( 13, updateStudent.getFinalGrade());
			statement.setInt( 14, updateStudent.getId());
			return statement.executeUpdate()>=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
