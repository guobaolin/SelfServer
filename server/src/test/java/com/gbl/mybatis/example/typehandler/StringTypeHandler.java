package com.gbl.mybatis.example.typehandler;

import com.gbl.mybatis.example.entity.NewString;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by guobaolin on 2018/11/5.
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class StringTypeHandler extends BaseTypeHandler<NewString> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, NewString integer, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, integer);
    }

    @Override
    public NewString getNullableResult(ResultSet resultSet, String s) throws SQLException {
        NewString newString = new NewString();
        newString.setValue(resultSet.getString(s));
        return newString;
    }

    @Override
    public NewString getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return (NewString) resultSet.getObject(i);
    }

    @Override
    public NewString getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return (NewString) callableStatement.getObject(i);
    }
}
