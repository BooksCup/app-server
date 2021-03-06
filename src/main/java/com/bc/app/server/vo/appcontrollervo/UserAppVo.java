package com.bc.app.server.vo.appcontrollervo;

import com.bc.app.server.entity.App;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户APP VO
 *
 * @author zhou
 */
@Data
public class UserAppVo implements Serializable {

    private List<App> userFastAppList;
    private List<App> userAppList;

}
