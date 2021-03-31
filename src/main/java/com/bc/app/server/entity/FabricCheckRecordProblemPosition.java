package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 记录问题表
 */
@Data
public class FabricCheckRecordProblemPosition implements Serializable {


    private  String widthTop;
    private  String widthMiddle;
    private  String widthBottom;
    private  String id;
    private  String problemPosition;
    private  String recordId;
    private  String remark;

    private List<FabricCheckRecordProblem> fabricCheckRecordProblemList;
    private String fabricCheckRecordProblemStrJson;

}
