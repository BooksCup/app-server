package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 面料验货问题配置表
 */
@Data
public class FabricCheckProblemConfig implements Serializable{
    private String id;
    private String enterpriseId;
    private String tag;
    private String createTime;


}
