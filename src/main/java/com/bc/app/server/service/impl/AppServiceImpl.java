package com.bc.app.server.service.impl;

import com.bc.app.server.entity.App;
import com.bc.app.server.mapper.AppMapper;
import com.bc.app.server.service.AppService;
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

    /**
     * 获取应用列表
     *
     * @return 应用列表
     */
    @Override
    public List<App> getAppList() {
        return appMapper.getAppList();
    }

}
