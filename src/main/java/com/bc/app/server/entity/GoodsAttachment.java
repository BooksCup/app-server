package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiu
 */
@Data
public class GoodsAttachment implements Serializable {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


    private List<GoodsAttachmentDetail> goodsAttachmentDetails;
}
