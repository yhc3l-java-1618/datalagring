package se.coredev.jdbc.helper;

import java.util.List;

public interface OperationBuilder {

	OperationBuilder parameter(Object parameter);
	
	int execute();
	
	<T> List<T> execute(Mapper<T> keyMapper);
	
	<T> List<T> query(Mapper<T> rowMapper);
}
