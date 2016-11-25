package se.coredev.jdbc.recap.service;

import se.coredev.jdbc.recap.model.Employee;
import se.coredev.jdbc.recap.repository.EmployeeRepository;
import se.coredev.jdbc.recap.repository.RepositoryException;

public final class EmployeeService {

	private final EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee getEmployee(Long id) {
		try {
			return repository.get(id);
		}
		catch (RepositoryException e) {
			throw new ServiceException(e);
		}
	}

}
