package com.bc.app.server.entity.econtract;

/**
 * 流程基本信息
 *
 * @author zhou
 */
public class FlowInfo {

    private String businessScene;
    private FlowConfigInfo flowConfigInfo;

    public String getBusinessScene() {
        return businessScene;
    }

    public void setBusinessScene(String businessScene) {
        this.businessScene = businessScene;
    }

    public FlowConfigInfo getFlowConfigInfo() {
        return flowConfigInfo;
    }

    public void setFlowConfigInfo(FlowConfigInfo flowConfigInfo) {
        this.flowConfigInfo = flowConfigInfo;
    }
}
