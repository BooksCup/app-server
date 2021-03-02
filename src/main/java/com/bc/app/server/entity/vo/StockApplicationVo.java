package com.bc.app.server.entity.vo;

import com.bc.app.server.entity.StockApplication;
import lombok.Data;

import java.io.Serializable;

/**
 * @author whl
 */
@Data
public class StockApplicationVo extends StockApplication implements Serializable {

    private String exchangeEnterpriseName;

    private String createUserName;

    private String goodsName;

    private String goodsNo;

    private String goodsUnit;

    private String goodsPhotos;
}
