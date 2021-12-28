package com.sofn.ducss.db;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {

    T handler(ResultSet rSet);

}
