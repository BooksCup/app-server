package com.bc.app.server.mapper;

import com.bc.app.server.entity.Contract;

import java.util.List;
import java.util.Map;

/**
 * 合同
 *
 * @author qiu
 */
public interface ContractMapper {

    /**
     * 获取合同列表
     *
     * @param map 参数map
     * @return 合同列表
     */
    List<Contract> getContractList(Map<String, String> map);

}
