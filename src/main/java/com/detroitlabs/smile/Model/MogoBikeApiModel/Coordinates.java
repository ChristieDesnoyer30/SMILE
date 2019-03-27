package com.detroitlabs.smile.Model.MogoBikeApiModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates extends ArrayList<Double> {

}
