package com.bc.app.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.AddressBook;
import com.bc.app.server.entity.OssConfig;
import com.bc.app.server.entity.Schedule;
import com.bc.app.server.entity.SystemConfig;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.AddressBookService;
import com.bc.app.server.service.ScheduleService;
import com.bc.app.server.service.SystemConfigService;
import com.bc.app.server.utils.AvatarUtil;
import com.bc.app.server.utils.JsonUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    AddressBookService addressBookService;

    @Resource
    SystemConfigService systemConfigService;

    /**
     * 新建日程
     *
     * @param title          标题
     * @param userId         用户ID
     * @param beginTime      开始时间
     * @param endTime        结束时间
     * @param address        地址
     * @param addressBookIds 通讯录ID列表(jsonArray格式)
     * @param remark         备注
     * @param images         相关图片(jsonArray格式)
     * @return 新建结果
     */
    @ApiOperation(value = "新建日程", notes = "新建日程")
    @PostMapping(value = "")
    public ResponseEntity<String> addSchedule(
            @RequestParam String title,
            @RequestParam String userId,
            @RequestParam String beginTime,
            @RequestParam String endTime,
            @RequestParam String address,
            @RequestParam(required = false) String addressBookIds,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) String images) {
        ResponseEntity<String> responseEntity;
        try {
            SystemConfig systemConfig = systemConfigService.getSystemConfigByConfigKey("OSS");
            OssConfig ossConfig = JSON.parseObject(systemConfig.getValue(), OssConfig.class);

            Schedule schedule = new Schedule(title, userId, beginTime, endTime, address, remark, images);

            List<String> addressBookIdList = JsonUtil.jsonArrayToList(addressBookIds, String.class);
            List<String> originImageList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(addressBookIdList)) {
                schedule.setPeopleNum(addressBookIdList.size());
                List<AddressBook> addressBookList = addressBookService.getAddressBookListByIdList(addressBookIdList);
                for (AddressBook addressBook : addressBookList) {
                    List<String> imageList = JsonUtil.jsonArrayToList(addressBook.getImages(), String.class);
                    if (!CollectionUtils.isEmpty(imageList)) {
                        originImageList.add(imageList.get(0));
                    }
                }
            } else {
                schedule.setPeopleNum(0);
            }
            String scheduleAvatar = AvatarUtil.getCombinationAvatar(originImageList, ossConfig);
            schedule.setAvatar(scheduleAvatar);
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

    /**
     * 获取日程分页信息
     *
     * @param userId   用户ID
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 日程分页信息
     */
    @ApiOperation(value = "获取日程分页信息", notes = "获取日程分页信息")
    @GetMapping(value = "/search")
    public ResponseEntity<PageInfo<Schedule>> getSchedulePageInfo(
            @RequestParam String userId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<Schedule>> responseEntity;
        Map<String, Object> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("userId", userId);
        try {
            PageInfo<Schedule> pageInfo = scheduleService.getSchedulePageInfo(map, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 删除日程
     *
     * @param scheduleId 日程ID
     * @return 删除结果
     */
    @ApiOperation(value = "删除日程", notes = "删除日程")
    @DeleteMapping(value = "/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable String scheduleId) {
        ResponseEntity<String> responseEntity;
        try {
            scheduleService.deleteSchedule(scheduleId);
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    DELETE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[deleteSchedule] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    DELETE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}