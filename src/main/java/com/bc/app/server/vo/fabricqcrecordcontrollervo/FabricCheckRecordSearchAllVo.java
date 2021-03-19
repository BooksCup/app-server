package com.bc.app.server.vo.fabricqcrecordcontrollervo;

import com.bc.app.server.entity.ProblemImageClassify;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FabricCheckRecordSearchAllVo implements Serializable{

    private List<FabricQcRecordAllByCheckLIIdVo> fabricQcRecordAllByCheckLIIdVoList;

    private List<ProblemImageClassify> problemImageClassifyList;

}
