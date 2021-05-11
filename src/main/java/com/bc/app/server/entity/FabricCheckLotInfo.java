package com.bc.app.server.entity;

import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
import lombok.Data;

/**
 * 面料盘点-缸信息
 *
 * @author zhou
 */
@Data
public class FabricCheckLotInfo {

    private String id;

    /**
     * 缸号
     */
    private String lotNo;

    /**
     * 卷数
     */
    private String num;

    /**
     * 数量
     */
    private String length;

    /**
     * 长度单位
     */
    private String lengthUnit;

    /**
     * 重量
     */
    private String weight;

    /**
     * 重量单位
     */
    private String weightUnit;

    /**
     * 面料盘点任务表id
     */
    private String fabricCheckTaskId;

    /**
     * 删除标记
     */
    private String isDelete;

    /**
     * 0盘点未完成，1盘点完成
     */
    private String status;

    private GetByWarehouseIdVo getByWarehouseIdVo;

    /**
     *胚布号
     */
    private String fabricNumber;

}
