package se.coredev.jdbc.recap;

import se.coredev.jdbc.recap.model.Employee;
import se.coredev.jdbc.recap.repository.SQLEmployeeRepository;
import se.coredev.jdbc.recap.service.EmployeeService;

public final class Main {

	public static void main(String[] args) {
		
		EmployeeService service = new EmployeeService(new SQLEmployeeRepository());
		Employee employee = service.getEmployee(10L);
		
		System.out.println(employee);
	}
	
	
}
