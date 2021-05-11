package com.bc.app.server.entity.econtract;

import java.util.List;

public class Signer {

    private boolean platformSign;
    private int signOrder = 1;
    private SignerAccount signerAccount;
    private List<Signfield> signfields;
    private String thirdOrderNo;

    public boolean isPlatformSign() {
        return platformSign;
    }

    public void setPlatformSign(boolean platformSign) {
        this.platformSign = platformSign;
    }

    public int getSignOrder() {
        return signOrder;
    }

    public void setSignOrder(int signOrder) {
        this.signOrder = signOrder;
    }

    public SignerAccount getSignerAccount() {
        return signerAccount;
    }

    public void setSignerAccount(SignerAccount signerAccount) {
        this.signerAccount = signerAccount;
    }

    public List<Signfield> getSignfields() {
        return signfields;
    }

    public void setSignfields(List<Signfield> signfields) {
        this.signfields = signfields;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public void setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
    }

}
