package com.bc.app.server.entity.econtract;

public class SignerAccount {

    private String signerAccountId;
    private String authorizedAccountId;

    public String getSignerAccountId() {
        return signerAccountId;
    }

    public void setSignerAccountId(String signerAccountId) {
        this.signerAccountId = signerAccountId;
    }

    public String getAuthorizedAccountId() {
        return authorizedAccountId;
    }

    public void setAuthorizedAccountId(String authorizedAccountId) {
        this.authorizedAccountId = authorizedAccountId;
    }
}
