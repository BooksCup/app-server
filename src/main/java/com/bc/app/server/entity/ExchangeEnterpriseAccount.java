package com.bc.app.server.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: wd-saas
 * @description:
 **/
@Data
public class ExchangeEnterpriseAccount implements Serializable {

    private String id;
    private String name;
    private String bank;
    private String number;
    private String balance;
    private String currency;
    private String remark;
    private String excId;
    private String createUserId;
    private String createTime;
    private String enterpriseId;
    private int sort;

}
