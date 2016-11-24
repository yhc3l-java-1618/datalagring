package se.coredev.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import se.coredev.jdbc.day2.model.Employee;
import se.coredev.jdbc.day2.repository.SQLEmployeeRepository;
import se.coredev.jdbc.day2.service.EmployeeService;

public final class Main {

	private static final String CONNECTION_URL = "jdbc:mysql://localhost/EmployeeDatabase?useSSL=false";

	public static void main(String[] args) {
		
		EmployeeService service = new EmployeeService(new SQLEmployeeRepository());
		Employee employee = service.getEmployee(10L);
		
		System.out.println(employee);
	}
	
	
	// Auto generated keys
//	public static void main(String[] args) {
//		
//		try (Connection connection = DriverManager.getConnection(CONNECTION_URL, "root", "secret");
//			 Statement statement = connection.createStatement()) {
//			
//			statement.executeUpdate("INSERT INTO Employee VALUES (null, 'Luke', 'Skywalker', '4004')", 
//			                        Statement.RETURN_GENERATED_KEYS);
//			
//			ResultSet keys = statement.getGeneratedKeys();
//			
//			if(keys.next()){
//				System.out.println("EmployeeId:" + keys.getLong(1));
//			}
//			
//		}
//		catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}
	
	// Transaction
//	public static void main(String[] args) {
//		
//		try (Connection connection = DriverManager.getConnection(CONNECTION_URL, "root", "secret")) {
//
//			connection.setAutoCommit(false);
//			
//			try (Statement statement = connection.createStatement()) {
//
//				statement.executeUpdate("INSERT INTO Employee VALUES (null, 'Luke', 'Skywalker', '4004')");
//				statement.executeUpdate("INSERT INTO Employee VALUES (null, 'Leia', 'Skywalker', '5005')");
////				statement.executeUpdate("INSERT INTO Employee VALUES (null, 'Anakin', 'Skywalker')");
//				
//				connection.commit();
//			}
//
//			catch (SQLException e) {
//				connection.rollback();
//				throw e;
//			}
//		}
//		catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}

	// Prepared statement
	// public static void main(String[] args) {
	// try (Connection connection = DriverManager.getConnection(CONNECTION_URL,
	// "root", "secret");
	// PreparedStatement statement = connection.prepareStatement("INSERT INTO
	// Employee VALUES (null, ?, ?, ?)")) {
	//
	// for (int i = 1; i < 11; i++) {
	// statement.setString(1, "firstName" + i);
	// statement.setString(2, "lastName" + i);
	// statement.setString(3, "" + i);
	//
	// statement.executeUpdate();
	// }
	//
	//// statement.setString(1, "Luke");
	//// statement.setString(2, "Skywalker");
	//// statement.setString(3, "4004");
	////
	//// statement.executeUpdate();
	// }
	// catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// public static void main(String[] args) {
	// try (Connection connection = DriverManager.getConnection(CONNECTION_URL,
	// "root", "secret");
	// PreparedStatement statement = connection.prepareStatement("SELECT * FROM
	// Employee WHERE employeeNumber = ?")) {
	//
	// statement.setString(1, "2002");
	//
	// ResultSet result = statement.executeQuery();
	//
	// while(result.next()) {
	//
	// int id = result.getInt(1);
	// String firstName = result.getString(2);
	// String lastName = result.getString(3);
	// String number = result.getString(4);
	//
	// System.out.printf("%s, %s, %s, %s\n", id, firstName, lastName, number);
	// }
	// }
	// catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
}
