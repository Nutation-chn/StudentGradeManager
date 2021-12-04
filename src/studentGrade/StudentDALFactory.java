package studentGrade;

/**Factory to create DAL, currently only one database connector is available
 * @author Dong Zhang
 *
 */
public class StudentDALFactory {

	public StudentDAL getStudentDAL(String dal) {
		if (dal=="jdbc")
		{
			return new StudentDALImp();
		}else
		return null;
	}

	
}
