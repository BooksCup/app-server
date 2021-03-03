package com.bc.app.server.vo.fabriccheckrecordproblemcontrollervo;

import com.bc.app.server.entity.FabricCheckProblemConfig;
import com.bc.app.server.entity.FabricCheckRecordProblemPosition;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetFabricQcRecordProblemByRecordIdVo implements Serializable{

    private List<FabricCheckProblemConfig> fabricCheckProblemConfigList;
    private List<FabricCheckRecordProblemPosition> fabricCheckRecordProblemPositionList;

}
