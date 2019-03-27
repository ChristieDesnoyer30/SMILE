package com.detroitlabs.smile.Model.GeoCodingMogoApiModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllGeoData {
    private Block block;

    @JsonProperty("Block")
    public Block getBlock() {
        return block;
    }

    @JsonProperty("Block")
    public void setBlock(Block block) {
        this.block = block;
    }
}
