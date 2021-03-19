package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qiu
 */
@Data
public class GoodsAttachmentDetail implements Serializable {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    private String path;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
    private String updateUserName;
}
