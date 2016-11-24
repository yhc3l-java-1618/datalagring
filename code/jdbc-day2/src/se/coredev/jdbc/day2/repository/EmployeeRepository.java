package se.coredev.jdbc.day2.repository;

import se.coredev.jdbc.day2.model.Employee;

public interface EmployeeRepository {

	Employee get(Long id);
	
}
