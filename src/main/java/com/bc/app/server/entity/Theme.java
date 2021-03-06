package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 主题
 *
 * @author zhou
 */
@Data
public class Theme implements Serializable {

    private String id;
    private String userId;
    private String enterpriseId;
    private String themeTitle;

}
