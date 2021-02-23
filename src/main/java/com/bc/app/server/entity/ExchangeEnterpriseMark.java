package com.bc.app.server.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:唛头
 **/
@Data
public class ExchangeEnterpriseMark  implements Serializable {

    private String id;
    private String name;
    private String content;

    /**
     * 0否 1 默认
     */
    private String isDefault;

    private int sort;

    private String excId;

    private String createUserId;

    private String createTime;

    private String enterpriseId;
}
