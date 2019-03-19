package com.detroitlabs.smile.Model.GeoDataModel;

import com.detroitlabs.smile.Model.GeoDataModel.CensusBlocks;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geographies {

    private CensusBlocks censusBlocks;

    @JsonProperty("2010 Census Blocks")
    public CensusBlocks getCensusBlocks() {
        return censusBlocks;
    }

    @JsonProperty("2010 Census Blocks")
    public void setCensusBlocks(CensusBlocks censusBlocks) {
        this.censusBlocks = censusBlocks;
    }
}
