package com.detroitlabs.smile.Model.GeoDataModel;

import com.detroitlabs.smile.Model.GeoDataModel.AddressData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressMatches extends ArrayList<AddressData> {
}
