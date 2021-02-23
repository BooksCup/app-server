package com.bc.app.server.vo.fabricqcrecordcontrollervo;

import com.bc.app.server.entity.FabricCheckRecord;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 检查记录表封装类
 *
 * @author qiu
 */
@Data
public class FabricQcRecordAllByCheckLIIdVo implements Serializable {

    private String deliveryDate;
    private String totalNum;
    private String lengthBeforeTotal;
    private String lengthAfterTotal;
    private String weightBeforeTotal;
    private String weightAfterTotal;
    private List<FabricCheckRecord> fabricCheckRecords;

}
