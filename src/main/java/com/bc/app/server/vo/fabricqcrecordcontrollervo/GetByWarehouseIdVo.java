package com.bc.app.server.vo.fabricqcrecordcontrollervo;


import com.bc.app.server.entity.FabricCheckRecord;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetByWarehouseIdVo implements Serializable {

    private String  totalNum;
    private String  lengthBeforeTotal;
    private String lengthAfterTotal;
    private String weightBeforeTotal;
    private String weightAfterTotal;
    private List<FabricCheckRecord> fabricCheckRecordList;
}
