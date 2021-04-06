package com.bc.app.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.ElectronicContractApiResult;
import com.bc.app.server.entity.UserAccount;
import com.bc.app.server.mapper.UserAccountMapper;
import com.bc.app.server.service.RealNameCertService;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.utils.ElectronicContractHttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 实名认证
 *
 * @author zhou
 */
@Service("realNameCertService")
public class RealNameCertServiceImpl implements RealNameCertService {

    @Resource
    private UserAccountMapper userAccountMapper;

    /**
     * code=53000000，message=账号已存在
     */
    private static final int ACCOUNT_ALREADY_EXIST = 53000000;

    /**
     * 添加用户账号至e签宝平台并开启实名认证流程
     * 对个人运营商三要素信息进行核验，成功后向手机号发送验证码
     *
     * @param idType      认证证件类型
     *                    CRED_PSN_CH_IDCARD:大陆身份证
     *                    CRED_PSN_CH_TWCARD:台湾来往大陆通行证
     *                    CRED_PSN_CH_MACAO:澳门来往大陆通行证
     *                    CRED_PSN_CH_HONGKONG:香港来往大陆通行证
     *                    CRED_PSN_PASSPORT:护照
     * @param userId      用户ID
     * @param userName    姓名
     * @param identityNum 身份证号
     * @param mobile      手机号
     * @return e签宝平台返回结果
     */
    @Override
    public ElectronicContractApiResult addUserAccount(String idType, String userId, String userName, String identityNum, String mobile) {
        String url = Constant.E_CONTRACT_BASE_URL + "/v1/accounts/createByThirdPartyUserId";
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("userId", userId);
        UserAccount userAccount = userAccountMapper.getUserAccountByUserId(map);
        ElectronicContractApiResult eContractApiResult;
        if (ObjectUtils.isEmpty(userAccount)) {
            // 如果用户e签宝账号为空,需要先去e签宝平台注册账号
            JSONObject paramJson = new JSONObject();
            paramJson.put("thirdPartyUserId", userId);
            paramJson.put("name", userName);
            paramJson.put("idType", idType);
            paramJson.put("idNumber", identityNum);
            paramJson.put("mobile", mobile);
            // 请求api accountId 第三方平台注册的id
            eContractApiResult = ElectronicContractHttpUtil.httpPost(url, String.valueOf(paramJson));
            if (eContractApiResult.getCode() == 0 || eContractApiResult.getCode() == ACCOUNT_ALREADY_EXIST) {
                String accountId = (String) eContractApiResult.getData().get("accountId");
                userAccount = new UserAccount();
                userAccount.setAccountId(accountId);
                userAccount.setId(CommonUtil.generateId());
                userAccount.setUserId(userId);
                userAccount.setCreateBy(userId);
                userAccount.setIdCard(identityNum);
                userAccount.setMobile(mobile);
                userAccountMapper.insert(userAccount);
                eContractApiResult = accountCert(identityNum, accountId, userName, mobile);
            }
        } else {
            eContractApiResult = accountCert(identityNum, userAccount.getAccountId(), userName, mobile);
        }
        return eContractApiResult;
    }

    /**
     * 对个人运营商三要素信息进行核验，成功后向手机号发送验证码
     * url:https://open.esign.cn/doc/detail?id=opendoc%2Fidentity_service%2Fwry7rc&namespace=opendoc%2Fidentity_service
     *
     * @param identityNum 身份证号
     * @param accountId   个人账号ID
     * @param userName    姓名
     * @param mobile      手机号
     * @return e签宝平台返回结果
     */
    public ElectronicContractApiResult accountCert(String identityNum, String accountId, String userName, String mobile) {
        String url = Constant.E_CONTRACT_BASE_URL + "/v2/identity/auth/api/individual/telecom3Factors";
        JSONObject paramJson = new JSONObject();
        paramJson.put("name", userName);
        paramJson.put("idNo", identityNum);
        paramJson.put("mobileNo", mobile);
        paramJson.put("repetition", true);
        ElectronicContractApiResult eContractApi = ElectronicContractHttpUtil.httpPost(url, String.valueOf(paramJson));
        if (eContractApi.getCode() == 0) {
            String flowId = (String) eContractApi.getData().get("flowId");
            UserAccount account = new UserAccount();
            account.setAccountId(accountId);
            account.setFlowId(flowId);
            account.setIdCard(identityNum);
            account.setMobile(mobile);
            userAccountMapper.updateUserAccountByAccountId(account);
        }
        return eContractApi;
    }

    /**
     * 校验验证码
     *
     * @param userId 用户ID
     * @param code   验证码
     * @return e签宝平台返回结果
     */
    @Override
    public ElectronicContractApiResult checkVerifyCode(String userId, String code) {
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("userId", userId);
        UserAccount userAccount = userAccountMapper.getUserAccountByUserId(map);
        ElectronicContractApiResult electronContractApi = null;
        if (!ObjectUtils.isEmpty(userAccount)) {
            String url = Constant.E_CONTRACT_BASE_URL + "/v2/identity/auth/pub/individual/" + userAccount.getFlowId() + "/telecom3Factors";
            JSONObject paramJson = new JSONObject();
            paramJson.put("authcode", code);
            electronContractApi = ElectronicContractHttpUtil.httpPut(url, String.valueOf(paramJson));
            if (electronContractApi.getCode() == 0) {
                userAccount.setRealType(Constant.REAL_TYPE_YES);
                userAccountMapper.updateUserAccountByAccountId(userAccount);
            }
        }
        return electronContractApi;
    }

}
