package se.coredev.jdbc.recap.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.coredev.jdbc.recap.model.Employee;

public final class SQLEmployeeRepository implements EmployeeRepository {

	private static final String CONNECTION_URL = "jdbc:mysql://localhost/EmployeeDatabase?useSSL=false";
	private static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM Employee WHERE employeeID = ?";
	private static final String GET_ALL_EMPLOYEES = "SELECT * FROM Employee";

	@Override
	public Employee get(Long id) throws RepositoryException {

		try (Connection connection = getConnection();
		        PreparedStatement statement = createStatement(connection, GET_EMPLOYEE_BY_ID, id)) {

			ResultSet result = statement.executeQuery();

			if (result.next()) { return extractEmployee(result); }
			throw new RepositoryException("Could not find emlpoyee with id:" + id);
		}
		catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Employee> getAll() throws RepositoryException {
		try (Connection connection = getConnection();
		        PreparedStatement statement = createStatement(connection, GET_ALL_EMPLOYEES)) {

			ResultSet result = statement.executeQuery();
			List<Employee> employees = new ArrayList<>();

			if (result.next()) {
				employees.add(extractEmployee(result));
			}

			return employees;
		}
		catch (SQLException e) {
			throw new RepositoryException(e);
		}
	}

	private Employee extractEmployee(ResultSet result) throws SQLException {

		Long employeeId = result.getLong(1);
		String firstName = result.getString(2);
		String lastName = result.getString(3);
		String number = result.getString(4);

		return new Employee(employeeId, firstName, lastName, number);
	}

	private PreparedStatement createStatement(Connection connection, String query, Object... parameters) throws SQLException {

		PreparedStatement statement = connection.prepareStatement(query);

		for (int i = 0; i < parameters.length; i++) {
			statement.setObject(i + 1, parameters[i]);
		}

		return statement;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTION_URL, "root", "secret");
	}

}
