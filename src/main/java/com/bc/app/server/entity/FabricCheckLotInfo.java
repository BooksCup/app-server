package com.bc.app.server.entity;

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

}
