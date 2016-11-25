package se.coredev.jdbc.recap.repository;

import java.util.List;

import se.coredev.jdbc.recap.model.Employee;

public interface EmployeeRepository {

	Employee get(Long id) throws RepositoryException;
	
	List<Employee> getAll() throws RepositoryException;
	
}
