package com.bc.app.server.service.impl;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.App;
import com.bc.app.server.entity.UserApp;
import com.bc.app.server.mapper.UserAppMapper;
import com.bc.app.server.service.UserAppService;
import com.bc.app.server.vo.appcontrollervo.UserAppVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取应用分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 应用分页信息
     */
    @Override
    public PageInfo<App> getAppList(Map<String, String> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<App> appList = userAppMapper.getAppList(paramMap);
        if (CollectionUtils.isNotEmpty(appList)) {
            for (App app : appList) {
                if (!StringUtils.isEmpty(app.getUserAppId())) {
                    app.setIsInstall(Constant.IS_INSTALL);
                } else {
                    app.setIsInstall(Constant.IS_NOT_INSTALL);
                }
            }
        }
        return new PageInfo<>(appList);
    }

    /**
     * 获取个人所有应用
     *
     * @param paramMap 参数map
     * @return 个人所有应用
     */
    @Override
    public UserAppVo getAppListByUserId(Map<String, String> paramMap) {
        List<App> appList = userAppMapper.getAppListByUserId(paramMap);
        List<App> fastAppList = userAppMapper.getFastAppListByUserId(paramMap);
        UserAppVo userAppVo = new UserAppVo();
        userAppVo.setUserFastAppList(fastAppList);
        userAppVo.setUserAppList(appList);
        return userAppVo;
    }
}
