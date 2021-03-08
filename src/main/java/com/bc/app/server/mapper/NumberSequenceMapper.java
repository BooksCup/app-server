package com.bc.app.server.mapper;

import com.bc.app.server.entity.NumberSequence;
import org.apache.ibatis.annotations.Param;

/**
 * 编号规则数据层接口
 *
 * @author leo
 */
public interface NumberSequenceMapper {

    /**
     * 根据类型获取最新的编号对象
     *
     * @param enterpriseId
     * @param type
     * @return
     */
    NumberSequence getByType(@Param("enterpriseId") String enterpriseId, @Param("type") String type);

    /**
     * 更新最新的值
     *
     * @param id
     * @return
     */
    int updateVal(String id);

    /**
     * 新增
     *
     * @param numberSequence
     * @return
     */
    int insert(NumberSequence numberSequence);

}
