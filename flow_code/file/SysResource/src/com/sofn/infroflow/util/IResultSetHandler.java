package com.sofn.infroflow.util;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {

    T handler(ResultSet rSet);

}
