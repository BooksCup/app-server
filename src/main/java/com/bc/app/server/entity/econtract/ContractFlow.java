package com.bc.app.server.entity.econtract;

import java.util.List;

/**
 * 签署流程
 *
 * @author zhou
 */
public class ContractFlow {

    private List<Doc> docs;
    private FlowInfo flowInfo;
    private List<Signer> signers;

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public FlowInfo getFlowInfo() {
        return flowInfo;
    }

    public void setFlowInfo(FlowInfo flowInfo) {
        this.flowInfo = flowInfo;
    }

    public List<Signer> getSigners() {
        return signers;
    }

    public void setSigners(List<Signer> signers) {
        this.signers = signers;
    }

}
