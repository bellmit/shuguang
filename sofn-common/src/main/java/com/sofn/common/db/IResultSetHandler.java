package com.sofn.common.db;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {

    T handler(ResultSet rSet);

}
