package DomainModel;

public class Employee extends User {

	private String empName;
	
	public Employee(String name, String pass, String firstName) {
		super(name, pass);
		this.empName = firstName;
		// TODO Auto-generated constructor stub
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
