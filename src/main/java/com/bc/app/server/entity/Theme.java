package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Theme implements Serializable {
    private String id;
    private String userId;
    private String enterpriseId;
    private String themeTitle;

}
