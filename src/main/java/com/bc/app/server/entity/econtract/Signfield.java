package com.bc.app.server.entity.econtract;

public class Signfield {

    private boolean autoExecute;
    private int actorIndentityType;
    private String fileId;
    private int signType = 0;

    public boolean isAutoExecute() {
        return autoExecute;
    }

    public void setAutoExecute(boolean autoExecute) {
        this.autoExecute = autoExecute;
    }

    public int getActorIndentityType() {
        return actorIndentityType;
    }

    public void setActorIndentityType(int actorIndentityType) {
        this.actorIndentityType = actorIndentityType;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public int getSignType() {
        return signType;
    }

    public void setSignType(int signType) {
        this.signType = signType;
    }
}
