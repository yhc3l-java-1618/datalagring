package se.coredev.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Main {

	private static final String CONNECTION_URL = "jdbc:mysql://localhost/EmployeeDatabase?useSSL=false";

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection(CONNECTION_URL, "root", "secret");
		        Statement statement = connection.createStatement()) {

			// CREATE
//			int affectedRows = statement.executeUpdate("INSERT INTO Employee VALUES (NULL, 'Luke', 'Skywalker', '4004')");
//			System.out.println("Rows affected: " + affectedRows);
			
			// READ
			ResultSet result = statement.executeQuery("SELECT * FROM Employee");
			while (result.next()) {
				
				int id = result.getInt(1);
				String firstName = result.getString(2);
				String lastName = result.getString(3);
				String number = result.getString(4);
				
				System.out.printf("%s, %s, %s, %s\n", id, firstName, lastName, number);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
