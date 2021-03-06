package com.bc.app.server.entity;

import lombok.Data;

/**
 * 应用
 *
 * @author zhou
 */
@Data
public class App {

    private String appId;
    private String name;
    private String icon;
    private String route;
    private String describes;
    private Integer sort;

    private String userAppId;
    private String isInstall;
    private String userId;
    private String isFast;

}
