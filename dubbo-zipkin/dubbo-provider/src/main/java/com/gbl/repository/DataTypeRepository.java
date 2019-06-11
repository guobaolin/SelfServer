package com.gbl.repository;

import com.gbl.api.entity.DataType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by guobaolin on 2018/11/1.
 */
@Mapper
public interface DataTypeRepository {

    @Select("select * from tb_data_type where data_type_id = #{dataTypeId}")
    DataType findByDataTypeId(@Param(value = "dataTypeId") Integer dataTypeId);

    @Insert("insert into tb_data_type(data_type_name, data_type_name_en, status,protocol_type, module_type, data_type) " +
            "values(#{dataType.dataTypeName}, #{dataType.dataTypeNameEn},#{dataType.status}, #{dataType.protocolType}," +
            "#{dataType.moduleType}, #{dataType.dataType})")
    Integer addDataType(@Param("dataType") DataType dataType);

}
