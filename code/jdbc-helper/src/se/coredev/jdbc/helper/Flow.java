package se.coredev.jdbc.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class Flow {

	private final String databaseUrl;
	
	public Flow(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}
	
	public OperationBuilder sql(String sql) {
		return new OperationBuilderImpl(this, sql);
	}
	
	private int executeUpdate(String sql, List<Object> parameters) {
		
		try(Connection connection = getConnection(databaseUrl);
			PreparedStatement statement = prepareStatement(connection, sql, parameters, false)) {
			
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Could not execute update: " + sql, e);
		}
	}

	private <T> List<T> executeUpdate(String sql, List<Object> parameters, Mapper<T> keyMapper) {
		
		try(Connection connection = getConnection(databaseUrl);
			PreparedStatement statement = prepareStatement(connection, sql, parameters, true)) {
			
				List<T> keys = new ArrayList<>(); 

				statement.executeUpdate();
				ResultSet resultKeys = statement.getGeneratedKeys();
				
				while(resultKeys.next()){
					keys.add(keyMapper.map(resultKeys));
				}
				
				return keys;
				
			} catch (SQLException e) {
				throw new RuntimeException("Could not execute update: " + sql, e);
			}
	}
	
	private <T> List<T> executeQuery(String sql, List<Object> parameters, Mapper<T> rowMapper) {
		try (Connection connection = getConnection(databaseUrl);
			 PreparedStatement statement = prepareStatement(connection, sql, parameters, false);
			 ResultSet resultSet = statement.executeQuery()) {
			
			List<T> result = new ArrayList<>();
			
			while(resultSet.next()) {
				result.add(rowMapper.map(resultSet));
			}

			return result;
			
		} catch(SQLException e){
			throw new RuntimeException("Could not execute query:" + sql, e);
		}
	}
	
	private static Connection getConnection(String databaseUrl) throws SQLException {
		return DriverManager.getConnection(databaseUrl);
	}
	
	private static PreparedStatement prepareStatement(Connection connection, String sql, List<Object> parameters, boolean returnKeys) throws SQLException {
		PreparedStatement statement = returnKeys ? connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) :
									  			   connection.prepareStatement(sql);	
		for(int i = 0; i < parameters.size(); i++){
			statement.setObject(i +1, parameters.get(i));
		}
		
		return statement;
	}
	
	private static final class OperationBuilderImpl implements OperationBuilder {

		private final List<Object> parameters;
		private final Flow flow;
		private final String sql;
		
		public OperationBuilderImpl(Flow flow, String sql) {
			this.parameters = new ArrayList<>();
			this.flow = flow;
			this.sql = sql;
		}
		
		@Override
		public OperationBuilder parameter(Object parameter) {
			parameters.add(parameter);
			return this;
		}

		@Override
		public <T> List<T> query(Mapper<T> rowMapper) {
			return flow.executeQuery(sql, parameters, rowMapper);
		}

		@Override
		public int execute() {
			return flow.executeUpdate(sql, parameters);
		}

		@Override
		public <T> List<T> execute(Mapper<T> keyMapper) {
			return flow.executeUpdate(sql, parameters, keyMapper);
		}
		
	}

}
