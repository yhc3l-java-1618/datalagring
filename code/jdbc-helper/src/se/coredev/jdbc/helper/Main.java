package se.coredev.jdbc.helper;

import java.util.List;

public final class Main {

	private static final String URL = "jdbc:mysql://localhost/EmployeeDatabase?useSSL=false&user=root&password=secret";
	private static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM Employee WHERE employeeID = ?";
	private static final String GET_ALL_EMPLOYEES = "SELECT * FROM Employee";
	private static final String INSERT_EMPLOYEE = "INSERT INTO Employee VALUES (null, ?, ?, ?)";

	public static void main(String[] args) {

		List<Long> keys = new Flow(URL).sql(INSERT_EMPLOYEE)
									   .parameter("Anders")
									   .parameter("Carlsson")
									   .parameter("AC-1001")
									   .execute(r -> r.getLong(1));

		keys.forEach(System.out::println);
		
		
//		int affectedRows = new Flow(URL).sql(INSERT_EMPLOYEE)
//										.parameter("Anders")
//										.parameter("Carlsson")
//										.parameter("AC-1001")
//										.execute();
//		
//		System.out.println("Affected rows:" + affectedRows);
		
//		List<Employee> result = new Flow(URL).sql(GET_EMPLOYEE_BY_ID)
//		                                     .parameter("1")
//		                                     .query(r -> new Employee(r.getLong(1), r.getString(2),
//		                                                              r.getString(3), r.getString(4)));
//		
//		result.forEach(System.out::println);
	}

}
