package com.bc.app.server.vo.fabriccheckrecordproblemcontrollervo;

import com.bc.app.server.entity.FabricCheckRecordProblem;
import lombok.Data;

import java.io.Serializable;

@Data
public class FabricCheckRecordProblemVo implements Serializable {

    private String problemPosition;
    private FabricCheckRecordProblem fabricCheckRecordProblem;
}
