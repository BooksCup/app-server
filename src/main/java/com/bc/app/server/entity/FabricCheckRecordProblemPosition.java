package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 记录问题表
 */
@Data
public class FabricCheckRecordProblemPosition implements Serializable {

    private  String id;
    private  String problemPosition;
    private  String recordId;

    private List<FabricCheckRecordProblem> fabricCheckRecordProblemList;
    private String fabricCheckRecordProblemStrJson;

}
