package com.bc.app.server.service.impl;

import com.bc.app.server.entity.App;
import com.bc.app.server.entity.UserApp;
import com.bc.app.server.mapper.UserAppMapper;
import com.bc.app.server.service.UserAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户应用程序
 *
 * @author zhou
 */
@Service("userAppService")
public class UserAppServiceImpl implements UserAppService {

    @Resource
    private UserAppMapper userAppMapper;

    /**
     * 重置用户应用程序
     *
     * @param userId 用户ID
     */
    @Override
    public void resetUserApp(String userId) {
        userAppMapper.deleteAppByUserId(userId);
        List<App> appList = userAppMapper.getDefaultInstallAppList();
        List<UserApp> userAppList = new ArrayList<>();
        for (App app : appList) {
            UserApp userApp = new UserApp(userId, app.getAppId());
            userAppList.add(userApp);
        }
        userAppMapper.batchAddUserApp(userAppList);
    }
}
