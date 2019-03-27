package com.detroitlabs.smile.Model.SpinDataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Vehicles extends ArrayList<SpinScooter> {

}
