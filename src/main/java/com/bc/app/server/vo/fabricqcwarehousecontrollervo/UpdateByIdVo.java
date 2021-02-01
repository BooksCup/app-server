package com.bc.app.server.vo.fabricqcwarehousecontrollervo;


import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateByIdVo  implements Serializable{

    private String  id;
    private String lengthAfter;
    private String weightAfter;
    private String remark;
}
