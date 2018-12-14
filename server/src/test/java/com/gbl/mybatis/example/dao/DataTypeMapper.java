package com.gbl.mybatis.example.dao;

import com.gbl.mybatis.example.entity.DataType;

/**
 * Created by guobaolin on 2018/11/5.
 */
public interface DataTypeMapper {

    DataType selectById(Integer dataTypeId);
}
