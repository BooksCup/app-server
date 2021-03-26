package com.bc.app.server.entity;

import lombok.Data;

import java.util.Map;

/**
 * 电子合同API返回值
 *
 * @author zhou
 */
@Data
public class EContractApiResult {

    private String message;
    private int code;
    private Map<String, Object> data;

}
