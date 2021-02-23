package com.bc.app.server.vo.fabricqcrecordcontrollervo;


import com.bc.app.server.entity.FabricCheckRecordProblem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SelectByIdVo implements Serializable{

    private String  id;
    private String  sno;
    private String  lengthBefore;
    private String lengthAfter;
    private String lengthUnit;
    private String weightBefore;
    private String weightAfter;
    private String weightUnit;
    private String createTime;
    private String  modifyTime;
    private String isDelete;
    private String remark;
    private String goodsName;
    private String cylinderNumber;
    private String deliveryDates;

    private List<FabricCheckRecordProblem>  recordProblemList;
}
