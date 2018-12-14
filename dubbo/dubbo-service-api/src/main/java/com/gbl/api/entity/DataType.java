package com.gbl.api.entity;

import java.io.Serializable;

/**
 * Created by guobaolin on 2018/11/1.
 */
public class DataType implements Serializable {
    private static final long serialVersionUID = -4229807003977895101L;

    //数据类型id
    private Integer dataTypeId;
    //数据类型
    private String dataTypeName;
    //数据类型英文名称
    private String dataTypeNameEn;
    //tb_data_type.status (有效状态：0-无效 1-有效)
    private Integer status;
    //所属协议类型(1-第三方协议 2-内部协议 3-其他)
    private Integer protocolType;
    //通信模块类型: 1-WiFi，2-蓝牙 3-红外
    private Integer moduleType;
    //数据类型: 2-控制数据 3-运行数据 4-故障数据 为空暂定
    private Integer dataType;

    public Integer getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getDataTypeNameEn() {
        return dataTypeNameEn;
    }

    public void setDataTypeNameEn(String dataTypeNameEn) {
        this.dataTypeNameEn = dataTypeNameEn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "DataType{" +
                "dataTypeId=" + dataTypeId +
                ", dataTypeName='" + dataTypeName + '\'' +
                ", dataTypeNameEn='" + dataTypeNameEn + '\'' +
                ", status=" + status +
                ", protocolType=" + protocolType +
                ", moduleType=" + moduleType +
                ", dataType=" + dataType +
                '}';
    }
}
