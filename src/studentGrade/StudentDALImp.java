package studentGrade;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDALImp implements StudentDAL {
	
	private static final String URL = "jdbc:mysql://localhost:3306/books";
	private static final String USER = "Group10";
	private static final String PASS = "8288";
	
	// These SQL need to be redefined
	private static final String SQL_GET_ALL = "select * from books.authors";         
	private static final String SQL_GET_WITH_ID = "select * from books.authors where AuthorID=?";
	private static final String SQL_INSERT = "insert into books.authors (FirstName, LastName) values (?,?);";
	private static final String SQL_UPDATE = "update books.authors set FirstName = ?  where id=?";
	private static final String SQL_DELETE = "delete from books.authors where id=?";
	
	@Override
	public List<Student> getAll() {
		List<Student> list = new ArrayList<Student>();
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_GET_ALL);
				ResultSet result = statement.executeQuery()){

			while( result.next()){
				list.add( new Student(
						result.getInt( "StudentID"),
						result.getString( "FirstName"),
						result.getString( "LastName"),
						result.getString("DateofBirth"),
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
							result.getInt( "StudentID"),
							result.getString( "FirstName"),
							result.getString( "LastName"),
							result.getString("DateofBirth"),
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
			
			//database set id auto incremental, insert sql remove id. //zd 20210.12.01
			statement.setString( 2, student.getFirstName());
			statement.setString( 3, student.getLastName());
			statement.setString( 4, student.getDob());
			statement.setString( 5, student.getEmailAddress());
			statement.setInt( 6, student.getMidterm1());
			statement.setInt( 7, student.getMidterm2());
			statement.setInt( 8, student.getAssignment1());
			statement.setInt( 9, student.getAssignment2());
			statement.setInt( 10, student.getAssignment3());
			statement.setInt( 11, student.getAssignment4());
			statement.setInt( 12, student.getAssignment5());
			statement.setInt( 13, student.getFinalScore());
			statement.setString( 14, student.getFinalGrade());
			return statement.executeUpdate()>=1;

		}catch( SQLException e){
			e.printStackTrace();
		}
		return ; //todo return if successful zd
	}

	@Override
	//todo return if successful zd
	public boolean updateStudent(Student student) {
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_UPDATE)){
			statement.setInt( 1, student.getId());
			statement.setString( 2, student.getFirstName());
			statement.setString( 3, student.getLastName());
			statement.setString( 4, student.getDob());
			statement.setString( 5, student.getEmailAddress());
			statement.setInt( 6, student.getMidterm1());
			statement.setInt( 7, student.getMidterm2());
			statement.setInt( 8, student.getAssignment1());
			statement.setInt( 9, student.getAssignment2());
			statement.setInt( 10, student.getAssignment3());
			statement.setInt( 11, student.getAssignment4());
			statement.setInt( 12, student.getAssignment5());
			statement.setInt( 13, student.getFinalScore());
			statement.setString( 14, student.getFinalGrade());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	//todo return if successful zd
	public boolean deleteStudent(int id) {
		try( Connection con = DriverManager.getConnection( URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement( SQL_DELETE)){
			statement.setInt( 1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
