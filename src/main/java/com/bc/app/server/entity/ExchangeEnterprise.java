package com.bc.app.server.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 往来企业信息
 */

@Data
public class ExchangeEnterprise implements Serializable {

    private String id;

    private String name;

    private String shortName;

    private String address;

    private String manager;

    private String buildTime;


}
