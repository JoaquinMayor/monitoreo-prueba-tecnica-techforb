package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.security.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthoritiesJsonCreator {

    @JsonCreator
    public SimpleGrantedAuthoritiesJsonCreator(@JsonProperty("authority") String role){
        
    }
}
