package com.detroitlabs.smile.Model.LyftData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nearby_Drivers extends ArrayList<LocationInfo> {
}
