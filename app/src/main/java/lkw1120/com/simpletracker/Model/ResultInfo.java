package lkw1120.com.simpletracker.Model;

public class ResultInfo {

    private String status;
    private String to;
    private String txHash;
    private String txIndex;
    private String blockHeight;
    private String blockHash;
    private String cumulativeStepUsed;
    private String stepUsed;
    private String stepPrice;
    private String scoreAddress;
    private String eventLogs;
    private String logsBloom;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getTxIndex() {
        return txIndex;
    }

    public void setTxIndex(String txIndex) {
        this.txIndex = txIndex;
    }

    public String getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(String blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getCumulativeStepUsed() {
        return cumulativeStepUsed;
    }

    public void setCumulativeStepUsed(String cumulativeStepUsed) {
        this.cumulativeStepUsed = cumulativeStepUsed;
    }

    public String getStepUsed() {
        return stepUsed;
    }

    public void setStepUsed(String stepUsed) {
        this.stepUsed = stepUsed;
    }

    public String getStepPrice() {
        return stepPrice;
    }

    public void setStepPrice(String stepPrice) {
        this.stepPrice = stepPrice;
    }

    public String getScoreAddress() {
        return scoreAddress;
    }

    public void setScoreAddress(String scoreAddress) {
        this.scoreAddress = scoreAddress;
    }

    public String getEventLogs() {
        return eventLogs;
    }

    public void setEventLogs(String eventLogs) {
        this.eventLogs = eventLogs;
    }

    public String getLogsBloom() {
        return logsBloom;
    }

    public void setLogsBloom(String logsBloom) {
        this.logsBloom = logsBloom;
    }
}
