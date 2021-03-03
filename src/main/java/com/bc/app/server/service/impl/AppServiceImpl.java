package com.bc.app.server.service.impl;

import com.bc.app.server.entity.App;
import com.bc.app.server.mapper.AppMapper;
import com.bc.app.server.mapper.UserAppMapper;
import com.bc.app.server.service.AppService;
import com.bc.app.server.utils.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用
 *
 * @author zhou
 */
@Service("appService")
public class AppServiceImpl implements AppService {

    @Resource
    private AppMapper appMapper;

    @Autowired
    UserAppServiceImpl userAppService;

    @Autowired
    UserAppMapper userAppMapper;


    /**
     * 重置已经安装的应用列表
     *
     * @param appList 应用列表
     */
    @Override
    public void resetUserApp(List<App> appList, String userId) {
        //根据用户ID删除应用程序
        userAppMapper.deleteAppByUserId(userId);
        if (CollectionUtils.isNotEmpty(appList)) {
            for (App app : appList) {
                app.setUaId(CommonUtil.generateId());
            }
            appMapper.batchAddUserApp(appList);
        }
    }

}
