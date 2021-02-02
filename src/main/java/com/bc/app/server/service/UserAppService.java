package com.bc.app.server.service;

/**
 * 用户应用程序
 *
 * @author zhou
 */
public interface UserAppService {

    /**
     * 重置用户应用程序
     *
     * @param userId 用户ID
     */
    void resetUserApp(String userId);

}
