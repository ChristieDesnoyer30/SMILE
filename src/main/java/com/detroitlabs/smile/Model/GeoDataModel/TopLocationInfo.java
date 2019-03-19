package com.detroitlabs.smile.Model.GeoDataModel;

import com.detroitlabs.smile.Model.GeoDataModel.Result;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopLocationInfo {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
