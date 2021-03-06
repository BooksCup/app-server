package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.User;
import com.bc.app.server.entity.UserApply;
import com.bc.app.server.entity.vo.EmployeeVo;
import com.bc.app.server.service.UserApplyService;
import com.bc.app.server.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员
 *
 * @author zhou
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Resource
    private UserService userService;

    @Autowired
    private UserApplyService userApplyService;

    /**
     * 获取人员分页信息
     *
     * @param enterpriseId 企业id
     * @param pageNum      当前分页数
     * @param pageSize     分页大小
     * @return 人员分页信息
     */
    @ApiOperation(value = "获取人员分页信息", notes = "获取人员分页信息")
    @GetMapping(value = "/search")
    public ResponseEntity<EmployeeVo> getEmployeePageInfo(
            @RequestParam String enterpriseId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        logger.info("[getEmployeePageInfo] pageNum: " + pageNum + ", pageSize: " + pageSize);
        ResponseEntity<EmployeeVo> responseEntity;
        Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        paramMap.put("enterpriseId", enterpriseId);
        try {
            PageInfo<User> userPageInfo = userService.getUserPageInfo(paramMap, pageNum, pageSize);
            paramMap.clear();
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("operateStatus", Constant.OPERATE_STATUS_INIT);
            List<UserApply> userApplyList = userApplyService.getUserApplyList(paramMap);
            EmployeeVo employeeVo = new EmployeeVo();
            employeeVo.setApplyNum(userApplyList.size());
            employeeVo.setUserPageInfo(userPageInfo);
            responseEntity = new ResponseEntity<>(employeeVo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new EmployeeVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
