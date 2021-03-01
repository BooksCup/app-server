package com.bc.app.server.entity;

import com.bc.app.server.utils.CommonUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 记录问题表
 */
@Data
public class FabricCheckRecordProblem implements Serializable {

    private  String id;
    private  String recordId;
    private  String tag;
    private  String tagATimes;
    private  String tagBTimes;
    private  String tagCTimes;
    private  String tagDTimes;
    private  String remark;
    private  String image;
    private  String createTime;
    private  String isDelete;
    private  String problemPosition;

    public FabricCheckRecordProblem(String id, String tag, String tagATimes, String tagBTimes, String tagCTimes, String tagDTimes, String remark, String image) {
        this.id = id;
        this.tag = tag;
        this.tagATimes = tagATimes;
        this.tagBTimes = tagBTimes;
        this.tagCTimes = tagCTimes;
        this.tagDTimes = tagDTimes;
        this.remark = remark;
        this.image = image;
    }

    public FabricCheckRecordProblem() {
    }
    public FabricCheckRecordProblem(String id, String recordId, String tag, String tagATimes, String tagBTimes, String tagCTimes, String tagDTimes, String remark, String image) {
        this.id = CommonUtil.generateId();
        this.recordId =recordId;
        this.tag = tag;
        this.tagATimes = tagATimes;
        this.tagBTimes = tagBTimes;
        this.tagCTimes = tagCTimes;
        this.tagDTimes = tagDTimes;
        this.remark = remark;
        this.image = image;
    }

    public FabricCheckRecordProblem(String id, String recordId, String tag, String tagATimes, String tagBTimes, String tagCTimes, String tagDTimes, String remark, String image, String createTime, String isDelete) {
        this.id = id;
        this.recordId = recordId;
        this.tag = tag;
        this.tagATimes = tagATimes;
        this.tagBTimes = tagBTimes;
        this.tagCTimes = tagCTimes;
        this.tagDTimes = tagDTimes;
        this.remark = remark;
        this.image = image;
        this.createTime = createTime;
        this.isDelete = isDelete;
    }
}
