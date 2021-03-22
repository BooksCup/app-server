package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.User;
import com.bc.app.server.enums.RoleEnum;
import com.bc.app.server.service.SystemRoleConfigService;
import com.bc.app.server.vo.SystemRoleConfigVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统角色配置
 *
 * @author zhou
 */
@RestController
@RequestMapping("/systemRoleConfig")
public class SystemRoleConfigController {

    @Resource
    private SystemRoleConfigService systemRoleConfigService;

    /**
     * 获取模块下各种角色列表
     *
     * @param enterpriseId 企业ID
     * @param module       模块
     * @return 模块下各种角色列表
     */
    @ApiOperation(value = "获取模块下各种角色列表", notes = "获取模块下各种角色列表")
    @GetMapping("")
    public ResponseEntity<SystemRoleConfigVo> getSystemRoleConfigVo(
            @RequestParam String enterpriseId,
            @RequestParam String module) {
        ResponseEntity<SystemRoleConfigVo> responseEntity;
        try {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("module", module);
            List<User> userList = systemRoleConfigService.getUserListByModuleAndRole(paramMap);
            SystemRoleConfigVo systemRoleConfigVo = new SystemRoleConfigVo();
            List<User> adminList = new ArrayList<>();
            List<User> auditorList = new ArrayList<>();
            List<User> copyList = new ArrayList<>();
            for (User user : userList) {
                if (RoleEnum.ADMIN.getCode().equals(user.getSystemRole())) {
                    adminList.add(user);
                } else if (RoleEnum.AUDITOR.getCode().equals(user.getSystemRole())) {
                    auditorList.add(user);
                } else if (RoleEnum.COPY.getCode().equals(user.getSystemRole())) {
                    copyList.add(user);
                }
            }
            systemRoleConfigVo.setAdminList(adminList);
            systemRoleConfigVo.setAuditorList(auditorList);
            systemRoleConfigVo.setCopyList(copyList);

            responseEntity = new ResponseEntity<>(systemRoleConfigVo, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(new SystemRoleConfigVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
