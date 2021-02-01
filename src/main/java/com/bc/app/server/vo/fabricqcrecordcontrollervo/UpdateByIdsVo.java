package com.bc.app.server.vo.fabricqcrecordcontrollervo;


import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateByIdsVo implements Serializable{
   private List<UpdateByIdVo> list;
}
