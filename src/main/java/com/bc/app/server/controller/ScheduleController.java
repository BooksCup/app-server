package com.bc.app.server.controller;

import com.bc.app.server.entity.Schedule;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 日程
 *
 * @author zhou
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Resource
    ScheduleService scheduleService;

    @ApiOperation(value = "新建日程", notes = "新建日程")
    @PostMapping(value = "")
    public ResponseEntity<String> addSchedule(
            @RequestParam String title,
            @RequestParam String beginTime,
            @RequestParam String endTime,
            @RequestParam String address,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) String images) {
        ResponseEntity<String> responseEntity;
        try {
            Schedule schedule = new Schedule(title, beginTime, endTime, address, remark, images);
            scheduleService.addSchedule(schedule);
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[addSchedule] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
