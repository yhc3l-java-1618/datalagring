package se.coredev.jdbc.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper <T> {

	T map(ResultSet row) throws SQLException;
}
