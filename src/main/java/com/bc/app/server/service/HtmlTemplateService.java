package com.bc.app.server.service;

import com.bc.app.server.entity.Contract;

/**
 * 网页模板
 *
 * @author zhou
 */
public interface HtmlTemplateService {

    /**
     * 获取合同网页模板
     *
     * @param contract 合同
     * @return 合同网页模板
     * @throws Exception 异常
     */
    String getContractHtmlTemplate(Contract contract) throws Exception;

}
