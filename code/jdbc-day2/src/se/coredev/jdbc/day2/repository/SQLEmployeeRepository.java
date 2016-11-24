package se.coredev.jdbc.day2.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import se.coredev.jdbc.day2.model.Employee;

public final class SQLEmployeeRepository implements EmployeeRepository {

	private static final String CONNECTION_URL = "jdbc:mysql://localhost/EmployeeDatabase?useSSL=false";

	@Override
	public Employee get(Long id) {

		try (Connection connection = DriverManager.getConnection(CONNECTION_URL, "root", "secret");
		        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee WHERE employeeID = ?")) {

			statement.setLong(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				Long employeeId = result.getLong(1);
				String firstName = result.getString(2);
				String lastName = result.getString(3);
				String number = result.getString(4);

				return new Employee(employeeId, firstName, lastName, number);
			}
			throw new RuntimeException("Could not find emlpoyee with id:" + id);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
