package studentGrade;

/**
 * This class stores student information
 * @author Feng Chen
 *
 */
public class Student {
	
	// Field
	private int id;
	private String firstName;
	private String lastName;
	private String dob;
	private String emailAddress;
	private int midterm1;
	private int midterm2;
	private int assignment1;
	private int assignment2;
	private int assignment3;
	private int assignment4;
	private int assignment5;
	private int finalScore;
	private String finalGrade;
	
	// Constructors
	public Student(Builder builder) {
		id = builder.id;
		firstName = builder.firstName;
		lastName = builder.lastName;
		dob = builder.dob;
		emailAddress = builder.emailAddress;
		midterm1 = builder.midterm1;
		midterm2 = builder.midterm2;
		assignment1 = builder.assignment1;
		assignment2 = builder.assignment2;
		assignment3 = builder.assignment3;
		assignment4 = builder.assignment4;
		assignment5 = builder.assignment5;
		finalScore = builder.finalScore;
		finalGrade = builder.finalGrade;
	}
	
	public Student(int id, String firstName, String lastName, String dob, String emailAddress, int midterm1,
			int midterm2, int assignment1, int assignment2, int assignment3, int assignment4, int assignment5,
			int finalScore, String finalGrade) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.emailAddress = emailAddress;
		this.midterm1 = midterm1;
		this.midterm2 = midterm2;
		this.assignment1 = assignment1;
		this.assignment2 = assignment2;
		this.assignment3 = assignment3;
		this.assignment4 = assignment4;
		this.assignment5 = assignment5;
		this.finalScore = finalScore;
		this.finalGrade = finalGrade;
	}
	
	/**
	 *  Inner class: Builder
	 * @author Feng Chen
	 *
	 */
	public static class Builder{
		private final int id;
		private String firstName;
		private String lastName;
		private String dob;
		private String emailAddress;
		private int midterm1;
		private int midterm2;
		private int assignment1;
		private int assignment2;
		private int assignment3;
		private int assignment4;
		private int assignment5;
		private int finalScore;
		private String finalGrade;
		
		public Builder(int id) {
			this.id = id;
		}
		
		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder setDOB(String dob) {
			this.dob = dob;
			return this;
		}
		
		public Builder setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}
		
		public Builder setMidterm1(int midterm1) {
			this.midterm1 = midterm1;
			return this;
		}
		
		public Builder setMidterm2(int midterm2) {
			this.midterm2 = midterm2;
			return this;
		}
		
		public Builder setAssignment1(int assignment1) {
			this.assignment1 = assignment1;
			return this;
		}
		
		public Builder setAssignment2(int assignment2) {
			this.assignment2 = assignment2;
			return this;
		}
		
		public Builder setAssignment3(int assignment3) {
			this.assignment3 = assignment3;
			return this;
		}
		
		public Builder setAssignment4(int assignment4) {
			this.assignment4 = assignment4;
			return this;
		}
		
		public Builder setAssignment5(int assignment5) {
			this.assignment5 = assignment5;
			return this;
		}
		
		public Builder setFinalScore(int finalScore) {
			this.finalScore = finalScore;
			return this;
		}
		
		public Builder setFinalGrade(String finalGrade) {
			this.finalGrade = finalGrade;
			return this;
		}
		
		public Student getStudent() {
			return new Student(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getMidterm1() {
		return midterm1;
	}

	public void setMidterm1(int midterm1) {
		this.midterm1 = midterm1;
	}

	public int getMidterm2() {
		return midterm2;
	}

	public void setMidterm2(int midterm2) {
		this.midterm2 = midterm2;
	}

	public int getAssignment1() {
		return assignment1;
	}

	public void setAssignment1(int assignment1) {
		this.assignment1 = assignment1;
	}

	public int getAssignment2() {
		return assignment2;
	}

	public void setAssignment2(int assignment2) {
		this.assignment2 = assignment2;
	}

	public int getAssignment3() {
		return assignment3;
	}

	public void setAssignment3(int assignment3) {
		this.assignment3 = assignment3;
	}

	public int getAssignment4() {
		return assignment4;
	}

	public void setAssignment4(int assignment4) {
		this.assignment4 = assignment4;
	}

	public int getAssignment5() {
		return assignment5;
	}

	public void setAssignment5(int assignment5) {
		this.assignment5 = assignment5;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}  	
	
	@Override
	public String toString() {
		return "";
	}	
}

	