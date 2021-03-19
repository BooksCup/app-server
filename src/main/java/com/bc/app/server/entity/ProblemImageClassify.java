package com.bc.app.server.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemImageClassify implements Serializable{

    private String tag;
    private String images;
}
