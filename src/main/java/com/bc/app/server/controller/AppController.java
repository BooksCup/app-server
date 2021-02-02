package com.bc.app.server.controller;

import com.bc.app.server.entity.App;
import com.bc.app.server.service.AppService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 应用
 *
 * @author zhou
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Resource
    private AppService appService;

    @ApiOperation(value = "获取应用列表", notes = "获取应用列表")
    @GetMapping(value = "")
    public ResponseEntity<List<App>> getAppList() {
        ResponseEntity<List<App>> responseEntity;
        try {
            List<App> appList = appService.getAppList();
            responseEntity = new ResponseEntity<>(appList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
