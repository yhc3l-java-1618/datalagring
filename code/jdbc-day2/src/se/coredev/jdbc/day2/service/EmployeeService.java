package se.coredev.jdbc.day2.service;

import se.coredev.jdbc.day2.model.Employee;
import se.coredev.jdbc.day2.repository.EmployeeRepository;

public final class EmployeeService {

	private final EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee getEmployee(Long id) {
		return repository.get(id);
	}

}
