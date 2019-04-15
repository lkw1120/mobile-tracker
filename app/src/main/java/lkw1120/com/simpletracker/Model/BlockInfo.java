package lkw1120.com.simpletracker.Model;

import java.util.ArrayList;

public class BlockInfo {

    private String version;
    private String prevBlockHash;
    private String merkleTreeRootHash;
    private ArrayList<String>  txHash;
    private String blockHash;
    private String height;
    private String peerId;
    private String signature;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public String getMerkleTreeRootHash() {
        return merkleTreeRootHash;
    }

    public void setMerkleTreeRootHash(String merkleTreeRootHash) {
        this.merkleTreeRootHash = merkleTreeRootHash;
    }

    public ArrayList<String> getTxHash() {
        return txHash;
    }

    public void setTxHash(ArrayList<String> txHash) {
        this.txHash = txHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPeerId() {
        return peerId;
    }

    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
