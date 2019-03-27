package com.detroitlabs.smile.Model.GeoCodingMogoApiModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {
    private BlockInfo blockInfo;

    public BlockInfo getBlockInfo() {
        return blockInfo;
    }

    public void setBlockInfo(BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
    }
}
