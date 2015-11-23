package org.kanomchan.core.common.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public interface RowMapperClone<T> extends RowMapper<T> {

	public T mapRow(ResultSet rs, int rowNum,T targetOut) throws SQLException;
}
