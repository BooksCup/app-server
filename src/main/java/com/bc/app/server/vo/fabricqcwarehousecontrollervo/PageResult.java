package com.bc.app.server.vo.fabricqcwarehousecontrollervo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageResult<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 返回的总数
     */
    private Long totalCount;

    public PageResult(Integer code, String msg, T data, Long totalCount) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.totalCount = totalCount;
    }

    public PageResult() {
    }
}
