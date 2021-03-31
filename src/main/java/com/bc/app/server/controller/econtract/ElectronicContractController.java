package com.bc.app.server.controller.econtract;

import com.bc.app.server.entity.Contract;
import com.bc.app.server.entity.ContractSignFlow;
import com.bc.app.server.entity.econtract.*;
import com.bc.app.server.service.ElectronicContractService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 电子合同
 *
 * @author zhou
 */
@RestController
@RequestMapping("/electronicContract")
public class ElectronicContractController {

    @Resource
    ElectronicContractService electronicContractService;

    @ApiOperation(value = "1", notes = "1")
    @PostMapping(value = "/signFlow")
    public ResponseEntity<String> createSignFlow(
            @RequestParam String enterpriseId,
            @RequestParam String sealId,
            @RequestParam String contractId
    ) {
        ResponseEntity<String> responseEntity;
        try {
            String fileId = "0b56e67b4b2548478bbca45d9b9d4a20";
            ContractFlow contractFlow = new ContractFlow();
            List<Doc> docList = new ArrayList<>();
            Doc doc = new Doc();
            doc.setFileId(fileId);
            doc.setFileName("个人借贷合同.pdf");
            docList.add(doc);

            FlowInfo flowInfo = new FlowInfo();
            flowInfo.setBusinessScene("测试流程签署");

            List<Signer> signerList = new ArrayList<>();
            Signer signer = new Signer();
            SignerAccount signerAccount = new SignerAccount();
            signerAccount.setSignerAccountId("8ba23c23bdd4483b86bdac79d301ddb1");
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

            electronicContractService.createSignFlow2(contractFlow);
            responseEntity = new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
