package studentGrade;

public class StudentDALFactory {

	public StudentDAL getStudentDAL(String dal) {
		if (dal=="jdbc")
		{
			return new StudentDALImp();
		}else
		return null;
	}

}
