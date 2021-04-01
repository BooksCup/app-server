package com.bc.app.server.controller.econtract;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.User;
import com.bc.app.server.entity.econtract.*;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.ElectronicContractService;
import com.bc.app.server.service.FileService;
import com.bc.app.server.service.UserService;
import com.bc.app.server.utils.PlaceholderUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电子合同
 *
 * @author zhou
 */
@RestController
@RequestMapping("/electronicContract")
public class ElectronicContractController {

    private static final Logger logger = LoggerFactory.getLogger(ElectronicContractController.class);

    @Resource
    ElectronicContractService electronicContractService;

    @Resource
    FileService fileService;

    @Resource
    UserService userService;

    /**
     * @param subject
     * @param sealId
     * @param contractId
     * @param signerPhone
     * @return
     */
    @ApiOperation(value = "创建并开启签署流程", notes = "创建并开启签署流程")
    @PostMapping(value = "/signFlow")
    public ResponseEntity<String> startSignFlow(
            @RequestParam String subject,
            @RequestParam String sealId,
            @RequestParam String contractId,
            @RequestParam String signerPhone) {
        ResponseEntity<String> responseEntity;
        try {
            String accountId;
            // 判断签署人是否在签署平台注册了账户
            List<User> userList = userService.getUserByPhone(signerPhone);
            if (!CollectionUtils.isEmpty(userList) && !StringUtils.isEmpty(userList.get(0).getAccountId())) {
                // 已注册账户
                // 走签署流程
                accountId = userList.get(0).getAccountId();
            } else {
                // 未注册账户
                // 短信提醒注册
                logger.info("[startSignFlow] stop: phone: " + signerPhone + " not exists.");
                return new ResponseEntity<>("", HttpStatus.OK);
            }

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/contract.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer templateBuffer = new StringBuffer();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                templateBuffer.append(data);
            }

            String template = templateBuffer.toString();
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            String htmlContent = PlaceholderUtil.replace(template, paramMap);
            String fileId = fileService.uploadFileToSignPlatform(htmlContent);

            ContractFlow contractFlow = new ContractFlow();
            List<Doc> docList = new ArrayList<>();
            Doc doc = new Doc();
            doc.setFileId(fileId);
            doc.setFileName(subject + ".pdf");
            docList.add(doc);

            FlowInfo flowInfo = new FlowInfo();
            flowInfo.setBusinessScene(subject);

            List<Signer> signerList = new ArrayList<>();
            Signer signer = new Signer();
            SignerAccount signerAccount = new SignerAccount();
            signerAccount.setSignerAccountId(accountId);
            List<Signfield> signfieldList = new ArrayList<>();
            Signfield signField = new Signfield();
            signField.setFileId(fileId);
            signfieldList.add(signField);

            signer.setSignerAccount(signerAccount);
            signer.setSignfields(signfieldList);
            signerList.add(signer);

            contractFlow.setDocs(docList);
            contractFlow.setFlowInfo(flowInfo);
            contractFlow.setSigners(signerList);

            responseEntity = electronicContractService.createAndStartSignFlow(contractFlow);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.CREATE_AND_START_SIGN_FLOW_ERROR.getResponseCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
